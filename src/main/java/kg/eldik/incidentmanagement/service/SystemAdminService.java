package kg.eldik.incidentmanagement.service;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;

import java.util.Optional;

public interface SystemAdminService {
    public Optional<SystemAdmin> createSystemAdmin(SystemAdminCreate systemAdminCreate);
    public boolean updateSystemAdmin(SystemAdmin systemAdmin);
    public boolean archiveSystemAdmin(String name, String lastName);
}
