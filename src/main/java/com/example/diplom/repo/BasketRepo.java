package com.example.diplom.repo;

import com.example.diplom.ent.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    Basket findByCustomerId(Long customerId);
    }
