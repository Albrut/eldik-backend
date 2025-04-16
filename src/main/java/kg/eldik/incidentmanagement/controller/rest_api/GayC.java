package kg.eldik.incidentmanagement.controller.rest_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GayC {
    @Value("${spring.datasource.username}")
    private String da;

    @GetMapping("/")
    public String hello() {
        return da;
    }
}

