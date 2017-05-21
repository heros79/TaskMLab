package am.david.securityapp.service;

import am.david.securityapp.dao.RoleDao;
import am.david.securityapp.dao.UserDao;
import am.david.securityapp.model.Role;
import am.david.securityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    public UserServiceImpl() {
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int userRank = new Random().nextInt(100);
        user.setUserRank(user.getUserRank() + userRank);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public int saveRank(User user) {
        return userDao.setRank(user.getUserRank() + new Random().nextInt(100));
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public List<User> getRankList() {

        List<User> list = new LinkedList<>();
        list.addAll(userDao.findAll());

        return  list;
    }
}
