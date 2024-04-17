package com.Royal.Main.service;

import com.Royal.Main.entity.PurchaseOrder;
import com.Royal.Main.entity.RoyalMerchandise;

import java.util.List;

public interface PurchaseOrderService {
    void addPurchaseOrders(List<PurchaseOrder> purchaseOrders);
    void cancelPurchaseOrder(List<PurchaseOrder> purchaseOrders);
}
