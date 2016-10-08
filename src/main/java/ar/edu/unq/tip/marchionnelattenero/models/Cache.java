package ar.edu.unq.tip.marchionnelattenero.models;

import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Cache {
    private Map<Integer, Integer> productsPending;
    private static AtomicReference<Cache> INSTANCE = new AtomicReference<Cache>();

    public Cache(){

        productsPending = new HashMap<Integer, Integer>();

        final Cache previous = INSTANCE.getAndSet(this);
        if (previous != null)
            throw new IllegalStateException("Second singleton " + this + " created after " + previous);
    }

    public static Cache getInstance() {
        return INSTANCE.get();
    }


    private static volatile Cache instance = null;


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FoodOrderRepository foodOrderRepository;


    public void addFoodOrder(FoodOrder foodOrder) {
        int idProduct = foodOrder.getProduct().getId();
        int count = productsPending.get(idProduct);

        productsPending.replace(idProduct, count + foodOrder.getAmount());
    }

    public Integer getPending(Integer idProduct) {
       return  (productsPending.get(idProduct));
    }

    public void addNewProduct(int id) {
        this.productsPending.put(id, 0);
    }
}
