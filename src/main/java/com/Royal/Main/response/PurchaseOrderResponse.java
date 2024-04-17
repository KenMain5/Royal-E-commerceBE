package com.Royal.Main.response;

public class PurchaseOrderResponse extends BaseResponse{
    public PurchaseOrderResponse() {
    }

    public PurchaseOrderResponse(boolean isSuccessful, String responseMessage) {
        super(isSuccessful, responseMessage);
    }


}
