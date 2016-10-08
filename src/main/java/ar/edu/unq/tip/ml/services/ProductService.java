package ar.edu.unq.tip.ml.services;

import ar.edu.unq.tip.ml.factories.ProductFactory;
import ar.edu.unq.tip.ml.models.Product;
import ar.edu.unq.tip.ml.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
