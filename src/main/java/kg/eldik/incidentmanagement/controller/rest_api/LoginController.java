package kg.eldik.incidentmanagement.controller.rest_api;

import kg.eldik.incidentmanagement.payload.request.UserLoginRequest;
import kg.eldik.incidentmanagement.service.ZabbixApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final ZabbixApiService zabbixApiService;

    public LoginController(ZabbixApiService zabbixApiService) {
        this.zabbixApiService = zabbixApiService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        String authToken = zabbixApiService.login(userLoginRequest.username(), userLoginRequest.password());
        return ResponseEntity.ok(authToken);
    }
}
