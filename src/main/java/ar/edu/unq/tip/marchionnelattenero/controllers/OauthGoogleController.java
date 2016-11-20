package ar.edu.unq.tip.marchionnelattenero.controllers;

import ar.edu.unq.tip.marchionnelattenero.controllers.requests.UserAuthorization;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.UserTokenResponse;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.UserToken;
import ar.edu.unq.tip.marchionnelattenero.models.oauth.GoogleOauthCredential;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
import ar.edu.unq.tip.marchionnelattenero.services.Login;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("oauth")
@Controller("oauthGoogleController")
public class OauthGoogleController {

    @Autowired
    private Login login;
    @Autowired
    private UserTokenRepository userTokenRepository;
/*
    @Autowired
    private GoogleCredentialsService googleCredentialsService;

    @POST
    @Path("google")
    @Consumes("application/json")
    @Produces("application/json")
    public UserTokenResponse loginUser(UserAuthorization userAuthorization) {
        Credential credential = this.googleCredentialsService.create(userAuthorization.getAuthorizationCode());
        Userinfoplus userinfoplus = this.googleCredentialsService.getUserinfo(credential);
        GoogleOauthCredential googleOauthCredential = this.googleCredentialsService.get(userinfoplus.getId());
        UserModel user = this.login.signUpWithCredentials(userinfoplus, googleOauthCredential);
        UserToken token = this.userTokenRepository.findByUserId(user.getId());
        return new UserTokenResponse(token.getToken());
    }
*/

}
