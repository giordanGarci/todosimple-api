package com.giordan.todosimple.services;

import com.giordan.todosimple.models.User;
import com.giordan.todosimple.repositories.UserRepository;
import com.giordan.todosimple.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = this.userRepository.findByUsername(username);
        if(Objects.isNull(user)) throw new UsernameNotFoundException("Entity not found: " + username);
        return new UserSpringSecurity(user.getId(), user.getUsername(), user.getPassword(), user.getProfiles());
    }
}
