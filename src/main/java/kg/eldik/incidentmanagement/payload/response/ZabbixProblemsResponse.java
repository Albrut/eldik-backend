package kg.eldik.incidentmanagement.payload.response;

import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;

import java.time.LocalDate;
import java.util.List;

public record ZabbixProblemsResponse (
        LocalDate dateOfTheProblem,
        String description,
        String notes,
        Integer number,
        ImportanceEnum importance,
        List<String> resources,
        String responsible,
        String solution,
        StatusEnum status
){

}
