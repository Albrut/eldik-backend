package kg.eldik.incidentmanagement.facade.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.servlet.http.HttpServletRequest;
import kg.eldik.incidentmanagement.facade.AdminFacade;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import kg.eldik.incidentmanagement.payload.response.ZabbixProblemsResponse;
import kg.eldik.incidentmanagement.service.ZabbixApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Component
public class AdminFacadeImpl implements AdminFacade {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final HttpServletRequest request;
    private final ZabbixApiService zabbixApiService;


    @Value("${zabbix.url}")
    private String apiUrl;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_ONLY_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AdminFacadeImpl(RestTemplate restTemplate,
                           ObjectMapper mapper,
                           HttpServletRequest request,
                           ZabbixApiService zabbixApiService) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.request = request;
        this.zabbixApiService = zabbixApiService;
    }


    @Override
    public List<ZabbixProblemsResponse> getAllProblemsZabbix() {
        if (!isAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admins only");
        }

        JsonNode root = callProblemGet();
        if (root.has("error")) {
            throw new RuntimeException("Zabbix API error: " + root.get("error"));
        }
        ArrayNode results = (ArrayNode) root.get("result");
        if (results.isEmpty()) {
            return Collections.emptyList();
        }

        List<ZabbixProblemsResponse> list = new ArrayList<>();
        for (JsonNode node : results) {
            LocalDate dateOfTheProblem = LocalDate.parse(
                    node.get("datetime").asText().substring(0, 10),
                    DATE_ONLY_FORMATTER
            );

            Integer number = node.get("number").asInt();
            String description = node.get("description").asText();
            String notes = node.get("notes").asText("");
            ImportanceEnum importance = mapImportance(node.get("priority").asText());
            List<String> resources = parseResources(node.get("resources").asText());
            String responsible = node.get("responsible").asText();
            String solution = node.hasNonNull("solution")
                    ? node.get("solution").asText()
                    : null;
            StatusEnum status = mapStatus(node.get("status").asText());

            list.add(new ZabbixProblemsResponse(
                    dateOfTheProblem,
                    description,
                    notes,
                    number,
                    importance,
                    resources,
                    responsible,
                    solution,
                    status
            ));
        }
        return list;
    }

    private JsonNode callProblemGet() {
        String token = request.getHeader("X-Auth-Token");
        Map<String, Object> payload = Map.of(
                "jsonrpc", "2.0",
                "method", "problem.get",
                "params", Collections.emptyMap(),
                "auth", token,
                "id", 1
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String raw = restTemplate
                .postForObject(apiUrl, new HttpEntity<>(payload, headers), String.class);
        try {
            return mapper.readTree(raw);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Zabbix response", e);
        }
    }

    private ImportanceEnum mapImportance(String prio) {
        return switch (prio) {
            case "Низкий" -> ImportanceEnum.low;
            case "Средний" -> ImportanceEnum.middle;
            case "Высокий" -> ImportanceEnum.high;
            default -> ImportanceEnum.middle;
        };
    }

    private StatusEnum mapStatus(String st) {
        return switch (st) {
            case "В процессе" -> StatusEnum.in_process;
            case "Закрыт" -> StatusEnum.closed;
            default -> StatusEnum.archived;
        };
    }

    private List<String> parseResources(String res) {
        if (res == null || res.isBlank()) {
            return Collections.emptyList();
        }
        String[] parts = res.split(",");
        List<String> list = new ArrayList<>(parts.length);
        for (String p : parts) {
            list.add(p.trim());
        }
        return list;
    }

    @Override
    public boolean isAdmin() {
        String token = request.getHeader("X-Auth-Token");
        JsonNode userInfo = zabbixApiService.getUserInfo(token);
        if (userInfo == null || userInfo.has("error")) {
            return false;
        }
        JsonNode result = userInfo.get("result").get(0);
        String role = result.get("role").asText();
        return "ADMIN".equals(role);
    }
}