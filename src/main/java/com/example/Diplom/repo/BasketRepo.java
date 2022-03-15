package com.example.Diplom.repo;

import com.example.Diplom.ent.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    Basket findByCustomerId(Long customerId);
    Optional<Basket> getByCustomerId(Long customerId);

}
