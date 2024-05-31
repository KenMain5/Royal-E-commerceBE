package com.Royal.Main.security.config;

import com.Royal.Main.security.MerchantAuthenticationProvider;
import com.Royal.Main.security.MerchantLoginAuthenticationPoint;
import com.Royal.Main.security.UserAuthenticationProvider;
import com.Royal.Main.security.UserLoginAuthenticationPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* This separate configuration file will prevent a circular dependency
 * */
@Component
@AllArgsConstructor
@EnableTransactionManagement
public class CommonBeansConfig {



    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public MerchantLoginAuthenticationPoint MerchantLoginAuthenticationPoint(){
        return new MerchantLoginAuthenticationPoint("/merchant/login");
    }

    @Bean
    public UserLoginAuthenticationPoint userLoginAuthenticationPoint(){
        return new UserLoginAuthenticationPoint("/user/login");
    }


}
