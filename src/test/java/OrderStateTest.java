import exception.InvalidTransitionException;
import model.Order;
import model.Place;
import model.Product;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderStateTest {
    Product product1;
    User user;
    Order order;
    @Before
    public void before(){
        product1 = new Product();
        user = new User();
        order = new Order(product1, user);
    }

    @Test
    public void fromPendingToCooked(){
        order.cook();

        assertTrue(order.isCooked());
        assertFalse(order.isPending());
        assertFalse(order.isCanceled());
    }

    @Test
    public void fromPendingToCanceled(){
        order.cancel();

        assertTrue(order.isCanceled());
        assertFalse(order.isPending());
        assertFalse(order.isCooked());
    }

    @Test(expected = InvalidTransitionException.class)
    public void fromCanceledToCanceled(){
        order.cancel();
        order.cancel();
    }

    @Test(expected = InvalidTransitionException.class)
    public void fromCookedToCooked(){
        order.cook();
        order.cook();
    }





}
