import exception.InvalidTransitionException;
import model.Order;
import model.Product;
import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProductTest {
    Product product;
    User user;
    Order order;

    @Before
    public void before(){
        product = new Product();
        user = new User();
        order = new Order(product, user);
    }

    @Test
    public void newProductHasNoPendingOrder(){
        Product aProduct = new Product("Comida1", "Plato Principal");
        assertTrue(aProduct.getPendingCount() == 0);
    }

    @Test
    public void newOrderGeneratePendingProduct(){
        assertTrue(product.getPendingCount() == 1);
    }

    @Test
    public void cookOrderGenerateNoPending(){
        order.cook();
        assertTrue(product.getPendingCount() == 0);
    }

    @Test
    public void cancelOrderGenerateNoPending(){
        order.cancel();
        assertTrue(product.getPendingCount() == 0);
    }

}
