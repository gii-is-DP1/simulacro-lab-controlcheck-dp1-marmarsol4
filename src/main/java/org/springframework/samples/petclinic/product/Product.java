package org.springframework.samples.petclinic.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity // Declaro que es una entidad
@Table(name = "products") // Creo una tabla
public class Product extends BaseEntity {
    
	//Integer id; // Ahora es el Base Entity
	// Si le pongo NotEmpty a cualquiera de los dos falla
	
	@Size(min = 3, max = 50)
	@NotNull
    private String name; // Los atributos deben ser privados
	
	@PositiveOrZero
	@NotNull
    private double price;
	
	@ManyToOne
	@JoinColumn(name = "product_type_id")
	private ProductType productType;
    
}
