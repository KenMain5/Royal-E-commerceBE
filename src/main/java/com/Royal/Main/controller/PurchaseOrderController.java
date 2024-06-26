package com.Royal.Main.controller;

import com.Royal.Main.persistence.dto.CancellationOrderDTO;
import com.Royal.Main.persistence.dto.PurchaseOrderDTO;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchandiseNotInStockException;
import com.Royal.Main.service.impl.PurchaseOrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/purchase")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderServiceImpl purchaseOrderService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPurchaseOrders(List<PurchaseOrderDTO> purchaseOrderDTOS) throws MerchandiseNotFoundException, MerchandiseNotInStockException {
        purchaseOrderService.processPurchaseOrders(purchaseOrderDTOS);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cancelPurchaseOrders(List<CancellationOrderDTO> cancellationOrderDTOS) throws MerchandiseNotFoundException, MerchandiseNotInStockException {
        purchaseOrderService.cancelPurchaseOrders(cancellationOrderDTOS);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/retrieve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrievePurchaseOrders(@RequestParam(name="page", defaultValue = "0") Integer page){
        Page pageable = purchaseOrderService.getPurchaseHistory(page);
        return new ResponseEntity(pageable, HttpStatus.OK);
    }
}
