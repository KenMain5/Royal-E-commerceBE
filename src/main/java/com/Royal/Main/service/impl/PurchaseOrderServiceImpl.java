package com.Royal.Main.service.impl;

import com.Royal.Main.persistence.dto.CancellationOrderDTO;
import com.Royal.Main.persistence.dto.PurchaseOrderDTO;
import com.Royal.Main.persistence.entity.*;
import com.Royal.Main.persistence.entity.enums.PurchaseStatus;
import com.Royal.Main.repository.PurchaseOrderRepository;
import com.Royal.Main.service.PurchaseOrderService;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchandiseNotInStockException;
import com.Royal.Main.service.util.MerchandiseUtil;
import com.Royal.Main.service.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final MerchandiseUtil merchandiseUtil;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final UserUtil userUtil;

    @Autowired
    public PurchaseOrderServiceImpl(MerchandiseUtil merchandiseUtil, PurchaseOrderRepository purchaseOrderRepository, UserUtil userUtil) {
        this.merchandiseUtil = merchandiseUtil;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.userUtil = userUtil;
    }


    @Transactional
    @Override
    public void processPurchaseOrders(List<PurchaseOrderDTO> purchaseOrderDTOs) throws MerchandiseNotFoundException, MerchandiseNotInStockException {
        for (PurchaseOrderDTO purchaseOrderDTO: purchaseOrderDTOs) {
            this.processSingularOrder(purchaseOrderDTO);
        }
    }


    @Override
    public void processSingularOrder(PurchaseOrderDTO purchaseOrderDTO) throws MerchandiseNotFoundException, MerchandiseNotInStockException {
        Merchandise merchandise = merchandiseUtil.getMerchandiseInformation(purchaseOrderDTO.getMerchName());
        User user = userUtil.getUserInformation(purchaseOrderDTO.getUserName());
        String merchandiseName = merchandise.getMerchName();

        //Decrement quantity by one
        if (merchandiseUtil.checkCurrentQuantity(merchandiseName) != 0  ) {

            //Create the purchase order
            merchandiseUtil.decrementQuantityByOne(merchandiseName);

            //Create the Purchase Order from the DTO
            PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                    .orderPlaced(LocalDateTime.now())
                    .purchaseStatus(PurchaseStatus.PROCESSING)
                    .merchName(merchandiseName)
                    .merchPrice(merchandise.getMerchPrice())
                    .orderNumber(UUID.randomUUID())
                    .userFinancials(purchaseOrderDTO.getUserFinancials())
                    .user(user)
                    .build();

            //Add to PurchaseOrder
            purchaseOrderRepository.save(purchaseOrder);
        }

        /* Future Updates: Implement Money System
        *  Remove money from the user bank account,
         * Add money to the merchants account? Another if statement to see if the user has money first
         * */
    }

    @Override
    public void cancelPurchaseOrders(List<CancellationOrderDTO> cancellationOrderDTOs) throws MerchandiseNotFoundException, MerchandiseNotInStockException {
        for(CancellationOrderDTO cancellationOrderDTO: cancellationOrderDTOs) {
            this.cancelPurchaseOrder(cancellationOrderDTO);
        }

    }

    @Override
    public void cancelPurchaseOrder(CancellationOrderDTO cancellationOrderDTO) throws MerchandiseNotFoundException, MerchandiseNotInStockException {
        //Ensure Merchandise is in the system to get the actual name
        Merchandise merchandise = merchandiseUtil.getMerchandiseInformation(cancellationOrderDTO.getMerchName());
        String merchandiseName = merchandise.getMerchName();
        PurchaseOrder purchaseOrder = this.getPurchaseOrder(cancellationOrderDTO.getOrderNumber());

        //Increase the quantity by one
        merchandiseUtil.decrementQuantityByOne(merchandiseName);

        //Remove the Purchase Order from database
        purchaseOrderRepository.delete(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrder(UUID purchaseOrderNum){
        return purchaseOrderRepository.findPurchaseOrderByOrderNumber(purchaseOrderNum);
    }

    public Page<PurchaseOrder> getPurchaseHistory(Integer page) {
        Object email = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (email != null) {
            PageRequest pageable = PageRequest.of(page, 5, Sort.by("orderPlaced").descending());
            return purchaseOrderRepository.findAll(pageable);
        }

        return null;
    }
}
