package com.Royal.Main.service.impl;

import com.Royal.Main.repository.MerchandiseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MerchandiseServiceImplTest {
    private MerchandiseRepository mockedMerchandiseRepository;
    private MerchantServiceImpl mockedMerchantServiceImpl;
    private MerchandiseServiceImpl mockedMerchandiseServiceImpl;

    @BeforeEach
    void setUp() {
        mockedMerchandiseRepository = mock(MerchandiseRepository.class);
        mockedMerchantServiceImpl = mock(MerchantServiceImpl.class);

        mockedMerchandiseServiceImpl = new MerchandiseServiceImpl(
                mockedMerchandiseRepository
                ,mockedMerchantServiceImpl
        );
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(
                mockedMerchandiseRepository,
                mockedMerchantServiceImpl );
    }

    @Test
    void addMerchandises() {

    }

    @Test
    void removeMerchandises() {
    }

    @Test
    void getMerchandisesCreatedByMerchant() {
    }

    @Test
    void getSingleMerchandiseCreatedByMerchant() {
    }

    @Test
    void getMerchandiseListByMerchant() {

        /*
        Optional<List<Merchandise>> optionalMerchandiseList = merchandiseRepository.findMerchandisesByMerchant(merchant);
        if (optionalMerchandiseList.isPresent()) {
            return optionalMerchandiseList.get();
        } else {
            throw new MerchantNotFoundException();
        }
        * */
    }




    @Test
    void findMerchandiseByMerchName() {
    }

    @Test
    void saveMerchandise() {
    }

    @Test
    void createMerchandiseFromDTO() {
    }
}