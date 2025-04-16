package kg.eldik.incidentmanagement.controller.rest_api;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.repository.IncidentRequestRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final IncidentRequestService incidentRequestService;

    public AdminController(IncidentRequestService incidentRequestService) {
        this.incidentRequestService = incidentRequestService;
    }

    @GetMapping("/get/all/incidents")
    public ResponseEntity<?> getAllIncidents() {
        return ResponseEntity.ok(incidentRequestService.findAllIncidentRequests());
    }
}
