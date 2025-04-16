package kg.eldik.incidentmanagement.payload.request;

import java.util.Map;
import java.util.UUID;

public class UpdateIncidentRequest {
    public UpdateIncidentRequest(UUID id, Map<String, Object> updates) {
        this.id = id;
        this.updates = updates;
    }

    private UUID id;
    Map<String, Object> updates;
}
