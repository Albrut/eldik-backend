package kg.eldik.incidentmanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;

import java.util.Date;
import java.util.UUID;

public record IncidentRequestDTO(
        String usedSource,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        Date incidentDate,
        String incident_description,
        ImportanceEnum importance,
        UUID workerId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        Date closeDate,
        String solution,
        String note,
        StatusEnum status
){}
