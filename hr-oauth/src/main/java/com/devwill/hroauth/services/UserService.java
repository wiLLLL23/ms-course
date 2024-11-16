package com.devwill.hroauth.services;

import com.devwill.hroauth.domain.User;
import com.devwill.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserFeignClient feignClient;

    public UserService(UserFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public User findByEmail(final String email) {
        final var user = feignClient.findByEmail(email).getBody();
        if (user == null) {
            logger.error("Email not fount: {}", email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email fount: {}", email);
        return user;
    }

    public User findById(final Long id) {
        final var user = feignClient.findById(id).getBody();
        if (user == null) {
            logger.error("Id not fount: {}", id);
            throw new IllegalArgumentException("Id not found");
        }
        logger.info("Id fount: {}", id);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = feignClient.findByEmail(username).getBody();
        if (user == null) {
            logger.error("Username not fount: {}", username);
            throw new UsernameNotFoundException("Username not found");
        }
        logger.info("Username fount: {}", username);
        return user;
    }
}
