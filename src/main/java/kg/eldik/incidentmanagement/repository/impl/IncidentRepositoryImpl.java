package kg.eldik.incidentmanagement.repository.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import kg.eldik.incidentmanagement.payload.request.IncidentRequestCreate;
import kg.eldik.incidentmanagement.repository.IncidentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IncidentRepositoryImpl implements IncidentRepository {
    private final JdbcTemplate jdbcTemplate;

    public IncidentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public IncidentRequest updateIncidentRequestSQL(IncidentRequestCreate updateDto) {
        String sql = "UPDATE incident_request SET " +
                "used_sources = ?, " +
                "incident_date = ?, " +
                "incident_description = ?, " +
                "importance = ?::importance_enum, " +
                "worker_id = ?, " +
                "status = ?::status_enum, " +
                "close_date = ?, " +
                "solution = ?, " +
                "note = ? " +
                "WHERE id = ? RETURNING *";

        return jdbcTemplate.queryForObject(sql, new Object[]{
                updateDto.getUsed_sources(),
                updateDto.getIncident_date(),
                updateDto.getIncident_description(),
                updateDto.getImportance().name(),
                updateDto.getWorker_id(),
                updateDto.getStatus().name(),
                updateDto.getClose_date(),
                updateDto.getSolution(),
                updateDto.getNote(),
                updateDto.getId()
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