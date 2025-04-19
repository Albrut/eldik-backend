package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;

public interface CreateIncidentRepository {
    IncidentRequest createIncidentRequestSQL(IncidentRequest IncidentRequest);
}
