package kg.eldik.incidentmanagement.payload.response;

public record ZabbixUserInfo (
        String first_name,
        String lastName,
        boolean isActive
){
}
