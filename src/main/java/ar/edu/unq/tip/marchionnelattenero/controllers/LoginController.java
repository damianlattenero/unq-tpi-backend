package ar.edu.unq.tip.marchionnelattenero.controllers;

import ar.edu.unq.tip.marchionnelattenero.controllers.requests.UserAuthorization;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.UserResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.UserTokenResponse;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
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
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public UserTokenResponse loginUser(UserAuthorization userAuthorization) {
        System.out.println("UserAuthorization: token: " + userAuthorization.getToken());
        System.out.println("UserAuthorization: userId : " + userAuthorization.getUserId());

        UserModel user = this.userTokenRepository.findByUserToken(userAuthorization.getUserId());
//        UserModel user = this.userModelRepository.findByUserId(userAuthorization.getUserId());
        System.out.println("User: " + ((user == null) ? "." : user.getName()) );
        UserTokenResponse response = (user == null) ? new UserTokenResponse()
                : new UserTokenResponse(userAuthorization.getToken(), true, user.getName(), user.getNickname(), user.getEmail());
        return response;

//        return new UserTokenResponse(true, "Cristian", "Cris", "cd@mail");
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
        UserModel user = this.userTokenRepository.findByUserToken(token);
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
