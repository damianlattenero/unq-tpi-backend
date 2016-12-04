package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.caches.CacheProductPending;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class CachePendingsResponse {

    public static String build(Map<Place, CacheProductPending> cacheMap) {
        System.out.println("Mapeando cache:");
        String jsonInString = "";

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(cacheMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return  jsonInString;
    }

    public static String build2(Map<UserModel, CacheProductPending> cacheMap) {
        System.out.println("Mapeando cache:");
        String jsonInString = "";

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(cacheMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return  jsonInString;
    }
}
