package am.david.securityapp.service;

/*import am.david.securityapp.model.User;*/
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Implementation of {@link SecurityService} Interface
 * Created by David Karchikyan on 5/14/2017.
 */

@Service
public class SecurityServiceImpl implements SecurityService{

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityServiceImpl() {
    }



    @Override
    public String findLoggedByUserName() {
        Object userDetalis = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if (userDetalis instanceof UserDetailServiceImpl) {
            return ((UserDetails) userDetalis).getUsername();
        }
        return null;
    }

    @Override
    public void autoLoggin(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        logger.debug(String.format("Succesfully auto loggin", userName));
    }
}
