package com.Royal.Main.security;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class MerchantLoginAuthenticationPoint extends LoginUrlAuthenticationEntryPoint {
    public MerchantLoginAuthenticationPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
}
