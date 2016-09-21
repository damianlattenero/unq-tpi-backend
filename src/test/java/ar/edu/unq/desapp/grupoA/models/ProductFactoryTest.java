package ar.edu.unq.desapp.grupoA.models;

import ar.edu.unq.desapp.grupoA.services.AbstractServiceTest;
import ar.edu.unq.tip.marchionnelattenero.factories.ProductFactory;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
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
        this.product = this.productFactory.getFuelVoucher100();
    }

    @Test
    public void testCountProductCreated() {
        assertEquals(3, this.cantidad = repository.count());
    }

    @Test
    public void testVoucher() {
        assertEquals(this.productFactory.getFuelVoucher100().getName(), "Fuel Voucher 100");
        assertEquals(this.productFactory.getFuelVoucher100().getPointCost(), 15000);
        assertEquals(this.productFactory.getFuelVoucher200().getName(), "Fuel Voucher 200");
        assertEquals(this.productFactory.getFuelVoucher200().getPointCost(), 28000);
    }

    @Test
    public void testOilChange() {

        assertEquals(this.productFactory.getOilChange().getName(), "Oil Change");
        assertEquals(this.productFactory.getOilChange().getPointCost(), 22000);
    }

}

