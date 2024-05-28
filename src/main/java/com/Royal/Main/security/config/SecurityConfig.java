package com.Royal.Main.security.config;


import com.Royal.Main.security.*;
import com.Royal.Main.security.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final JWTUtil jwtUtil;
    private final UserLoginAuthenticationPoint userLoginAuthenticationPoint;
    private final MerchantLoginAuthenticationPoint merchantLoginAuthenticationPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final MerchantAuthenticationProvider merchantAuthenticationProvider;

    @Bean
    SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {

        //Authorization Configuration
        http.authorizeHttpRequests((requests) -> {
            requests
                    .requestMatchers("/home", "/error", "/").permitAll()
                    .requestMatchers("user/login", "user/register").permitAll()
                    .requestMatchers("/merchant/register", "/merchant/signIn", "merchandise/get").permitAll()

                    .requestMatchers("/merchant/merchandise/**").authenticated()
                    .requestMatchers("/purchase/**").authenticated();
        });


        //Authentication Configuration
        /* longest time I've spent on a bug, and finally got a better chance at using debugger
        *  formLogin being enabled, means that Spring would handle the authentication process.
        *
        * */

//        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());


        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer
                    .loginPage("/login")
//                    .usernameParameter()
//                    .passwordParameter()
                    .permitAll();
        });

        //Exception Handling
        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
        entryPoints.put(new AntPathRequestMatcher("/user/**"), userLoginAuthenticationPoint);
        entryPoints.put(new AntPathRequestMatcher("/merchant/**"), merchantLoginAuthenticationPoint);

        DelegatingAuthenticationEntryPoint entryPoint = new DelegatingAuthenticationEntryPoint(entryPoints);
        entryPoint.setDefaultEntryPoint(new Http403ForbiddenEntryPoint());

        http.exceptionHandling((exceptionHandlingConfigurer) -> {
            exceptionHandlingConfigurer.authenticationEntryPoint(entryPoint);
        });

        //Session Management
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER));

        //Cors Configuration
        http.cors((cors) -> cors.configurationSource((req) -> corsConfig.getCorsConfiguration(req)));

        //CSRF configuration
        //CSRF is enabled by default
        http.csrf((csrf) -> csrf.disable());

        http.httpBasic(Customizer.withDefaults());

        http.addFilterBefore(new CustomAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


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

    @Bean
    public ProviderManager providerManager(){
        List<AuthenticationProvider> list = new ArrayList<>(Arrays.asList(userAuthenticationProvider, merchantAuthenticationProvider));
        return new ProviderManager(list);
    }
}
