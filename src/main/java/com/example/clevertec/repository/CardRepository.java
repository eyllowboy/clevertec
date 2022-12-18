package com.example.clevertec.repository;

import com.example.clevertec.data.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Discount, Long> {
    Optional<Discount> findDiscountByName(String name);
}
