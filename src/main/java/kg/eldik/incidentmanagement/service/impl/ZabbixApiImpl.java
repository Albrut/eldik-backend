package kg.eldik.incidentmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kg.eldik.incidentmanagement.service.SystemAdminService;
import kg.eldik.incidentmanagement.service.ZabbixApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ZabbixApiImpl implements ZabbixApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final SystemAdminService systemAdminService;

    @Value("${zabbix.url}")
    private String zabbixUrl;

    public ZabbixApiImpl(RestTemplate restTemplate, SystemAdminService systemAdminService) {
        this.restTemplate = restTemplate;
        this.systemAdminService = systemAdminService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String login(String username, String password) {
        try {
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("method", "user.login");
            requestBody.put("id", 1);
            requestBody.putNull("auth");

            ObjectNode params = requestBody.putObject("params");
            params.put("user", username);
            params.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    zabbixUrl, entity, String.class);

            JsonNode jsonResponse = objectMapper.readTree(response.getBody());

            if (jsonResponse.has("result")) {
                return jsonResponse.get("result").asText();
            } else if (jsonResponse.has("error")) {
                String errorMessage = jsonResponse.get("error").get("message").asText();
                if (errorMessage.equals("Invalid credentials")){
                    systemAdminService.archiveSystemAdmin(username);
                }
                throw new IllegalArgumentException("Zabbix login failed: " + errorMessage);
            }

            throw new IllegalStateException("Unexpected response format from Zabbix API");

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid JSON received from Zabbix", e);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new IllegalStateException("Zabbix API returned an error: " + e.getMessage(), e);
        } catch (ResourceAccessException e) {
            throw new IllegalStateException("Could not connect to Zabbix API", e);
        }
    }

    public JsonNode getProblemData(String authToken) {
        try {
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("method", "problem.get");
            requestBody.put("id", 2);
            requestBody.put("auth", authToken);

            ObjectNode params = requestBody.putObject("params");
            params.put("recent", "true");
            params.put("sortfield", "eventid");
            params.put("sortorder", "DESC");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    zabbixUrl, entity, String.class);

            return objectMapper.readTree(response.getBody());

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid JSON received from Zabbix", e);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new IllegalStateException("Zabbix API returned an error: " + e.getMessage(), e);
        } catch (ResourceAccessException e) {
            throw new IllegalStateException("Could not connect to Zabbix API", e);
        }
    }

    public JsonNode getUserInfo(String authToken) {
        try {
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("method", "user.get");
            requestBody.put("id", 3);
            requestBody.put("auth", authToken);

            ObjectNode params = requestBody.putObject("params");
            params.put("output", "extend");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    zabbixUrl, entity, String.class);

            return objectMapper.readTree(response.getBody());

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid JSON received from Zabbix", e);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new IllegalStateException("Zabbix API returned an error: " + e.getMessage(), e);
        } catch (ResourceAccessException e) {
            throw new IllegalStateException("Could not connect to Zabbix API", e);
        }
    }
}
