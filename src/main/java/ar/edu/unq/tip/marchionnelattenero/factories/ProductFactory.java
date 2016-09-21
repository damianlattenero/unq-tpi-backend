package ar.edu.unq.tip.marchionnelattenero.factories;

import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component("productFactory")
public class ProductFactory {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createBasicProducts() {
        Product mila = new Product("Mila", "Completa");
        Product hamburguesa = new Product("Hamburguesa", "Simple");
        this.productRepository.save(mila);
        this.productRepository.save(hamburguesa);
    }

    public Product getMila() {
        return this.productRepository.findByname("Mila");
    }

    public Product getHamburguesa() {
        return this.productRepository.findByname("Hamburguesa");
    }

    public Product getProductByName(String name){ return  this.productRepository.findByProductName(name);}

    public Product getProductByID(Integer id){return this.productRepository.findById(id);}
}
