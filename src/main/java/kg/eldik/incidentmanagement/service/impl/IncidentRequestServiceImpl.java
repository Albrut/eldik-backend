package kg.eldik.incidentmanagement.service.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.payload.request.UpdateIncidentRequest;
import kg.eldik.incidentmanagement.repository.IncidentRequestRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class IncidentRequestServiceImpl implements IncidentRequestService {
    private final IncidentRequestRepository incidentRequestRepository;

    public IncidentRequestServiceImpl(IncidentRequestRepository incidentRequestRepository) {
        this.incidentRequestRepository = incidentRequestRepository;
    }

    @Override
    public Iterable<IncidentRequest> findAllIncidentRequests() {
        return incidentRequestRepository.findAll();
    }

    @Override
    public IncidentRequest findIncidentRequestById(UUID id) {
        return incidentRequestRepository.findById(id).orElse(null);
    }

    @Override
    public IncidentRequest updateIncidentRequest(UpdateIncidentRequest updateRequest) {
        IncidentRequest incidentRequest = incidentRequestRepository.findById(updateRequest.getId()).orElse(null);

        applyUpdates(incidentRequest, updateRequest);

        return incidentRequestRepository.save(incidentRequest);
    }

    @Override
    public IncidentRequest archiveIncident(UUID id) {
        IncidentRequest incidentRequest = incidentRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Incident not found"));
        incidentRequest.setStatus(StatusEnum.archived);
        return incidentRequestRepository.save(incidentRequest);
    }

    @Override
    @Transactional
    public IncidentRequest createIncident(IncidentRequestCreate incidentRequestCreate) {
        IncidentRequest incidentRequest = new IncidentRequest();

        incidentRequest.setUsed_sources(incidentRequestCreate.getUsed_sources());
        incidentRequest.setIncident_date(incidentRequestCreate.getIncident_date());
        incidentRequest.setIncident_description(incidentRequestCreate.getIncident_description());
        incidentRequest.setImportance(incidentRequestCreate.getImportance());
        incidentRequest.setWorker_id(incidentRequestCreate.getWorker_id());
        incidentRequest.setStatus(incidentRequestCreate.getStatus());
        incidentRequest.setClose_date(incidentRequestCreate.getClose_date());
        incidentRequest.setSolution(incidentRequestCreate.getSolution());
        incidentRequest.setNote(incidentRequestCreate.getNote());

        return incidentRequestRepository.save(incidentRequest);
    }


    public void applyUpdates(IncidentRequest entity, UpdateIncidentRequest request) {
        if (request.getUsed_sources() != null) {
            entity.setUsed_sources(request.getUsed_sources());
        }
        if (request.getIncident_date() != null) {
            entity.setIncident_date(request.getIncident_date());
        }
        if (request.getIncident_description() != null) {
            entity.setIncident_description(request.getIncident_description());
        }
        if (request.getImportance() != null) {
            entity.setImportance(request.getImportance());
        }
        if (request.getWorker_id() != null) {
            entity.setWorker_id(request.getWorker_id());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (request.getClose_date() != null) {
            entity.setClose_date(request.getClose_date());
        }
        if (request.getSolution() != null) {
            entity.setSolution(request.getSolution());
        }
        if (request.getNote() != null) {
            entity.setNote(request.getNote());
        }
    }

}
