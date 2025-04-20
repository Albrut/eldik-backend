package kg.eldik.incidentmanagement.payload.request;


import kg.eldik.incidentmanagement.models.enums.RoleEnum;

public class SystemAdminCreate {
    private String username;
    private String firstName;
    private String lastName;
    private boolean is_active;
    private RoleEnum role;

    public boolean isIs_active() {
        return is_active;
    }

    @Override
    public String toString() {
        return "SystemAdminCreate{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", is_active=" + is_active +
                ", role=" + role +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

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

}
