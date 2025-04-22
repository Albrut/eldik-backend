package kg.eldik.incidentmanagement.payload.request;



public record UserLoginRequest(
        String username,
        String password
) {
}
