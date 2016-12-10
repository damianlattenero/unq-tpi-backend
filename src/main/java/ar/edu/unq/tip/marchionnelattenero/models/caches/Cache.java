package ar.edu.unq.tip.marchionnelattenero.models.caches;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserModelRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
    private Map<UserModel, CacheProductPending> usersPending;

    //Mapa de Productos Pendientes x Ubicacion
    //Map<ProductId, Cant>
    private Map<Place, CacheProductPending> placesPending;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserModelRepository userModelRepository;


    public Cache() {

        productsPending = new CacheProductPending();
        usersPending = new HashMap<UserModel, CacheProductPending>();
        placesPending = new HashMap<Place, CacheProductPending>();
        for (Place place : Place.values()) {
            placesPending.put(place, new CacheProductPending());
        }

        final Cache previous = INSTANCE.getAndSet(this);
        if (previous != null)
            throw new IllegalStateException("Second singleton " + this + " created after " + previous);
    }

    public static Cache getInstance() {
        return INSTANCE.get();
    }

    public void addFoodOrder(FoodOrder foodOrder) {
        this.productsPending.addFoodOrder(foodOrder);

        this.setUsersByUserId(this.usersPending, foodOrder.getUser().getUserId(), foodOrder);

        this.setPlaces(this.placesPending, foodOrder.getUser().getPlace(), foodOrder);
    }


    public Integer getProductPending(Integer productId) {
        return this.productsPending.getProductPending(productId);
    }

    public CacheProductPending getProductsPending() {
        return productsPending;
    }

    public Map<UserModel, CacheProductPending> getUsersPending() {
        return usersPending;
    }

    public Map<Place, CacheProductPending> getPlacesPending() {
        return placesPending;
    }

    public void setUsersByUserId(Map<UserModel, CacheProductPending> usersPending, String userId, FoodOrder foodOrder) {
        UserModel userModel = this.userModelRepository.findByUserId(userId);
        if (!usersPending.keySet().stream().filter(user -> user.getUserId().equals(userId)).findAny().isPresent()) {
            CacheProductPending cacheProductPending = new CacheProductPending();
            cacheProductPending.addFoodOrder(foodOrder);
            this.getUsersPending().put(userModel, cacheProductPending);
        } else {
            this.getUsersPending().get(userModel).addFoodOrder(foodOrder);
        }

    }

    private void setPlaces(Map<Place, CacheProductPending> placesPending, Place place, FoodOrder foodOrder) {
        if (!placesPending.keySet().stream().filter(place1 -> place1 == place).findAny().isPresent()) {
            CacheProductPending cacheProductPending = new CacheProductPending();
            cacheProductPending.addFoodOrder(foodOrder);
            this.placesPending.put(place, cacheProductPending);
        } else {
            this.placesPending.get(place).addFoodOrder(foodOrder);
        }
    }

    public UserModel getUserByUserId(String userId) {
        Optional<UserModel> maybe = this.usersPending.keySet().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();

        if (!maybe.isPresent()) {
            return this.userModelRepository.findByUserId(userId);
        } else {
            return maybe.get();
        }
    }

}


