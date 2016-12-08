package ar.edu.unq.tip.marchionnelattenero.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.persistence.*;


@Entity
@Table(name = "UserToken")
public class UserToken {

    @Id()
    @GeneratedValue()
    @Column(name = "TOKEN_ID")
    private int id;

    @Lob
    @Column(name = "TOKEN_STRING")
    private String token;

    @ManyToOne(optional = false)
    private UserModel userModel;

    public UserToken() {
    }

    public UserToken(String token, UserModel userModel) {
        this.token = token;
        this.userModel = userModel;
    }

    @Override
    public String toString() {
//        return "{'nickname':" + this.getUserModel().getNickname() +", " + "'userId':" + this.getUserModel().getUserId() + "}";
        Gson gson = new Gson();
        String jsonInString = gson.toJson(this);
        return jsonInString;
    }



    @Override
    public boolean equals(Object obj) {
        return this.getToken().equals(((UserToken) obj).getToken());
    }

    @Override
    public int hashCode() {
        return this.getToken().hashCode();
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
