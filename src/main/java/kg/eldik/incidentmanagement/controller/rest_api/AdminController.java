package kg.eldik.incidentmanagement.controller.rest_api;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final IncidentRequestRepository incidentRequestRepository;
    @GetMapping("/get/all/incidents")
    public ResponseEntity<?> getAllIncidents() {
        Optional<List<IncidentRequest>> incidents = Optional.ofNullable(null);
        return ResponseEntity.ok(incidents);
    }
}
