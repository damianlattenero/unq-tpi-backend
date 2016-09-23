package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.factories.ProductFactory;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFactory productFactory;

    @Transactional
    public Product createProduct(String name, String description) {
        Product product = new Product(name, description);
        productRepository.save(product);
        return product;
    }
    @Transactional
    public List<ProductCreationResponse> findAll() {

        List<ProductCreationResponse> prods = new ArrayList<>();

        for(Product f : productRepository.findAll()){
            prods.add((new ProductCreationResponse(f.getName(),f.getDescription(),f.getId())));
        }

        return prods;

    }

    @Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public Product getProduct(String name) {
        return this.productFactory.getProductByName(name);
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
