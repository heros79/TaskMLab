package am.david.securityapp.validator;


import am.david.securityapp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * Implementation of {@link ApplicationListener<AbstractAuthenticationEvent>} Interface
 * Created by David Karchikyan on 5/21/2017.
 */

@Component
public class LoginListener implements ApplicationListener<AbstractAuthenticationEvent> {


    @Autowired
    private UserDao userDao;


    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent appEvent) {

        if (appEvent instanceof AuthenticationSuccessEvent) {
            final AuthenticationSuccessEvent successEvent = (AuthenticationSuccessEvent) appEvent;
            successEvent.getAuthentication();

            final UserDetails user = (UserDetails) successEvent.getAuthentication().getPrincipal();

        } else if (appEvent instanceof InteractiveAuthenticationSuccessEvent) {

            final InteractiveAuthenticationSuccessEvent successEvent = (InteractiveAuthenticationSuccessEvent) appEvent;

            final UserDetails user = (UserDetails) successEvent.getAuthentication().getPrincipal();

            userDao.setRank(new Random().nextInt(100), user.getUsername());

        } else if (appEvent instanceof AbstractAuthenticationFailureEvent) {

        } else {

        }


/*        AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetailServiceImpl) {
            try {
                userDao.setRank(User.class.newInstance().getUserRank() + new Random().nextInt(100), (event.getAuthentication().getName()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(event.getAuthentication().getName());
        }*/
    }
}
