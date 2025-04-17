package kg.eldik.incidentmanagement.repository;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import org.springframework.stereotype.Repository;

public interface SystemAdminRepository {
    SystemAdmin createSystemAdminSQL(SystemAdminCreate systemAdminCreate);
}
