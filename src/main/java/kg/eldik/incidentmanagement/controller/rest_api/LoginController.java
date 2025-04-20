package kg.eldik.incidentmanagement.controller.rest_api;

import kg.eldik.incidentmanagement.facade.LoginFacade;
import kg.eldik.incidentmanagement.payload.request.UserLoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final LoginFacade loginFacade;
    public LoginController(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        Optional<String> authToken = loginFacade.login(userLoginRequest.username(), userLoginRequest.password());
        if(authToken.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(authToken.get());
    }
}
