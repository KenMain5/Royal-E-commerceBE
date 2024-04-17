package com.Royal.Main.response;

public class AuthenticationResponse extends BaseResponse{
    private String token;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(boolean isSuccessful, String responseMessage, String token) {
        super(isSuccessful, responseMessage);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
