package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;

public class UserResponse {
    private int id;
    private String name;
    private String nickname;
    private String email;
    private String place;

    public UserResponse(int id, String name, String nickname, String email, String place) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.place = place;
    }

    public static UserResponse build(UserModel userModel) {
        return new UserResponse(
                userModel.getId(),
                userModel.getName(),
                userModel.getNickname(),
                userModel.getEmail(),
                userModel.getPlace().toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
