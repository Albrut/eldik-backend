package kg.eldik.incidentmanagement.facade;

import kg.eldik.incidentmanagement.payload.response.ZabbixProblemsResponse;

import java.util.List;

public interface AdminFacade {
    List<ZabbixProblemsResponse> getAllProblemsZabbix();
    boolean isAdmin();
}
