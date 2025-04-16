package kg.eldik.incidentmanagement.service.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.payload.request.UpdateIncidentRequest;
import kg.eldik.incidentmanagement.repository.IncidentRequestRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
