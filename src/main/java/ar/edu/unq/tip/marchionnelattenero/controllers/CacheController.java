package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.CacheCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.requests.ProductCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.CachePendingsResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderHistoryCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductPendingResponse;
import ar.edu.unq.tip.marchionnelattenero.models.*;
import ar.edu.unq.tip.marchionnelattenero.models.caches.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.caches.CacheProductPending;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.net.CacheResponse;
import java.util.List;
import java.util.Map;

@Path("cache")
@Controller("cacheController")
public class CacheController {

/*
    @GET
    @Path("all")
    @Produces("application/json")
    public List<CachePendingsResponse> getAll() {
        return CachePendingsResponse.buildMany();
    }
*/

    @GET
    @Path("products")
    @Produces("application/json")
    public String cacheProductsPending() {
        return CachePendingsResponse.buildProductsPending(Cache.getInstance().getProductsPending());
    }

    @GET
    @Path("places")
    @Produces("application/json")
    public String cachePlacesPending() {
        System.out.println("Places");
        Map<Place, CacheProductPending> placesPending = Cache.getInstance().getPlacesPending();
        return CachePendingsResponse.buildPlaces(placesPending);
    }

    @GET
    @Path("users")
    @Produces("application/json")
    public String cacheUsersPending() {
        Map<UserToken, CacheProductPending> usersPending = Cache.getInstance().getUsersPending();
        System.out.println("Users");
        return CachePendingsResponse.buildUsers(usersPending);
    }

    @POST
    @Path("changeUserPlace")
    @Consumes("application/json")
    @Produces("application/json")
    public String changeUserPlace(@QueryParam("token") String token, CacheCreationBody cacheCreationBody) {
        Cache.getInstance().getUserByToken(token).getUserModel().setPlace(Place.valueOf(cacheCreationBody.getPlace()));
        System.out.println(cacheCreationBody.getPlace());
        return CachePendingsResponse.buildUsers(Cache.getInstance().getUsersPending());
    }


}
