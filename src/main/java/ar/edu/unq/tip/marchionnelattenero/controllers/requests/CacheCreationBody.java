package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

/**
 * Created by damian on 08/12/16.
 */
public class CacheCreationBody {
    private String place;

    public CacheCreationBody(String place) {
        this.place = place;
    }

    public CacheCreationBody() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
