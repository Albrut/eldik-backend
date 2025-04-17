package kg.eldik.incidentmanagement.repository.impl;

import kg.eldik.incidentmanagement.models.entity.IncidentRequest;
import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.RankEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.repository.SystemAdminRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SystemAdminRepositoryImpl implements SystemAdminRepository {
    private final JdbcTemplate jdbcTemplate;

    public SystemAdminRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SystemAdmin createSystemAdminSQL(SystemAdminCreate systemAdminCreate) {
        String sql = "INSERT INTO system_admin (id, first_name, last_name, patronymic, rank, is_active) VALUES (?, ?, ?, ?, ?::rank_enum, ?) RETURNING *";


        UUID id = UUID.randomUUID();
        return jdbcTemplate.queryForObject(sql, new Object[]{
                id,
                systemAdminCreate.getFirstName(),
                systemAdminCreate.getLastName(),
                systemAdminCreate.getPatronymic(),
                systemAdminCreate.getRank().name(),
                systemAdminCreate.getIs_active(),
        }, (rs, rowNum) -> {
            SystemAdmin systemAdmin = new SystemAdmin();
            systemAdmin.setId(UUID.fromString(rs.getString("id")));
            systemAdmin.setFirstName(rs.getString("first_name"));
            systemAdmin.setLastName(rs.getString("last_name"));
            systemAdmin.setPatronymic(rs.getString("patronymic"));
            systemAdmin.setRank(RankEnum.valueOf(rs.getString("rank")));
            systemAdmin.setIs_active(rs.getBoolean("is_active"));
            return systemAdmin;
        });
    }
}
