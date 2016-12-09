package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.responses.CachePendingsResponse;
import ar.edu.unq.tip.marchionnelattenero.models.*;
import ar.edu.unq.tip.marchionnelattenero.models.caches.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.caches.CacheProductPending;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
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
        Map<UserToken, CacheProductPending> usersPending = Cache.getInstance().getUsersPending();
        return CachePendingsResponse.buildUsers();
    }

}
