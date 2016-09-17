package factories;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

@Component("productFactory")
public class ProductFactory {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createBasicProducts() {
        Product milanesaCompleta = new Product("milanesa", "completa");
        Product hamburguesaConQueso = new Product("hamburguesa", "con queso");
    }

    public Product getProductByName(String name){ return  this.productRepository.findByProductName(name);}

    public Product getProductByID(Integer id){return this.productRepository.findById(id);}
}
