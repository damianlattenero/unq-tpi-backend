package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

public class LogoutResponse {

    private Boolean isAuthenticated = false;
    private String token;
    private Boolean hasTokenRegister = false;

    public LogoutResponse() {
    }

    public LogoutResponse(Boolean isAuthenticated, String token, Boolean hasTokenRegister) {
        this.isAuthenticated = isAuthenticated;
        this.token = token;
        this.hasTokenRegister = hasTokenRegister;
    }

    public static LogoutResponse LogoutResponseNoRegister(String token) {
        return new LogoutResponse(true, token, false);
    }

    public static LogoutResponse LogoutResponseWithToken(String token) {
        return new LogoutResponse(true, token, true);
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getHasTokenRegister() {
        return hasTokenRegister;
    }

    public void setHasTokenRegister(Boolean hasTokenRegister) {
        this.hasTokenRegister = hasTokenRegister;
    }
}
