package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserToken;
import ar.edu.unq.tip.marchionnelattenero.models.caches.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.caches.CacheProductPending;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class CachePendingsResponse {

    public static String buildPlaces() {
        Map<Place, CacheProductPending> cacheResponse = new HashMap<>();
        cacheResponse.put(Place.COCINA, Cache.getInstance().getProductsPending());

        for (Place place : Cache.getInstance().getPlacesPending().keySet()) {
            if(place != Place.COCINA)
                cacheResponse.put(place, Cache.getInstance().getPlacesPending().get(place));
        }

        Gson gson = new Gson();
        String jsonInString = gson.toJson(cacheResponse);

        return jsonInString;
    }

    public static String buildUsers() {
        Map<UserToken, CacheProductPending> cacheResponse = Cache.getInstance().getUsersPending();

        Gson gson = new Gson();
        String jsonInString = gson.toJson(cacheResponse);

        return jsonInString;
    }
}
