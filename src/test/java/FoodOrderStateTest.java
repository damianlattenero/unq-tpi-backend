import beans.exception.InvalidTransitionException;
import beans.model.FoodOrder;
import beans.model.Product;
import beans.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodOrderStateTest {
    Product product1;
    User user;
    FoodOrder foodOrder;
    @Before
    public void before(){
        product1 = new Product();
        user = new User();
        foodOrder = new FoodOrder(product1, user);
    }

    @Test
    public void newOrderMustBePending(){

        assertTrue(foodOrder.isPending());
        assertFalse(foodOrder.isCooked());
        assertFalse(foodOrder.isCanceled());
    }

    @Test
    public void fromPendingToCooked(){
        foodOrder.cook();

        assertTrue(foodOrder.isCooked());
        assertFalse(foodOrder.isPending());
        assertFalse(foodOrder.isCanceled());
    }

    @Test
    public void fromPendingToCanceled(){
        foodOrder.cancel();

        assertTrue(foodOrder.isCanceled());
        assertFalse(foodOrder.isPending());
        assertFalse(foodOrder.isCooked());
    }

    @Test(expected = InvalidTransitionException.class)
    public void fromCookToCanceled(){
        foodOrder.cook();
        foodOrder.cancel();
    }

    @Test(expected = InvalidTransitionException.class)
    public void fromCanceledToCook(){
        foodOrder.cancel();
        foodOrder.cook();
    }


    @Test(expected = InvalidTransitionException.class)
    public void fromCanceledToCanceled(){
        foodOrder.cancel();
        foodOrder.cancel();
    }

    @Test(expected = InvalidTransitionException.class)
    public void fromCookedToCooked(){
        foodOrder.cook();
        foodOrder.cook();
    }





}
