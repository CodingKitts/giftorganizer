package com.kittsware.giftorganizer.services.auth;

import com.kittsware.giftorganizer.entities.User;
import com.kittsware.giftorganizer.entities.auth.CustomUserDetails;
import com.kittsware.giftorganizer.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during Authentication");
        User u = this.userRepository.findUserByUserEmail(username).orElseThrow(s);
        return new CustomUserDetails(u);
    }
}
