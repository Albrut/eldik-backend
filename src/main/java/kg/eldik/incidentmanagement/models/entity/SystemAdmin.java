package kg.eldik.incidentmanagement.models.entity;//package kg.eldik.incidentmanagement.models.entity;

import kg.eldik.incidentmanagement.models.enums.ImportanceEnum;
import kg.eldik.incidentmanagement.models.enums.RankEnum;
import kg.eldik.incidentmanagement.models.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;


@Table(name = "system_admin")
public class SystemAdmin {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private RankEnum rank;

    @Override
    public String toString() {
        return "SystemAdmin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", rank=" + rank +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public RankEnum getRank() {
        return rank;
    }

    public void setRank(RankEnum rank) {
        this.rank = rank;
    }
}
