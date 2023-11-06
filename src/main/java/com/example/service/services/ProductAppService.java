package com.example.service.services;

import java.util.List;

import com.example.service.entity.ProductApp;

public interface ProductAppService 
{
	
	public ProductApp addProduct(ProductApp prd);
	public List<ProductApp> GetAllProducts();
	public ProductApp getProductById(int id);
	public void deleteProductById(int id);
	public void updateProduct(ProductApp prd);
	
}
