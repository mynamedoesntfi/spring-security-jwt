package com.example.springsecurityjpa.Services;

import com.example.springsecurityjpa.Models.MyUserDetails;
import com.example.springsecurityjpa.Models.User;
import com.example.springsecurityjpa.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //region JPA

        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User '"+ username + "' Not found"));

        //endregion

        return user.map(MyUserDetails::new).get();
    }
}
