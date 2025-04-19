package kg.eldik.incidentmanagement.service.impl;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.repository.SystemAdminRepository;
import kg.eldik.incidentmanagement.service.SystemAdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;

    public SystemAdminServiceImpl(SystemAdminRepository systemAdminRepository) {
        this.systemAdminRepository = systemAdminRepository;
    }


    @Override
    public Optional<SystemAdmin> createSystemAdmin(SystemAdminCreate systemAdminCreate) {
        return systemAdminRepository.createSystemAdminSQL(systemAdminCreate);
    }
}
