package com.Royal.Main.service.util;

import com.Royal.Main.persistence.entity.Merchandise;
import com.Royal.Main.repository.MerchandiseRepository;
import com.Royal.Main.service.exceptions.MerchandiseNotFoundException;
import com.Royal.Main.service.exceptions.MerchandiseNotInStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MerchandiseUtil {
    private final MerchandiseRepository merchandiseRepository;

    @Autowired
    public MerchandiseUtil(MerchandiseRepository merchandiseRepository) {
        this.merchandiseRepository = merchandiseRepository;
    }

    public int checkCurrentQuantity(String merchName) throws MerchandiseNotFoundException {
        Merchandise merchandise = this.getMerchandiseInformation(merchName);
        return merchandise.getCurrentStockQuantity();
    }

    public Merchandise getMerchandiseInformation(String merchname) throws MerchandiseNotFoundException {
        Optional<Merchandise> optionalMerchandise = merchandiseRepository.findMerchandiseByMerchName(merchname);
        return optionalMerchandise.orElseThrow(() -> new MerchandiseNotFoundException(merchname));
    }

    public void decrementQuantityByOne(String merchName) throws MerchandiseNotInStockException, MerchandiseNotFoundException {
        Merchandise merchandise = this.getMerchandiseInformation(merchName);
        int currentStockQuantity = merchandise.getCurrentStockQuantity();

        if (currentStockQuantity > 0) {
            merchandise.setCurrentStockQuantity(currentStockQuantity - 1);
        } else {
            throw new MerchandiseNotInStockException(merchName);
        }
    }


}
