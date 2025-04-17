package kg.eldik.incidentmanagement.payload.request;

import java.util.UUID;

public record UserLoginRequest(
        UUID id,
        String username,
        String password
) {
}
