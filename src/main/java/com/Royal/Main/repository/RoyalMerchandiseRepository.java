package com.Royal.Main.repository;

import com.Royal.Main.entity.RoyalMerchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoyalMerchandiseRepository extends JpaRepository<RoyalMerchandise, Long> {

}
