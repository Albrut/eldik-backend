package kg.eldik.incidentmanagement.models.entity;//package kg.eldik.incidentmanagement.models.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table(name = "incident_request")
public class IncidentRequest {
    @Id
    private UUID id;
    private String used_sources;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date incident_date;

    private String incident_description;
    @Column("importance")
    private ImportanceEnum importance;
    private UUID worker_id;
    private StatusEnum status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date close_date;

    private String solution;
    private String note;

    @Override
    public String toString() {
        return "IncidentRequest{" +
                "id=" + id +
                ", used_sources='" + used_sources + '\'' +
                ", incident_date=" + incident_date +
                ", incident_description='" + incident_description + '\'' +
                ", importance=" + importance +
                ", worker_id=" + worker_id +
                ", status=" + status +
                ", close_date=" + close_date +
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

    public String getUsed_sources() {
        return used_sources;
    }

    public void setUsed_sources(String used_sources) {
        this.used_sources = used_sources;
    }

    public Date getIncident_date() {
        return incident_date;
    }

    public void setIncident_date(Date incident_date) {
        this.incident_date = incident_date;
    }

    public String getIncident_description() {
        return incident_description;
    }

    public void setIncident_description(String incident_description) {
        this.incident_description = incident_description;
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

    public Date getClose_date() {
        return close_date;
    }

    public void setClose_date(Date close_date) {
        this.close_date = close_date;
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
