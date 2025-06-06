package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IncidentRequestRepository extends CrudRepository<IncidentRequest, UUID> {
}
