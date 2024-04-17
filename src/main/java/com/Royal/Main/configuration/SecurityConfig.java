package com.Royal.Main.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //store password as {bcrypt}hashedpassword

        //
        //matches function
        //encode function
        //can you do JWT without Spring Security?
    }

}
