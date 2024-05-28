package com.Royal.Main.security;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class UserLoginAuthenticationPoint extends LoginUrlAuthenticationEntryPoint {
    public UserLoginAuthenticationPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
}
