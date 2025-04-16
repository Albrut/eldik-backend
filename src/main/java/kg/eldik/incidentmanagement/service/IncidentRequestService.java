package kg.eldik.incidentmanagement.service;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;

public interface IncidentRequestService {
    public Iterable<IncidentRequest> findAllIncidentRequests();
}
