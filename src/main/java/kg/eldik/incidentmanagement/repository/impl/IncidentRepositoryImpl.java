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

        return jdbcTemplate.queryForObject(sql,
                new Object[]{
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
                },
                (rs, rowNum) -> {
                    IncidentRequest ir = new IncidentRequest();
                    ir.setId(UUID.fromString(rs.getString("id")));
                    ir.setUsed_sources(rs.getString("used_sources"));
                    ir.setIncident_date(rs.getTimestamp("incident_date"));
                    ir.setIncident_description(rs.getString("incident_description"));
                    ir.setImportance(ImportanceEnum.valueOf(rs.getString("importance")));
                    ir.setWorker_id(UUID.fromString(rs.getString("worker_id")));
                    ir.setStatus(StatusEnum.valueOf(rs.getString("status")));
                    ir.setClose_date(rs.getTimestamp("close_date"));
                    ir.setSolution(rs.getString("solution"));
                    ir.setNote(rs.getString("note"));
                    return ir;
                }
        );

    }
}