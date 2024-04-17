package com.Royal.Main.service.impl;

import com.Royal.Main.entity.PurchaseOrder;
import com.Royal.Main.repository.PurchaseOrderRepository;
import com.Royal.Main.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public void addPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        purchaseOrderRepository.saveAll(purchaseOrders);

        //response
    }

    @Override
    public void cancelPurchaseOrder(List<PurchaseOrder> purchaseOrders) {
        purchaseOrderRepository.deleteAll(purchaseOrders);
        //check each one if they're in the database or not.
        //response
    }

    @Override
    public void processSingularOrder(PurchaseOrder purchaseOrders) {
        //in the future, check if they're available first..
        //subtract one from the current stock
        //


    }


}
