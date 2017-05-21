package am.david.securityapp.validator;

import am.david.securityapp.dao.UserDao;
import am.david.securityapp.model.User;
import am.david.securityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * Created by David on 5/21/2017.
 */

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    HttpSession session;

    private User user;

    @Override
    @ModelAttribute(name = "listner")
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
             userService.saveRank(userDao.findByUserName(user.getUserName()));
    }
}
