package kg.eldik.incidentmanagement.controller.rest_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GayC {

    @GetMapping("/")
    public String hello() {
        return "Привет! 👋";
    }
}

