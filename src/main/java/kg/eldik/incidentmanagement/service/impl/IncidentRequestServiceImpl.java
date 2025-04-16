package kg.eldik.incidentmanagement.service.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.repository.IncidentRequestRepository;
import kg.eldik.incidentmanagement.service.IncidentRequestService;
import org.springframework.stereotype.Service;

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
}
