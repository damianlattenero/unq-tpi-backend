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
        Product voucher100 = new Product("Fuel Voucher 100", 50, 15000);
        Product voucher200 = new Product("Fuel Voucher 200", 30, 28000);
        Product oilChange = new Product("Oil Change", 20, 22000);
        this.productRepository.save(voucher100);
        this.productRepository.save(voucher200);
        this.productRepository.save(oilChange);
    }

    public Product getFuelVoucher100() {
        return this.productRepository.findByname("Fuel Voucher 100");
    }

    public Product getFuelVoucher200() {
        return this.productRepository.findByname("Fuel Voucher 200");
    }

    public Product getOilChange() {
        return this.productRepository.findByProductName("Oil Change");
    }

    public Product getProductByName(String name){ return  this.productRepository.findByProductName(name);}

    public Product getProductByID(Integer id){return this.productRepository.findById(id);}
}
