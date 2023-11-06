package com.example.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.service.entity.ProductApp;

@Repository
public interface ProductsAppRepository extends JpaRepository<ProductApp, Integer> {

}
