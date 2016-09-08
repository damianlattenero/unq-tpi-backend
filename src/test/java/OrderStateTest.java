import model.Order;
import model.Place;
import model.Product;
import model.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by damian on 08/09/16.
 */
public class OrderStateTest {
    Product product1 = new Product();
    User user = new User();


    @Test
    public void fromPendingToCooked(){
        Order order = new Order(product1, user);

        order.cook();

        Assert.assertTrue(order.isCooked());
        Assert.assertFalse(order.isPending());
        Assert.assertFalse(order.isCanceled());
    }



}

