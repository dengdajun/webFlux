package com.believe.webFlux.users.service;

import com.believe.webFlux.users.domain.User;

import java.util.List;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
public interface UserService {

    List<User> findAll();

    User findById(String id);

    User save(User user);
}
