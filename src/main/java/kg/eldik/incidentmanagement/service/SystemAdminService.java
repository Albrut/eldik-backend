package kg.eldik.incidentmanagement.service;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;

public interface SystemAdminService {
    public SystemAdmin createSystemAdmin(SystemAdminCreate systemAdminCreaet);
}
