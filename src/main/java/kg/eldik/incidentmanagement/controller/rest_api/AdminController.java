package kg.eldik.incidentmanagement.controller.rest_api;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.payload.request.UpdateIncidentRequest;
import kg.eldik.incidentmanagement.repository.IncidentRequestRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    @PatchMapping("/update/incident")
    public ResponseEntity<?> updateIncident(UpdateIncidentRequest updateRequest) {
        return ResponseEntity.ok(incidentRequestService.updateIncidentRequest(updateRequest));
    }

    @PostMapping("/archive/incident")
    public ResponseEntity<?> archiveIncident(@RequestParam UUID id) {
        return ResponseEntity.ok(incidentRequestService.archiveIncident(id));
    }

    @PostMapping("/create/incident")
    public ResponseEntity<?> createIncident(@RequestBody IncidentRequestCreate incidentRequestCreate) {
        return ResponseEntity.ok(incidentRequestService.createIncident(incidentRequestCreate));
    }
}
