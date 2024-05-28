package com.Royal.Main.repository;

import com.Royal.Main.persistence.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    Optional<Merchandise> findMerchandiseByMerchName(String merchname);

    void deleteByMerchName(String merchname);

    Optional<Merchandise> findByMerchName(String merchname);

    @Query("SELECT m.imageLocation FROM Merchandise m WHERE m.id = :id")
    Optional<String> findImageLocationById(int id);

}
