package am.david.securityapp.dao;

import am.david.securityapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName (String userName);
}
