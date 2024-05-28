package com.Royal.Main.service;

import com.Royal.Main.persistence.dto.CancellationOrderDTO;
import com.Royal.Main.persistence.dto.PurchaseOrderDTO;
import com.Royal.Main.persistence.entity.PurchaseOrder;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchandiseNotInStockException;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderService {
    void processPurchaseOrders(List<PurchaseOrderDTO> purchaseOrdersDTOS) throws MerchandiseNotFoundException, MerchandiseNotInStockException;
    void processSingularOrder(PurchaseOrderDTO purchaseOrderDTO) throws MerchandiseNotFoundException, MerchandiseNotInStockException;
    void cancelPurchaseOrders(List<CancellationOrderDTO> cancellationOrderDTOs) throws MerchandiseNotFoundException, MerchandiseNotInStockException;
    void cancelPurchaseOrder(CancellationOrderDTO cancellationOrderDTO) throws MerchandiseNotFoundException, MerchandiseNotInStockException;
    PurchaseOrder getPurchaseOrder(UUID purchaseOrderNum);
}
