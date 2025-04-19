package kg.eldik.incidentmanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;

import java.util.Date;
import java.util.UUID;

public record IncidentRequest(
        String usedSource,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        Date incident_date,
        String incident_description,
        ImportanceEnum importance,
        UUID workerId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
        Date closeDate,
        String solution,
        String note
){}
