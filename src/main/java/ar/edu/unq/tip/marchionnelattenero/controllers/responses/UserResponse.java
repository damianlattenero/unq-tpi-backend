package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;

public class UserResponse {
    private String userId;
    private String name;
    private String nickname;
    private String email;
    private String place;

    public UserResponse(String userId, String name, String nickname, String email, String place) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.place = place;
    }

    public static UserResponse build(UserModel userModel) {
        return new UserResponse(
                String.valueOf(userModel.getId()),
                userModel.getName(),
                userModel.getNickname(),
                userModel.getEmail(),
                userModel.getPlace().toString());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
