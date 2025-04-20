package kg.eldik.incidentmanagement.controller.rest_api;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestDTO;
import jakarta.validation.Valid;
import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.payload.response.SystemAdminResponse;
import kg.eldik.incidentmanagement.repository.CreateIncidentRepository;
import kg.eldik.incidentmanagement.repository.IncidentRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import kg.eldik.incidentmanagement.service.SystemAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
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

    @GetMapping("/get/all/system_admins")
    public ResponseEntity<List<SystemAdminResponse>> getAllSystemAdmins() {
        List<SystemAdminResponse> allAdminsList = systemAdminService.getAllSystemAdmins();
        if (allAdminsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allAdminsList);
    }

    @PatchMapping("/update/incident")
    public ResponseEntity<IncidentRequest> updateIncident(
            @RequestBody @Valid IncidentRequestCreate updateDto) {
        return ResponseEntity.ok(
                incidentRepository.updateIncidentRequestSQL(updateDto)
        );
    }


    @PostMapping("/archive/incident")
    public ResponseEntity<IncidentRequest> archiveIncident(@RequestParam UUID id) {
        return ResponseEntity.ok(incidentRepository.archiveIncidentRequestSQL(id));
    }

    @PostMapping("/create/incident")
    public ResponseEntity<IncidentRequest> createIncident(@RequestBody IncidentRequestDTO incidentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createIncidentRepository.createIncidentRequestSQL(incidentRequestDTO));
    }

    @PostMapping("/create/system_admin")
    public ResponseEntity<String> createSystemAdmin(@RequestBody SystemAdminCreate systemAdminCreate) {
       if (systemAdminService.createSystemAdmin(systemAdminCreate).isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist");
       }else {
           return ResponseEntity.status(HttpStatus.CREATED).body("System admin created");
       }
    }
    @PatchMapping("/update/system_admin")
    public ResponseEntity<Boolean> updateSystemAdmin(@RequestBody SystemAdmin systemAdminCreate) {
        return ResponseEntity.ok(systemAdminService.updateSystemAdmin(systemAdminCreate));
    }
}
