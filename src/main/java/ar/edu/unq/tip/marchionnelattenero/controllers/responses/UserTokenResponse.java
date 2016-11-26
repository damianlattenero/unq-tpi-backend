package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

public class UserTokenResponse {

    private boolean isAuthenticated = false;
    private String token;

    private String name;
    private String nickname;
    private String email;

    public UserTokenResponse() {
    }

    public UserTokenResponse(boolean isAuthenticated, String name, String nickname, String email) {
        this.isAuthenticated = isAuthenticated;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public UserTokenResponse(String token, boolean isAuthenticated, String name, String nickname, String email) {
        this.token = token;
        this.isAuthenticated = isAuthenticated;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
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
}
