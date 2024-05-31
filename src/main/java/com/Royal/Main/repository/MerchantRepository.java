package com.Royal.Main.repository;

import com.Royal.Main.persistence.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Optional<Merchant> getMerchantByEmail(String email);
    Boolean existsByEmail(String email);
}
