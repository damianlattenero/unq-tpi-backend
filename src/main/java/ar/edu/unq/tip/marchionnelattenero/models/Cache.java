package ar.edu.unq.tip.marchionnelattenero.models;

import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Cache {
    private Map<Integer, Integer> productsPending;
    private static AtomicReference<Cache> INSTANCE = new AtomicReference<Cache>();
    private static volatile Cache instance = null;
    @Autowired
    private ProductRepository productRepository;


    public Cache() {

        productsPending = new HashMap<Integer, Integer>();

        final Cache previous = INSTANCE.getAndSet(this);
        if (previous != null)
            throw new IllegalStateException("Second singleton " + this + " created after " + previous);
//        this.update();
    }

    public void update() {
        for(Product p :this.productRepository.findAll()){
            Cache.getInstance().productsPending.putIfAbsent(p.getId(), 0);
        }
    }

    public static Cache getInstance() {
        return INSTANCE.get();
    }

    public void addFoodOrder(FoodOrder foodOrder) {
        int idProduct = foodOrder.getProduct().getId();
        int count = this.getPending(idProduct);

        if (productsPending.containsKey(idProduct))
            productsPending.replace(idProduct, count + foodOrder.getAmount());
        else
            productsPending.put(idProduct, count + foodOrder.getAmount());
    }

    public Integer getPending(Integer idProduct) {
        return (productsPending.containsKey(idProduct)) ? productsPending.get(idProduct) : 0;
    }

    public void addNewProduct(int id) {
        this.productsPending.put(id, 0);
    }
}
