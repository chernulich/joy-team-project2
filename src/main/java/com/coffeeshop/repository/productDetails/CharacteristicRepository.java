package com.coffeeshop.repository.productDetails;

import com.coffeeshop.model.entity.productDetails.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
