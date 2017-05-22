package am.david.securityapp.dao;

import am.david.securityapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName (@Param("username") String userName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET userrank = ?1 WHERE username = ?2", nativeQuery = true)
    int setRank (@Param("userrank") int userRank, @Param("username") String userName);
}
