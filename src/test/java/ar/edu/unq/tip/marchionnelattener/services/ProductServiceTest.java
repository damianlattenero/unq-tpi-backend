package ar.edu.unq.tip.marchionnelattener.services;


import ar.edu.unq.tip.marchionnelattenero.factories.ProductFactory;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import ar.edu.unq.tip.marchionnelattenero.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends AbstractServiceTest {

    @Autowired
    protected ProductFactory productFactory;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected ProductRepository productRepository;

    private Product product;

    @Before
    public void setUp() {
        // this.productFactory.createBasicProducts();
        this.product = this.productService.createProduct("Milanga", "Mega");
    }

    @Test
    public void TestNewProduct() {
        assertEquals("Milanga", this.product.getName());
        assertEquals("Mega", this.product.getDescription());
    }

    @Test
    public void TestOneMoreProduct() {
        assertEquals(4, this.productRepository.count());
        this.productService.createProduct("Paty", "Super");
        assertEquals(5, this.productRepository.count());
    }

    @Test
    public void FindProductByName() {
        Product productFinded = this.productService.getProduct("Milanga");
        assertEquals(product, productFinded);
    }

    @Test
    public void DeleteProduct() {
        assertEquals(4, this.productRepository.count());
        this.productService.deleteProduct(product);
        assertEquals(3, this.productRepository.count());
    }


}
