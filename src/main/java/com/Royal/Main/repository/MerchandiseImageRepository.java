package com.Royal.Main.repository;

import com.Royal.Main.persistence.entity.MerchandiseImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandiseImageRepository extends JpaRepository<MerchandiseImage, Long> {
}
