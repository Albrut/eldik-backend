package kg.eldik.incidentmanagement.service;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.payload.request.UpdateIncidentRequest;

import java.util.UUID;

public interface IncidentRequestService {
    public Iterable<IncidentRequest> findAllIncidentRequests();
    public IncidentRequest findIncidentRequestById(UUID id);
    public IncidentRequest updateIncidentRequest(UpdateIncidentRequest updateRequest);
}
