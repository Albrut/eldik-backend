package kg.eldik.incidentmanagement.repository.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.repository.IncidentRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IncidentRequestRepositoryImpl implements IncidentRequestRepository {
    private final IncidentRequestRepository incidentRequestRepository;

    public IncidentRequestRepositoryImpl(IncidentRequestRepository incidentRequestRepository) {
        this.incidentRequestRepository = incidentRequestRepository;
    }

    @Override
    public List<IncidentRequest> findAllIncidentRequests() {
        return incidentRequestRepository.findAllIncidentRequests();
    }

    @Override
    public <S extends IncidentRequest> S save(S entity) {
        return null;
    }

    @Override
    public <S extends IncidentRequest> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<IncidentRequest> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Iterable<IncidentRequest> findAll() {
        return null;
    }

    @Override
    public Iterable<IncidentRequest> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(IncidentRequest entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends IncidentRequest> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
