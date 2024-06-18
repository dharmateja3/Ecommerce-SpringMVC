package com.spring.Ecommerce2.repository;


import com.spring.Ecommerce2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

