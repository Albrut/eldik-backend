package kg.eldik.incidentmanagement.service;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.payload.request.UpdateIncidentRequest;

import java.util.UUID;

public interface IncidentRequestService {
     Iterable<IncidentRequest> findAllIncidentRequests();
     IncidentRequest findIncidentRequestById(UUID id);
     IncidentRequest updateIncidentRequest(UpdateIncidentRequest updateRequest);
     IncidentRequest archiveIncident(UUID id);
     IncidentRequest createIncident(IncidentRequestCreate incidentRequestCreate);
}
