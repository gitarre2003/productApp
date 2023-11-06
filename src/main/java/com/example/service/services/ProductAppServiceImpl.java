package com.example.service.services;

import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.entity.ProductApp;
import com.example.service.exception.ProductNotFoundException;
import com.example.service.repository.ProductsAppRepository;

@Service
public class ProductAppServiceImpl implements ProductAppService 
{
	@Autowired
	private ProductsAppRepository repo;
	
	@Override
	public ProductApp addProduct(ProductApp prd) {
		return repo.save(prd);
	}
	
	
	@Override
	public List<ProductApp> GetAllProducts() {
		return repo.findAll();
	}
	
	@Override
	public ProductApp getProductById(int id) {
		
		Optional<ProductApp> opt = repo.findById(id);
	       if(opt.isPresent()) {
	           return opt.get();
	       } else {
	           throw new ProductNotFoundException("Product Id : "+ id +" Not Found");
	       }
		//return repo.findById(id).get();
	}


	@Override
	public void deleteProductById(int id) {
		repo.delete(getProductById(id));

	}

	@Override
	public void updateProduct(ProductApp prd) {
		repo.save(prd);
	}


}

