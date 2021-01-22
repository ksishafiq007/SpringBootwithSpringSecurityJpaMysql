package io.javaspringbootsecurity.springsecurityjpa;

import io.javaspringbootsecurity.springsecurityjpa.models.MyUserDetails;
import io.javaspringbootsecurity.springsecurityjpa.models.User;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //return new MyUserDetails(s);
         Optional<User> user= userRepository.findByUserName(userName);
         user.orElseThrow(() ->new UsernameNotFoundException("Not found: "+userName));
         return user.map(MyUserDetails::new).get();
    }
}
