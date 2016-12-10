package ar.edu.unq.tip.marchionnelattenero.controllers;

import ar.edu.unq.tip.marchionnelattenero.controllers.requests.UserModelPlaceRR;
import ar.edu.unq.tip.marchionnelattenero.controllers.requests.UserAuthorization;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.LoginResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.LogoutResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.UserResponse;
import ar.edu.unq.tip.marchionnelattenero.models.Place;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.UserToken;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserModelRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
import ar.edu.unq.tip.marchionnelattenero.services.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("userModels")
@Controller("loginController")
public class LoginController {

    @Autowired
    private Login login;
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Autowired
    private UserModelRepository userModelRepository;

    @POST
    @Path("changeUserPlace")
    @Consumes("application/json")
    @Produces("application/json")
    public UserModelPlaceRR changeUserPlace(UserModelPlaceRR userModelPlaceRR) {
        UserModel userModel = this.userModelRepository.findByUserId(userModelPlaceRR.getUserId());
        userModel.setPlace(Place.valueOf(userModelPlaceRR.getPlace()));
        this.userModelRepository.update(userModel);
        //devuelvo un eco que el front recibira para corroborar que funcionó
        return UserModelPlaceRR.build(this.userModelRepository.findByUserId(userModel.getUserId()));
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public LoginResponse loginUser(UserAuthorization userAuthorization) {
        System.out.println(" - loginUser - ");
        System.out.println("UserAuthorization: token: " + userAuthorization.getToken());
        System.out.println("UserAuthorization: userId : " + userAuthorization.getUserId());
        LoginResponse response = new LoginResponse();

        UserModel user = this.userModelRepository.findByUserId(userAuthorization.getUserId());
        if (user != null) {
            System.out.println("User: " + user.getName());
            UserToken newUserToken = new UserToken(userAuthorization.getToken(), user);
            UserToken userToken = this.userTokenRepository.findByUser(user);

            //Si aun no esta loggeado, lo registro
            if (userToken != null) {
                response = LoginResponse.userWasSignedBefore(userToken.getToken(), user);
            } else {
                this.userTokenRepository.save(newUserToken);
                newUserToken = this.userTokenRepository.findByUser(user);
                Boolean isSignedIn = (newUserToken != null);
                response = LoginResponse.SignIn((!isSignedIn) ? "" : newUserToken.getToken(), user, isSignedIn);
            }
        } else {
            response.setMessage("El usuario no existe!");
        }

        System.out.println("response: " + response.getMessage());
        return response;
    }

    @POST
    @Path("logout")
    @Consumes("application/json")
    @Produces("application/json")
    public LogoutResponse logoutUser(UserAuthorization userAuthorization) {
        System.out.println(" - logoutUser - ");
        System.out.println("UserAuthorization: token: " + userAuthorization.getToken());
        System.out.println("UserAuthorization: userId : " + userAuthorization.getUserId());
        LogoutResponse response = new LogoutResponse();

        UserToken userToken = this.userTokenRepository.findByUserToken(userAuthorization.getToken());

        //Deberia estar registrado
        if (userToken != null) {
            System.out.println("UserToken From: " + userToken.getUserModel().getNickname());
            this.userTokenRepository.delete(userToken);
            response = LogoutResponse.LogoutResponseWithToken(userAuthorization.getToken());
        } else {
            UserModel user = this.userModelRepository.findByUserId(userAuthorization.getUserId());
            if (user != null) {
                System.out.println("UserModel: " + user.getNickname());
                userToken = this.userTokenRepository.findByUser(user);

                //Deberia estar registrado
                if (userToken != null) {
                    System.out.println("UserToken From: " + userToken.getUserModel().getNickname());
                    this.userTokenRepository.delete(userToken);
                }
                response = LogoutResponse.LogoutResponseNoRegister(userToken.getToken());
            }
        }
        return response;
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public List<UserModel> getAll() {
        return this.login.findAll();
    }

    @GET
    @Path("listUsers")
    @Produces("application/json")
    public List<UserResponse> listUserModel() {
        List<UserResponse> result = new ArrayList<UserResponse>();
        for (UserModel user : this.login.findAll()) {
            result.add(UserResponse.build(user));
        }
        return result;
    }

    @GET
    @Path("myUserInfo")
    @Produces("application/json")
    public UserResponse findUserLogin(@QueryParam("token") String token) {
        UserModel user = (this.userTokenRepository.findByUserToken(token)).getUserModel();
        return UserResponse.build(user);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public UserResponse findUserInfo(@PathParam("id") Integer id) {
        UserModel user = this.login.getUserByID(id);
        return UserResponse.build(user);
    }

}
