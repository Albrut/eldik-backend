package kg.eldik.incidentmanagement.controller.rest_api;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.payload.request.UpdateIncidentRequest;
import kg.eldik.incidentmanagement.repository.CreateIncidentRepository;
import kg.eldik.incidentmanagement.repository.IncidentRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import kg.eldik.incidentmanagement.service.SystemAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final IncidentRepository incidentRepository;
    private final IncidentRequestService incidentRequestService;
    private final CreateIncidentRepository createIncidentRepository;
    private final SystemAdminService systemAdminService;

    public AdminController(IncidentRepository incidentRepository, IncidentRequestService incidentRequestService, CreateIncidentRepository createIncidentRepository, SystemAdminService systemAdminService) {
        this.incidentRepository = incidentRepository;
        this.incidentRequestService = incidentRequestService;
        this.createIncidentRepository = createIncidentRepository;
        this.systemAdminService = systemAdminService;
    }


    @GetMapping("/get/all/incidents")
    public ResponseEntity<Iterable<IncidentRequest>> getAllIncidents() {
        return ResponseEntity.ok(incidentRequestService.findAllIncidentRequests());
    }

    @PatchMapping("/update/incident")
    public ResponseEntity<IncidentRequest> updateIncident( IncidentRequestCreate updateDto) {
        return ResponseEntity.ok(incidentRepository.updateIncidentRequestSQL(updateDto));
    }

    @PostMapping("/archive/incident")
    public ResponseEntity<IncidentRequest> archiveIncident(@RequestParam UUID id) {
        return ResponseEntity.ok(incidentRequestService.archiveIncident(id));
    }

    @PostMapping("/create/incident")
    public ResponseEntity<IncidentRequest> createIncident(@RequestBody IncidentRequestCreate incidentRequestCreate) {
        return ResponseEntity.ok(createIncidentRepository.createIncidentRequestSQL(incidentRequestCreate));
    }

    @PostMapping("/create/system_admin")
    public ResponseEntity<SystemAdmin> createSystemAdmin(@RequestBody SystemAdminCreate systemAdminCreate) {
        return ResponseEntity.ok(systemAdminService.createSystemAdmin(systemAdminCreate));
    }
}
