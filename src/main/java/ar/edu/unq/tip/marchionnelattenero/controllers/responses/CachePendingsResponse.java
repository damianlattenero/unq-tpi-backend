package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.caches.CacheProductPending;
import com.google.gson.Gson;

import java.util.Map;

public class CachePendingsResponse {

    public static String buildPlaces(Map<Place, CacheProductPending> cacheMap) {
        Gson gson = new Gson();
        String jsonInString = gson.toJson(cacheMap);

        return jsonInString;
    }

    public static String buildUsers(Map<UserModel, CacheProductPending> cacheMap) {
        Gson gson = new Gson();
        String jsonInString = gson.toJson(cacheMap);

        return jsonInString;
    }


}
