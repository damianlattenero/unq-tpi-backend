package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;

public class LoginResponse {

    private boolean isAuthenticated = false;
    private boolean isSignedIn = false;
    private String token;

    private String name;
    private String nickname;
    private String email;
    private String message;

    public LoginResponse() {
    }

    public LoginResponse(String token, boolean isAuthenticated, String name, String nickname, String email) {
        this.token = token;
        this.isAuthenticated = isAuthenticated;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public static LoginResponse userWasSignedBefore(String token, UserModel user) {
        LoginResponse response = new LoginResponse(token, true, user.getName(), user.getNickname(), user.getEmail());
        response.setSignedIn(false);
        response.setMessage("El usuario ya esta registrado con otro dispositivo");
        return response;
    }

    public static LoginResponse SignIn(String token, UserModel user, Boolean isSignedIn) {
        LoginResponse response = new LoginResponse(token, true, user.getName(), user.getNickname(), user.getEmail());
        response.setSignedIn(isSignedIn);
        response.setMessage(isSignedIn ? "Se registro al usuario" : "No se logro registrar al usuario");
        return response;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public void setSignedIn(boolean signedIn) {
        isSignedIn = signedIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
