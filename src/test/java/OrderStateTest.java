import beans.aspects.exception.InvalidTransitionException;
import beans.aspects.model.Order;
import beans.aspects.model.Product;
import beans.aspects.model.User;
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
    public void newOrderMustBePending(){

        assertTrue(order.isPending());
        assertFalse(order.isCooked());
        assertFalse(order.isCanceled());
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
    public void fromCookToCanceled(){
        order.cook();
        order.cancel();
    }

    @Test(expected = InvalidTransitionException.class)
    public void fromCanceledToCook(){
        order.cancel();
        order.cook();
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
