package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	// CrudRepository<Tipo, Tipo_ID>, BaseEntity tiene ID Integer 
	
    List<Product> findAll();
    
    @Query("SELECT pt FROM ProductType pt") // Las querys se hacen con " "
    List<ProductType> findAllProductTypes();
    
    @Query("SELECT pt FROM ProductType pt WHERE pt.name = :name") // :name es name de la func (alias)
    ProductType findProductType(@Param("name") String name); // @Param si lo voy a usar en query
    
    @Query("SELECT p FROM Product p WHERE p.price < :price")
    List<Product> findByPriceLessThan(@Param("price") Double price);
    
    Optional<Product> findById(int id);
    
    Product findByName(String name);
    Product save(Product p);
    
}
