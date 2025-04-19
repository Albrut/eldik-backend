package kg.eldik.incidentmanagement.repository.impl;

import kg.eldik.incidentmanagement.models.entity.SystemAdmin;
import kg.eldik.incidentmanagement.payload.request.SystemAdminCreate;
import kg.eldik.incidentmanagement.repository.SystemAdminRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SystemAdminRepositoryImpl implements SystemAdminRepository {
    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_QUERY_CHECK_EXIST_USER = "SELECT EXISTS(SELECT 1 FROM system_admin WHERE first_name=? and last_name=?)";
    private final static String SQL_QUERY_TO_CREATE_ADMIN = "INSERT INTO system_admin (id, first_name, last_name, is_active) " +
            "VALUES (?, ?, ?, ?) RETURNING *";

    public SystemAdminRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<SystemAdmin> createSystemAdminSQL(SystemAdminCreate systemAdminCreate) {

        if ( checkSystemAdminIsExist(systemAdminCreate.getFirstName(), systemAdminCreate.getLastName())) {
            UUID id = UUID.randomUUID();
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_QUERY_TO_CREATE_ADMIN, new Object[]{
                    id,
                    systemAdminCreate.getFirstName(),
                    systemAdminCreate.getLastName(),
                    systemAdminCreate.getIs_active(),
            }, (rs, rowNum) -> {
                SystemAdmin systemAdmin = new SystemAdmin();
                systemAdmin.setId(UUID.fromString(rs.getString("id")));
                systemAdmin.setFirstName(rs.getString("first_name"));
                systemAdmin.setLastName(rs.getString("last_name"));
                systemAdmin.setIs_active(rs.getBoolean("is_active"));
                return systemAdmin;
            }));
        } else {
            return Optional.empty();
        }
    }

    private Boolean checkSystemAdminIsExist(String firstName, String lastName) {
        return jdbcTemplate.queryForObject(SQL_QUERY_CHECK_EXIST_USER, Boolean.class, firstName, lastName);
    }
}
