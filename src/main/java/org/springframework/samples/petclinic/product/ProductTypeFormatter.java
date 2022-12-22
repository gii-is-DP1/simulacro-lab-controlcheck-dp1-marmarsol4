package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{
	
	private final ProductService productService; // Que servicio usa
	
	@Autowired
	public ProductTypeFormatter(ProductService productService) { // Constructor con autowired
		this.productService = productService;
	}
	
    @Override
    public String print(ProductType object, Locale locale) {
        return object.getName();
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
        
    	ProductType tipo = productService.getProductType(text); // uso del servicio 
    	
    	if (tipo != null) 
    		return tipo;
    	else
    		throw new ParseException("Product type not found: " + text, 0);
    	
    }
    
}
