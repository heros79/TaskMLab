package am.david.securityapp.service;

/**
 * Service for Security.
 * Created by David Karchikyan on 5/14/2017.
 */

public interface SecurityService {

    String findLoggedByUserName();

    void autoLoggin (String userName, String password);
}
