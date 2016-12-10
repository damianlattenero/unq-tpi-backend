package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

/**
 * Created by damian on 08/12/16.
 */
public class UserModelPlaceRR {
    private String place;
    private String userId;

    public UserModelPlaceRR(String place) {
        this.place = place;
    }

    public UserModelPlaceRR() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public static UserModelPlaceRR build(UserModel user) {
        return new UserModelPlaceRR(user.getPlace().toString());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
