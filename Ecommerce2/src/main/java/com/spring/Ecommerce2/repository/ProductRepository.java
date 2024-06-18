package com.spring.Ecommerce2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Ecommerce2.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
