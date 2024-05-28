package com.Royal.Main.repository;

import com.Royal.Main.persistence.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    PurchaseOrder existsPurchaseOrderByOrderNumber(UUID orderNumber);
    PurchaseOrder findPurchaseOrderByOrderNumber(UUID orderNumber);
}
