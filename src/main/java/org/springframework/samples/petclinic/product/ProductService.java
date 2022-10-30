package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // HAY QUE PONERLE QUE ES UN SERVICIO
public class ProductService {
	
	private ProductRepository productRepository; // Hay que declarar el repo a usar
	
	@Autowired
	public ProductService(ProductRepository productRepository) { // Constructor con autowired
		this.productRepository = productRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Product> getAllProducts() throws DataAccessException {
        return productRepository.findAll();
    }

    public List<Product> getProductsCheaperThan(double price) {
        return productRepository.findByPriceLessThan(price); //Metodo nuevo en el repo
    }

    public ProductType getProductType(String typeName) {
        return productRepository.findProductType(typeName); // Usar metodo del repo
    }

    public Product save(Product p){
        return null;       
    }

	public List<ProductType> findAllProductTypes() {
		return productRepository.findAllProductTypes();
	}

}
