package com.Royal.Main.security;

import com.Royal.Main.persistence.entity.Merchant;
import com.Royal.Main.repository.MerchantRepository;
import com.Royal.Main.repository.UserRepository;
import com.Royal.Main.service.exceptions.AuthenticationException;
import com.Royal.Main.service.util.RoleUtility;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
public class MerchantAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final MerchantRepository merchantRepository;
    private final RoleUtility roleUtility;
    private final Logger logger = LoggerFactory.getLogger(MerchantAuthenticationProvider.class);
    private final JWTUtil jwtUtil;

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws com.Royal.Main.service.exceptions.AuthenticationException {
        String givenEmail = authentication.getPrincipal().toString();
        String givenPassword = authentication.getCredentials().toString();

        //gets the user information from the database
        Optional<Merchant> optionalMerchant = merchantRepository.getMerchantByEmail(givenEmail);

        //checks the credentials and does the authentication process
        if (optionalMerchant.isPresent()) {
            if (passwordEncoder.matches(givenPassword, optionalMerchant.get().getPassword())) {
                logger.info("Merchant has been authenticated");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(givenEmail, givenPassword, this.getGrantedList());
                jwtUtil.createJWTGenerator(authenticationToken);
                return authenticationToken;
            }
        }
        logger.info("Merchant provided wrong credentials");
        throw new AuthenticationException();
    }

    //Gets the list of granted authorities of the merchant
    public List<GrantedAuthority> getGrantedList(){
        return Collections.singletonList(new SimpleGrantedAuthority(roleUtility.getMerchantRole().getGivenRole()));
    }
}
