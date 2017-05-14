package am.david.securityapp.service;

import am.david.securityapp.dao.UserDao;
import am.david.securityapp.model.Role;
import am.david.securityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} Interface
 * Created by David Karchikyan on 5/14/2017.
 */
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    public UserDetailServiceImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
