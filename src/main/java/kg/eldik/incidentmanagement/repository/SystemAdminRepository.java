package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;

import java.util.Optional;

public interface SystemAdminRepository {
    Optional<SystemAdmin> createSystemAdminSQL(SystemAdminCreate systemAdminCreate);
}
