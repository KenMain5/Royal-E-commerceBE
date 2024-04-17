package com.Royal.Main.repository;

import com.Royal.Main.entity.RoyalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoyalUserRepository extends JpaRepository<RoyalUser, Long> {
    boolean existsByEmail(String email);
    Optional<RoyalUser> getRoyalUserByEmail(String email);
}
