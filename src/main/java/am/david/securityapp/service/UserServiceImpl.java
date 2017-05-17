package am.david.securityapp.service;

import am.david.securityapp.dao.RoleDao;
import am.david.securityapp.dao.UserDao;
import am.david.securityapp.model.Role;
import am.david.securityapp.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of {@link UserService} Interface
 * Created by David Karchikyan on 5/14/2017.
 */

@Service("User")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource(name = "sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public UserServiceImpl() {
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public void saveRank(User user) {
        int userRank = new Random().nextInt(100);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE mlab SET userrank=userrank+" + userRank + "WHERE username = :nameCode");
        query.setParameter("nameCode", user.getUserName());
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public List<User> getRankList() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM users");

        return  query.list();
    }
}
