package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.payload.response.SystemAdminResponse;

import java.util.List;
import java.util.Optional;

public interface SystemAdminRepository {
    Optional<SystemAdmin> createSystemAdminSQL(SystemAdminCreate systemAdminCreate);
    boolean updateSystemAdmin(SystemAdmin systemAdmin);
    boolean archiveSystemAdmin(String username);
    List<SystemAdminResponse> getAllSystemAdmins();
}
