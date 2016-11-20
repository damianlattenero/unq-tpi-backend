package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserModelRepository;
import com.jcabi.aspects.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Loggable
@Service("loginService")
public class Login {

    //@Loggable
    @Autowired
    private UserModelRepository userModelRepository;
    @Autowired
    private UserTokenService userTokenService;
/*
    @Autowired
    private FakeClass fake;
*/

    @Loggable
    @Transactional
    public UserModel signUp(String name, String nickname, String email) {
        UserModel user = new UserModel(name, nickname, email);
        this.userModelRepository.save(user);
        this.userTokenService.create(user);
//        this.fake.afterSignUp(user);
        return user;
    }

/*
    @Loggable
    @Transactional
    public UserModel signUpWithCredentials(Userinfoplus userinfoplus, GoogleOauthCredential googleOauthCredential) {
        UserModel newUser = this.signUp(userinfoplus.getName(),userinfoplus.getNickName(), userinfoplus.getEmail());
        newUser.setGoogleOauthCredential(googleOauthCredential);
        newUser.setPicture(userinfoplus.getPicture());
        this.userModelRepository.update(newUser);
        return newUser;
    }
*/

    @Transactional
    public List<UserModel> findAll() {
        return this.userModelRepository.findAll();
    }

    public UserModel getUserByID(Integer id) {
        return this.userModelRepository.findById(id);
    }

}
