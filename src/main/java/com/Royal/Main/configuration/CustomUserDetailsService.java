package com.Royal.Main.configuration;

import com.Royal.Main.entity.RoyalUser;
import com.Royal.Main.repository.RoyalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RoyalUserRepository royalUserRepository;

    @Autowired
    public CustomUserDetailsService(RoyalUserRepository royalUserRepository) {
        this.royalUserRepository = royalUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<RoyalUser> currentUser= royalUserRepository.getRoyalUserByEmail(username);

        //UserDetailsService userDetailsService = new User();
        return null;
    }
}
