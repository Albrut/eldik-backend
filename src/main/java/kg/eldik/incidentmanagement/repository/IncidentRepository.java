package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;

public interface IncidentRepository {
    IncidentRequest updateIncidentRequestSQL(IncidentRequestCreate incidentRequestCreate);

}
