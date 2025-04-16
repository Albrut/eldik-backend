package kg.eldik.incidentmanagement.models.entity;//package kg.eldik.incidentmanagement.models.entity;


import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table(name = "incident_request")
public class IncidentRequest {
    @Id
    private UUID id;
    private String usedSources;
    private Date incidentDate;
    private String incidentDescription;
    private ImportanceEnum importance;
    private UUID worker_id;
    private StatusEnum status;
    private Date closeDate;
    private String solution;
    private String note;

    @Override
    public String toString() {
        return "IncidentRequest{" +
                "id=" + id +
                ", usedSources='" + usedSources + '\'' +
                ", incidentDate=" + incidentDate +
                ", incidentDescription='" + incidentDescription + '\'' +
                ", importance=" + importance +
                ", system_admin_id=" + worker_id+
                ", status=" + status +
                ", closeDate=" + closeDate +
                ", solution='" + solution + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsedSources() {
        return usedSources;
    }

    public void setUsedSources(String usedSources) {
        this.usedSources = usedSources;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public ImportanceEnum getImportance() {
        return importance;
    }

    public void setImportance(ImportanceEnum importance) {
        this.importance = importance;
    }

    public UUID getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(UUID worker_id) {
        this.worker_id = worker_id;
    }



    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
