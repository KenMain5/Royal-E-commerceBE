package com.Royal.Main.repository;

import com.Royal.Main.persistence.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> findMerchantByEmail(String email);

    Boolean existsByEmail(String email);
}
