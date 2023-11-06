package com.example.service.entity;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.PositiveOrZero;

//import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="product2023")
public class ProductApp 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	
	@Column(nullable = false)
    private String name;

//@Min(value=0L)
 //@Range(min=0l,message= "Use Positive Numbers Only")
   // @PositiveOrZero
	@Column(nullable = false)
    private double price;
	
	@Column(nullable = false)
    private String category;


	
}


