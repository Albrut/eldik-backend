package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SystemAdminRepository extends CrudRepository<SystemAdmin, UUID> {
}
