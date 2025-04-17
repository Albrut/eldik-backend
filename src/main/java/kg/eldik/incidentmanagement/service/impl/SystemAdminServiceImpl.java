package kg.eldik.incidentmanagement.service.impl;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.repository.SystemAdminRepository;
import kg.eldik.incidentmanagement.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class SystemAdminServiceImpl implements SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;

    public SystemAdminServiceImpl(SystemAdminRepository systemAdminRepository) {
        this.systemAdminRepository = systemAdminRepository;
    }


    @Override
    public SystemAdmin createSystemAdmin(SystemAdminCreate systemAdminCreate) {
        return systemAdminRepository.createSystemAdminSQL(systemAdminCreate);
    }
}
