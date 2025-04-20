package kg.eldik.incidentmanagement.repository.impl;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.payload.response.SystemAdminResponse;
import kg.eldik.incidentmanagement.repository.SystemAdminRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SystemAdminRepositoryImpl implements SystemAdminRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_QUERY_CHECK_EXIST_USER =
            "SELECT EXISTS(SELECT 1 FROM system_admin WHERE username = ?)";
    private static final String SQL_QUERY_TO_CREATE_ADMIN =
            "INSERT INTO system_admin (id, first_name, last_name, is_active, role, username) " +
                    "VALUES (?, ?, ?, ?, ?::user_role, ?) RETURNING *";
    private static final String SQL_QUERY_TO_UPDATE_ADMIN =
            "UPDATE system_admin SET first_name = ?, last_name = ?, is_active = ?, role = ?::user_role WHERE id = ?";
    private static final String SQL_QUERY_TO_ARCHIVE_ADMIN =
            "UPDATE system_admin SET is_active = ? WHERE username = ?";
    private static final String SQL_QUERY_GET_ALL_ADMINS =
            "SELECT a.id, a.first_name, a.last_name, ir.id AS incident_id, a.is_active " +
                    "FROM system_admin a " +
                    "LEFT JOIN incident_request ir ON a.id = ir.worker_id";

    public SystemAdminRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Optional<SystemAdmin> createSystemAdminSQL(SystemAdminCreate systemAdminCreate) {
        if (!checkSystemAdminIsExist(systemAdminCreate.getUsername())) {
            UUID id = UUID.randomUUID();
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            SQL_QUERY_TO_CREATE_ADMIN,
                            new Object[]{
                                    id,
                                    systemAdminCreate.getFirstName(),
                                    systemAdminCreate.getLastName(),
                                    systemAdminCreate.getIs_active(),
                                    systemAdminCreate.getRole().toString().toLowerCase(),
                                    systemAdminCreate.getUsername()
                            },
                            (rs, rowNum) -> {
                                SystemAdmin systemAdmin = new SystemAdmin();
                                systemAdmin.setId(UUID.fromString(rs.getString("id")));
                                systemAdmin.setFirstName(rs.getString("first_name"));
                                systemAdmin.setLastName(rs.getString("last_name"));
                                systemAdmin.setIs_active(rs.getBoolean("is_active"));
                                return systemAdmin;
                            }
                    )
            );
        } else {
            return Optional.empty();
        }
    }

    private boolean checkSystemAdminIsExist(String username) {
        Boolean exists = jdbcTemplate.queryForObject(SQL_QUERY_CHECK_EXIST_USER, Boolean.class, username);
        return exists != null && exists;
    }

    @Override
    @Transactional
    public boolean updateSystemAdmin(SystemAdmin systemAdmin) {
        int rowsUpdated = jdbcTemplate.update(
                SQL_QUERY_TO_UPDATE_ADMIN,
                systemAdmin.getFirstName(),
                systemAdmin.getLastName(),
                systemAdmin.getIs_active(),
                systemAdmin.getRole().toString().toLowerCase(),
                systemAdmin.getId()
        );
        return rowsUpdated > 0;
    }

    @Override
    @Transactional
    public boolean archiveSystemAdmin(String username) {
        if (checkSystemAdminIsExist(username)) {
            jdbcTemplate.update(SQL_QUERY_TO_ARCHIVE_ADMIN, false, username);
            return true;
        }
        return false;
    }

    @Override
    public List<SystemAdminResponse> getAllSystemAdmins() {
        return jdbcTemplate.query(
                SQL_QUERY_GET_ALL_ADMINS,
                (rs, rowNum) -> new SystemAdminResponse(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getObject("incident_id", UUID.class),
                        rs.getBoolean("is_active")
                )
        );
    }
}
