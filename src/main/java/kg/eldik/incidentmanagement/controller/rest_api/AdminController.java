package kg.eldik.incidentmanagement.controller.rest_api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kg.eldik.incidentmanagement.facade.AdminFacade;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestDTO;
import jakarta.validation.Valid;
import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.payload.response.SystemAdminResponse;
import kg.eldik.incidentmanagement.payload.response.ZabbixProblemsResponse;
import kg.eldik.incidentmanagement.repository.CreateIncidentRepository;
import kg.eldik.incidentmanagement.repository.IncidentRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import kg.eldik.incidentmanagement.service.SystemAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final IncidentRepository incidentRepository;
    private final IncidentRequestService incidentRequestService;
    private final CreateIncidentRepository createIncidentRepository;
    private final SystemAdminService systemAdminService;
    private final AdminFacade adminFacade;


    public AdminController(IncidentRepository incidentRepository,
                           IncidentRequestService incidentRequestService, CreateIncidentRepository createIncidentRepository,
                           SystemAdminService systemAdminService, AdminFacade adminFacade) {
        this.incidentRepository = incidentRepository;
        this.incidentRequestService = incidentRequestService;
        this.createIncidentRepository = createIncidentRepository;
        this.systemAdminService = systemAdminService;
        this.adminFacade = adminFacade;
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @GetMapping("/get/all/incidents")
    public ResponseEntity<Iterable<IncidentRequest>> getAllIncidents() {
        return ResponseEntity.ok(incidentRequestService.findAllIncidentRequests());
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @GetMapping("/get/all/system_admins")
    public ResponseEntity<List<SystemAdminResponse>> getAllSystemAdmins() {
        List<SystemAdminResponse> allAdminsList = systemAdminService.getAllSystemAdmins();
        if (allAdminsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allAdminsList);
    }


    @SecurityRequirement(name = "X-Auth-Token")
    @GetMapping("/get/all/incidents/zabbix/")
    public ResponseEntity<List<ZabbixProblemsResponse>> getAllIncidentsZabbix(@RequestHeader("X-Auth-Token") String token) {
        if(adminFacade.isAdmin()){
            return ResponseEntity.ok(adminFacade.getAllProblemsZabbix());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @PatchMapping("/update/incident")
    public ResponseEntity<IncidentRequest> updateIncident(
            @RequestBody @Valid IncidentRequestCreate updateDto, @RequestHeader("X-Auth-Token") String token) {
        if(!adminFacade.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(
                incidentRepository.updateIncidentRequestSQL(updateDto)
        );
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @PostMapping("/archive/incident")
    public ResponseEntity<IncidentRequest> archiveIncident(@RequestParam UUID id, @RequestHeader("X-Auth-Token") String token) {
        if(!adminFacade.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(incidentRepository.archiveIncidentRequestSQL(id));
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @PostMapping("/create/incident")
    public ResponseEntity<IncidentRequest> createIncident(@RequestBody IncidentRequestDTO incidentRequestDTO, @RequestHeader("X-Auth-Token") String token) {
        if(!adminFacade.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createIncidentRepository.createIncidentRequestSQL(incidentRequestDTO));
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @PostMapping("/create/system_admin")
    public ResponseEntity<String> createSystemAdmin(@RequestBody SystemAdminCreate systemAdminCreate, @RequestHeader("X-Auth-Token") String token) {
        if(!adminFacade.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
       if (systemAdminService.createSystemAdmin(systemAdminCreate).isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist");
       }else {
           return ResponseEntity.status(HttpStatus.CREATED).body("System admin created");
       }
    }

    @SecurityRequirement(name = "X-Auth-Token")
    @PatchMapping("/update/system_admin")
    public ResponseEntity<Boolean> updateSystemAdmin(@RequestBody SystemAdmin systemAdminCreate, @RequestHeader("X-Auth-Token") String token) {
        if(!adminFacade.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(systemAdminService.updateSystemAdmin(systemAdminCreate));
    }

}
