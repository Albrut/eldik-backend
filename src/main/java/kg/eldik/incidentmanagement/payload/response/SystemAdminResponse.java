package kg.eldik.incidentmanagement.payload.response;

import java.util.UUID;

public record SystemAdminResponse(
        UUID id,
        String firstName,
        String lastName,
        UUID incidentId,
        boolean isActive
) {
}
