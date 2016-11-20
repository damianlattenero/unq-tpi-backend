package ar.edu.unq.tip.marchionnelattenero.models.caches;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Place;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Cache {
    private static AtomicReference<Cache> INSTANCE = new AtomicReference<Cache>();
    private static volatile Cache instance = null;

    //Mapa de Productos Pendientes Totales
    //Map<ProductId, Cant>
    private CacheProductPending productsPending;

    //Mapa de Productos Pendientes x Usuario
    //Map<ProductId, Cant>
    private Map<String, CacheProductPending> usersPending;

    //Mapa de Productos Pendientes x Ubicacion
    //Map<ProductId, Cant>
    private Map<Place, CacheProductPending> placesPending;

    public Cache() {

        productsPending = new CacheProductPending();
        usersPending = new HashMap<String, CacheProductPending>();
        placesPending = new HashMap<Place, CacheProductPending>();

        final Cache previous = INSTANCE.getAndSet(this);
        if (previous != null)
            throw new IllegalStateException("Second singleton " + this + " created after " + previous);
    }

    public static Cache getInstance() {
        return INSTANCE.get();
    }

    public void addFoodOrder(FoodOrder foodOrder) {
        this.productsPending.addFoodOrder(foodOrder);
        this.usersPending.get(foodOrder.getUser()).addFoodOrder(foodOrder);
        this.placesPending.get(Place.MOSTRADOR1).addFoodOrder(foodOrder);
    }

    public Integer getProductPending(Integer productId) {
        return this.productsPending.getProductPending(productId);
    }
}

