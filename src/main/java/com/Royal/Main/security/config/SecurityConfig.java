package com.Royal.Main.security.config;


import com.Royal.Main.security.*;
//import com.Royal.Main.security.filter.CustomAuthenticationFilter;
import com.Royal.Main.security.filter.JWTCreationFilter;
import com.Royal.Main.security.filter.JWTVerificationFilter;
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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
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
    private final JWTCreationFilter jwtCreationFilter;
    private final JWTVerificationFilter jwtVerificationFilter;


    @Bean
    SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {

        //Authorization Configuration
        http.authorizeHttpRequests((requests) -> {
            requests
                    .requestMatchers("/home", "/error", "/", "/login").permitAll()
                    .requestMatchers("/user/login", "/user/register").permitAll()
                    .requestMatchers("/merchant/register").permitAll()
                    .requestMatchers("/merchant/merchandise/test").permitAll()
                    .requestMatchers("/images/**").permitAll()
                    .requestMatchers("/login/success").authenticated()
                    .requestMatchers("/merchant/merchandise/**").authenticated()
                    .requestMatchers("/user/cart").authenticated()
                    .requestMatchers("/purchase/**").authenticated();
        });



        //Authentication Configuration
        /* longest time I've spent on a bug, and finally got a better chance at using debugger
        *  formLogin being enabled, means that Spring would handle the authentication process.
        * */
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successForwardUrl("/login/success")
                    .permitAll();
        });





        //Exception Handling
//        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
//        entryPoints.put(new AntPathRequestMatcher("/user/**"), userLoginAuthenticationPoint);
//        entryPoints.put(new AntPathRequestMatcher("/merchant/**"), merchantLoginAuthenticationPoint);
//        entryPoints.put(new AntPathRequestMatcher("/login"), new LoginUrlAuthenticationEntryPoint("/login"));
//
//
//        DelegatingAuthenticationEntryPoint entryPoint = new DelegatingAuthenticationEntryPoint(entryPoints);
//        entryPoint.setDefaultEntryPoint(new Http403ForbiddenEntryPoint());
//
//        http.exceptionHandling((exceptionHandlingConfigurer) -> {
//            exceptionHandlingConfigurer.authenticationEntryPoint(entryPoint);
//        });

        //Session Management
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //Cors Configuration
        http.cors((cors) -> cors.configurationSource((req) -> corsConfig.getCorsConfiguration(req)));

        http.securityContext(httpSecuritySecurityContextConfigurer -> {
            httpSecuritySecurityContextConfigurer.securityContextRepository(new DelegatingSecurityContextRepository(
                    new NullSecurityContextRepository(),
                    new RequestAttributeSecurityContextRepository()
            ));
        });

        //CSRF configuration
        //CSRF is enabled by default
        http.csrf((csrf) -> csrf.disable());
//
        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtVerificationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(jwtCreationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public ProviderManager providerManager(){
        List<AuthenticationProvider> list = new ArrayList<>(Arrays.asList(userAuthenticationProvider, merchantAuthenticationProvider));
        return new ProviderManager(list);
    }
}
