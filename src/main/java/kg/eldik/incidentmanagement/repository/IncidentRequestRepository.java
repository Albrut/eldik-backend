package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IncidentRequestRepository extends CrudRepository<IncidentRequest, UUID> {
    List<IncidentRequest> findAllIncidentRequests();
}
