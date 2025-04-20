package kg.eldik.incidentmanagement.service;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.payload.response.SystemAdminResponse;

import java.util.List;
import java.util.Optional;

public interface SystemAdminService {
     Optional<SystemAdmin> createSystemAdmin(SystemAdminCreate systemAdminCreate);
     boolean updateSystemAdmin(SystemAdmin systemAdmin);
     boolean archiveSystemAdmin(String username);
     List<SystemAdminResponse> getAllSystemAdmins();

}
