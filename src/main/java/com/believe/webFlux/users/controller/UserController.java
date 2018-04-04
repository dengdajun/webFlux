package com.believe.webFlux.users.controller;

import com.believe.webFlux.core.config.annotation.ValidatedController;
import com.believe.webFlux.users.domain.User;
import com.believe.webFlux.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@ValidatedController("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Mono<User> users(@PathVariable("id") String id) {
        return Mono.just(userService.findById(id));
    }

    @GetMapping
    public Flux<User> users() {
        return Flux.fromIterable(userService.findAll());
    }

    @PostMapping
    public Mono<User> users(@Valid @RequestBody User user) {
        return Mono.just(userService.save(user));
    }
}
