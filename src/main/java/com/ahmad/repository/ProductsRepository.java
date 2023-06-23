package com.ahmad.repository;

import com.ahmad.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Integer>{
}
