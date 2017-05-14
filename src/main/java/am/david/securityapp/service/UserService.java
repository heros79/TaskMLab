package am.david.securityapp.service;

import am.david.securityapp.model.User;

/**
 * Service interface for {@link User}.
 * Created by David Karchikyan on 5/14/2017.
 */
public interface UserService {

    void save(User user);

    User findByUserName(String userName);
}
