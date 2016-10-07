package ar.edu.unq.tip.marchionnelattenero.models;

import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Cache instance = null;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FoodOrderRepository foodOrderRepository;

    private Map<Integer, Integer> productsPending;

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache(new Timestamp(DateTime.now().getMillis()));
        }
        return instance;
    }

    public Cache(Timestamp moment) {
        productsPending = new HashMap<Integer, Integer>();
/*        productsPending.put(1, 1);
        productsPending.put(2, 2);
        productsPending.put(3, 5);*/
        initialize(moment);
    }

    //TODO arreglar como jetty carga los componentes por primera vez
    private void initialize(Timestamp moment) {
        //Create a Map with All the Products
        for (Product product : this.productRepository.findAll()) {
            productsPending.put(product.getId(), findProductsFor(product, moment));
        }
    }

    private int findProductsFor(Product product, Timestamp moment) {
        int amount = 0;

        for (FoodOrder foodOrder : this.foodOrderRepository.findProductsAfterMoment(product, moment)) {
            amount += foodOrder.getAmount();
        }

        return amount;
    }

    public void addFoodOrder(FoodOrder foodOrder){
        int idProduct = foodOrder.getProduct().getId();
        int count = productsPending.get(idProduct);

        productsPending.replace(idProduct, count + foodOrder.getAmount());
    }

    public Integer getPending(Integer idProduct) {
        return (productsPending.containsKey(idProduct)) ? productsPending.get(idProduct) : 0;
    }
}
