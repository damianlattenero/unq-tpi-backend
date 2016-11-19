package ar.edu.unq.tip.marchionnelattenero.factories;

import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("productFactory")
public class ProductFactory {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createBasicProducts() {
        Product mila = new Product("Mila", "Completa");
        Product hamburguesa = new Product("Hamburguesa", "Simple");
        Product pebete = new Product("Pebete", "Sin Lechuga");
        this.productRepository.save(mila);
        this.productRepository.save(hamburguesa);
        this.productRepository.save(pebete);
    }

    public Product getMila() {
        return this.productRepository.findByName("Mila");
    }

    public Product getHamburguesa() {
        return this.productRepository.findByName("Hamburguesa");
    }

    public Product getProductByName(String name) {
        return this.productRepository.findByName(name);
    }

    public Product getProductByID(Integer id) {
        return this.productRepository.findById(id);
    }
}
