package kg.eldik.incidentmanagement.facade.impl;

import com.fasterxml.jackson.databind.JsonNode;
import kg.eldik.incidentmanagement.facade.LoginFacade;
import kg.eldik.incidentmanagement.models.enums.RoleEnum;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.repository.SystemAdminRepository;
import kg.eldik.incidentmanagement.service.ZabbixApiService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginFacadeImpl implements LoginFacade {
    private final SystemAdminRepository systemAdminRepository;
    private final ZabbixApiService zabbixApiService;

    public LoginFacadeImpl(SystemAdminRepository systemAdminRepository, ZabbixApiService zabbixApiService) {
        this.systemAdminRepository = systemAdminRepository;
        this.zabbixApiService = zabbixApiService;
    }

    @Override
    public Optional<String> login(String username, String password) {
       Optional<String> authToken =  Optional.ofNullable(zabbixApiService.login(username, password));
        if (authToken.isEmpty()) {
            return Optional.empty();
        }
        JsonNode userInfo = zabbixApiService.getUserInfo(authToken.get());
        SystemAdminCreate systemAdminCreate = new SystemAdminCreate();
        userInfo = userInfo.get("result").get(0);
        systemAdminCreate.setIs_active(userInfo.get("is_working").asBoolean());
        systemAdminCreate.setFirstName(userInfo.get("name").asText());
        systemAdminCreate.setLastName(userInfo.get("surname").asText());
        systemAdminCreate.setRole(userInfo.get("alias").asText().equals("admin") ? RoleEnum.ADMIN : RoleEnum.USER);
        systemAdminCreate.setUsername(username);
        systemAdminRepository.createSystemAdminSQL(systemAdminCreate);
        return authToken;
    }
}
