package ar.edu.unq.tip.marchionnelattenero.models.caches;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserToken;
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
    private Map<UserToken, CacheProductPending> usersPending;

    //Mapa de Productos Pendientes x Ubicacion
    //Map<ProductId, Cant>
    private Map<Place, CacheProductPending> placesPending;

    @Autowired
    private UserTokenRepository userTokenRepository;


    public Cache() {

        productsPending = new CacheProductPending();
        usersPending = new HashMap<UserToken, CacheProductPending>();
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

        this.setUsersByToken(this.usersPending, foodOrder.getUser().getToken(), foodOrder);

        this.setPlacesByToken(this.placesPending, foodOrder.getUser().getUserModel().getPlace(), foodOrder);
        /*if (this.placesPending.containsKey(foodOrder.getUser().getToken())) {
            this.placesPending.get(foodOrder.getUser().getUserModel().getPlace()).addFoodOrder(foodOrder);
        } else {
            this.placesPending.put(foodOrder.getUser().getUserModel().getPlace(), new CacheProductPending());
        }
*/
    }



    public Integer getProductPending(Integer productId) {
        return this.productsPending.getProductPending(productId);
    }

    public CacheProductPending getProductsPending() {
        return productsPending;
    }

    public Map<UserToken, CacheProductPending> getUsersPending() {
        return usersPending;
    }

    public Map<Place, CacheProductPending> getPlacesPending() {
        return placesPending;
    }

    public void setUsersByToken(Map<UserToken, CacheProductPending> usersPending, String token, FoodOrder foodOrder) {
        UserToken userToken = null;
        if (!usersPending.keySet().stream().filter(user -> user.getToken().equals(token)).findAny().isPresent()) {
            userToken = this.userTokenRepository.findByUserToken(token);
            CacheProductPending cacheProductPending = new CacheProductPending();
            cacheProductPending.addFoodOrder(foodOrder);
            this.getUsersPending().put(userToken, cacheProductPending);
        } else {
            this.getUsersPending().get(foodOrder.getUser()).addFoodOrder(foodOrder);
        }

    }

    private void setPlacesByToken(Map<Place, CacheProductPending> placesPending, Place place, FoodOrder foodOrder) {
        UserToken userToken = null;
        if (!placesPending.keySet().stream().filter(place1 -> place1 == place).findAny().isPresent()) {
            CacheProductPending cacheProductPending = new CacheProductPending();
            cacheProductPending.addFoodOrder(foodOrder);
            this.placesPending.put(place, cacheProductPending);
        } else {
            this.placesPending.get(place).addFoodOrder(foodOrder);
        }
    }

    public UserToken getUserByToken(String token) {
        Optional<UserToken> maybe = this.usersPending.keySet().stream()
                .filter(userToken -> userToken.getToken().equals(token))
                .findFirst();

        if(!maybe.isPresent()){
            return this.userTokenRepository.findByUserToken(token);
        }else{
            return maybe.get();
        }
    }
}


