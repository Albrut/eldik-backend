package kg.eldik.incidentmanagement.facade;

import java.util.Optional;

public interface LoginFacade {
    Optional<String> login(String username, String password);
}