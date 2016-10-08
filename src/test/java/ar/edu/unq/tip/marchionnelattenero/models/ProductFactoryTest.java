package ar.edu.unq.tip.marchionnelattenero.models;

import ar.edu.unq.tip.marchionnelattenero.factories.ProductFactory;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import ar.edu.unq.tip.marchionnelattenero.services.AbstractServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ProductFactoryTest extends AbstractServiceTest {
    @Autowired
    protected ProductRepository repository;

    @Autowired
    protected ProductFactory productFactory;

    protected Product product;
    protected int cantidad;


    @Before
    public void setUp() {
        // this.productFactory.createBasicProducts();
        this.product = this.productFactory.getMila();
    }

    @Test
    public void testCountProductCreated() {
        assertEquals(3, this.cantidad = repository.count());
    }

    @Test
    public void testVoucher() {
        assertEquals(this.productFactory.getMila().getName(), "Mila");
        assertEquals(this.productFactory.getHamburguesa().getDescription(), "Simple");
    }


}

