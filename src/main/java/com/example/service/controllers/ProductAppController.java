package com.example.service.controllers;

import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.entity.ProductApp;
import com.example.service.exception.ProductNotFoundException;
import com.example.service.services.ProductAppService;
//import com.netflix.discovery.DiscoveryClient;

//import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/product")
public class ProductAppController 
{
	
	@Autowired
	private ProductAppService service;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/") 
    public String showHomePage() {
       return "homePage";
    }

    @GetMapping("/register")
    public String showRegistration() {
       return "registerProductPage";
    }
	
    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute ProductApp product,
            Model model
            ) {
    	HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<ProductApp> httpEntity = new HttpEntity<>(product, httpHeaders);
        ResponseEntity<ProductApp> resp = restTemplate.exchange("http://localhost:8084/", HttpMethod.POST, httpEntity, ProductApp.class);
        int id = resp.getBody().getProductid();
        String message = "Record with id : '"+id+"' is saved successfully !";
        model.addAttribute("message", message);
        return "registerProductPage";
    }
    @GetMapping("/getAllProducts")
    public String getAllInvoices(
            @RequestParam(value = "message", required = false) String message,
            Model model
            ) {
       HttpEntity<String> httpEntity = new HttpEntity<String>("");
       ResponseEntity<List> resp = restTemplate.exchange("http://localhost:8084/", HttpMethod.GET, httpEntity, List.class);
       List<ProductApp> products= resp.getBody();
       model.addAttribute("list", products);
       model.addAttribute("message", message);
       return "allProductsPage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
            ) {
       String page = null; 
       try {
       	HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<ProductApp> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<ProductApp> resp = restTemplate.exchange("http://localhost:8084/"+id, HttpMethod.GET, httpEntity, ProductApp.class);
       ProductApp product = resp.getBody();
       model.addAttribute("product", product);
       page="editProductPage";
       } catch (ProductNotFoundException e) {
           e.printStackTrace();
           attributes.addAttribute("message", e.getMessage());
           page="redirect:getAllProducts";
       }
       return page; 
    }

    @PostMapping("/update")
    public String updateProduct(
            @ModelAttribute ProductApp product,
            RedirectAttributes attributes
            ) {
   	   HttpHeaders httpHeaders = new HttpHeaders();
       HttpEntity<ProductApp> httpEntity = new HttpEntity<>(product, httpHeaders);
       ResponseEntity<ProductApp> resp = restTemplate.exchange("http://localhost:8084/", HttpMethod.PUT, httpEntity, ProductApp.class);
       int id = resp.getBody().getProductid();
       attributes.addAttribute("message", "Product with id: '"+id+"' is updated successfully !");
       return "redirect:getAllProducts";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id,
            RedirectAttributes attributes
            ) {
        try {
       	HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<ProductApp> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> resp = restTemplate.exchange("http://localhost:8084/"+id, HttpMethod.DELETE, httpEntity, String.class);
        attributes.addAttribute("message", "Product with Id : '"+id+"' is removed successfully!");
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:getAllProducts";
    }

    
	//@Autowired
	//private DiscoveryClient dclient;
	//@Autowired
//	private LoadBalancerClient loadbal;

	
	
	
}


