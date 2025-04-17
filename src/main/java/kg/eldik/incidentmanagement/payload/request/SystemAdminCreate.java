package kg.eldik.incidentmanagement.payload.request;

import kg.eldik.incidentmanagement.models.enums.RankEnum;

public class SystemAdminCreate {
    private String firstName;
    private String lastName;
    private String patronymic;
    private RankEnum rank;
    private boolean is_active;

    public boolean getIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
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

    @Override
    public String toString() {
        return "SystemAdminCreate{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", isActive='" + is_active + '\'' +
                ", rank=" + rank +
                '}';
    }
}
