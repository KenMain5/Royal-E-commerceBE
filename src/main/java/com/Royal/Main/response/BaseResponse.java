package com.Royal.Main.response;

public abstract class BaseResponse {
    protected boolean isSuccessful;
    protected String responseMessage;

    public BaseResponse() {
    }

    public BaseResponse(boolean isSuccessful, String responseMessage) {
        this.isSuccessful = isSuccessful;
        this.responseMessage = responseMessage;
    }



    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
