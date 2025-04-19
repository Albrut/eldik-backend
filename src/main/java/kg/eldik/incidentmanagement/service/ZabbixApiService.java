package kg.eldik.incidentmanagement.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface ZabbixApiService {
    String login(String username, String password);
    JsonNode getProblemData(String authToken);
    JsonNode getUserInfo(String authToken);
}
