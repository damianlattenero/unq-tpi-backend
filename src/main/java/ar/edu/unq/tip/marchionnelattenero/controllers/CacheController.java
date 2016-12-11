package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.responses.CachePendingsResponse;
import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.caches.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.caches.CacheProductPending;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("cache")
@Controller("cacheController")
public class CacheController {

    @GET
    @Path("places")
    @Produces("application/json")
    public String cachePlacesPending() {
        Map<Place, CacheProductPending> placesPending = Cache.getInstance().getPlacesPending();
        return CachePendingsResponse.buildPlaces();
    }

    @GET
    @Path("users")
    @Produces("application/json")
    public String cacheUsersPending() {
        Map<UserModel, CacheProductPending> usersPending = Cache.getInstance().getUsersPending();
        return CachePendingsResponse.buildUsers();
    }

    @GET
    @Path("cleanAll")
    @Produces("application/json")
    public String cleanAll() {
        Cache.getInstance().cleanAll();
        return "All cleaned";
    }

}
