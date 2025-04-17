package kg.eldik.incidentmanagement.repository.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.repository.CreateIncidentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CreateIncidentRepositoryImpl implements CreateIncidentRepository {
    private final JdbcTemplate jdbcTemplate;

    public CreateIncidentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public IncidentRequest createIncidentRequestSQL(IncidentRequestCreate createDto) {
        String sql = "INSERT INTO incident_request " +
                "(id, used_sources, incident_date, incident_description, importance, worker_id, status, close_date, solution, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *";

        UUID id = UUID.randomUUID();

        return jdbcTemplate.queryForObject(sql, new Object[]{
                id,
                createDto.getUsed_sources(),
                createDto.getIncident_date(),
                createDto.getIncident_description(),
                createDto.getImportance().name(),
                createDto.getWorker_id(),
                createDto.getStatus().name(),
                createDto.getClose_date(),
                createDto.getSolution(),
                createDto.getNote()
        }, (rs, rowNum) -> {
            IncidentRequest incident = new IncidentRequest();
            incident.setId(UUID.fromString(rs.getString("id")));
            incident.setUsed_sources(rs.getString("used_sources"));
            incident.setIncident_date(rs.getDate("incident_date"));
            incident.setIncident_description(rs.getString("incident_description"));
            incident.setImportance(ImportanceEnum.valueOf(rs.getString("importance")));
            incident.setWorker_id(UUID.fromString(rs.getString("worker_id")));
            incident.setStatus(StatusEnum.valueOf(rs.getString("status")));
            incident.setClose_date(rs.getDate("close_date"));
            incident.setSolution(rs.getString("solution"));
            incident.setNote(rs.getString("note"));
            return incident;
        });
    }
}
