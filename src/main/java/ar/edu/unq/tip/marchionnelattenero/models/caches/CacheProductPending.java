package ar.edu.unq.tip.marchionnelattenero.models.caches;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;

import java.util.HashMap;
import java.util.Map;

public class CacheProductPending {

    //Mapa de Productos Pendientes Totales
    //Map<ProductId, Cant>
    private Map<Integer, Integer> allProductsPending;

    public CacheProductPending() {
        this.allProductsPending = new HashMap<Integer, Integer>();
    }

    public Map<Integer, Integer> getAllProductsPending() {
        return allProductsPending;
    }

    public void addFoodOrder(FoodOrder foodOrder) {
        int idProduct = foodOrder.getProduct().getId();
        int countCache = getProductPending(idProduct);
        int totalCount = calculate(countCache, foodOrder);

        this.allProductsPending.put(idProduct, totalCount);
    }

    private int calculate(int countCache, FoodOrder foodOrder) {
        return (!foodOrder.getArchived()) ? (countCache + foodOrder.getAmount()) : (countCache - foodOrder.getAmount());
    }

    public void addNewProduct(int id) {
        this.allProductsPending.put(id, 0);
    }

    public Integer getProductPending(Integer idProduct) {
        return this.allProductsPending.getOrDefault(idProduct, 0);
    }
}
