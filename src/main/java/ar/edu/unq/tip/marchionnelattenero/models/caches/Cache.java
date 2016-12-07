package ar.edu.unq.tip.marchionnelattenero.models.caches;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Cache {
    private static AtomicReference<Cache> INSTANCE = new AtomicReference<Cache>();
    private static volatile Cache instance = null;
    @Autowired
    private UserTokenRepository userTokenRepository;

    //Mapa de Productos Pendientes Totales
    //Map<ProductId, Cant>
    private CacheProductPending productsPending;

    //Mapa de Productos Pendientes x Usuario
    //Map<ProductId, Cant>
    private Map<String, CacheProductPending> usersPending;

    //Mapa de Productos Pendientes x Ubicacion
    //Map<ProductId, Cant>
    private Map<Place, CacheProductPending> placesPending;

    private UserModel user;

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

//        CacheProductPending usersProductsPending = this.usersPending.getOrDefault(foodOrder.getUser(), new CacheProductPending());
//        usersProductsPending.addFoodOrder(foodOrder);
//
//        CacheProductPending placesProductsPending = this.placesPending.getOrDefault(Place.MOSTRADOR1, new CacheProductPending());
//        placesProductsPending.addFoodOrder(foodOrder);
    }

    public Integer getProductPending(Integer productId) {
        return this.productsPending.getProductPending(productId);
    }

    public UserModel getUserByToken(String token) {
        if(user == null){
            user = this.userTokenRepository.findByUserToken(token).getUserModel();
        }
        return user;
    }
}

