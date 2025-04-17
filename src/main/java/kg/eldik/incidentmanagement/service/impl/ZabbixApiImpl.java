package kg.eldik.incidentmanagement.service.impl;


import kg.eldik.incidentmanagement.service.ZabbixApiLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ZabbixApiImpl implements ZabbixApiLogin {
    private final RestTemplate restTemplate;
    @Value("${zabbix.url}")
    private String urlZabbix;
    private final String jsonRequestForLogin = """
            {
            "jsonrpc": "2.0",
            "method": "user.login",
            "params": {
            "user": "Admin",
            "password": "zabbix"
            },
            "id": 1,
            "auth": null
            }""";
    public ZabbixApiImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String login(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonRequestForLogin, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(urlZabbix + "/api", entity, String.class);
        return response.getBody();
    }




}
