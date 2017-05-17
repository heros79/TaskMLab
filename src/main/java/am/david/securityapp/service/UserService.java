package am.david.securityapp.service;

import am.david.securityapp.model.User;

import java.util.List;

/**
 * Service interface for {@link User}.
 * Created by David Karchikyan on 5/14/2017.
 */
public interface UserService {

    void save(User user);

    void saveRank(User user);

    User findByUserName(String userName);

    List<User> getRankList ();
}
