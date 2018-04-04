package com.believe.webFlux.users.service;

import com.believe.webFlux.core.config.annotation.TransactionalService;
import com.believe.webFlux.users.domain.User;
import com.believe.webFlux.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@TransactionalService
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.byId(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
