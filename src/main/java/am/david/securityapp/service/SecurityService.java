package am.david.securityapp.service;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} Interface
 * Created by David Karchikyan on 5/14/2017.
 */

public interface SecurityService {

    String findLoggedByUserName();

    void autoLoggin (String userName, String password);
}
