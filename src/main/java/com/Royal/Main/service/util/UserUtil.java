package com.Royal.Main.service.util;

import com.Royal.Main.persistence.entity.User;
import com.Royal.Main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUtil {
    //remove this

    private final UserRepository userRepository;

    @Autowired
    public UserUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserInformation(String email) throws UsernameNotFoundException{
        Optional<User> optionalUser =  userRepository.getUserByEmail(email);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
