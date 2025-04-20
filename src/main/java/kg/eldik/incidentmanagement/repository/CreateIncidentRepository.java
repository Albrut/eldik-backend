package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestDTO;

public interface CreateIncidentRepository {
    IncidentRequest createIncidentRequestSQL(IncidentRequestDTO IncidentRequest);
}
