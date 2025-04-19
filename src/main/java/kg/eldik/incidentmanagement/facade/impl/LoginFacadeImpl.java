package kg.eldik.incidentmanagement.facade.impl;

import kg.eldik.incidentmanagement.facade.LoginFacade;
import org.springframework.stereotype.Component;

@Component
public class LoginFacadeImpl implements LoginFacade {

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}
