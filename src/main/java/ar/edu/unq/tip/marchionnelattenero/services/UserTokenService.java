package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
import org.springframework.stereotype.Service;

@Service("userTokenService")
public class UserTokenService {

    private UserTokenRepository userTokenRepository;

    /*@Transactional
    public UserToken create(UserModel userModel){
        UserToken token = new UserToken();
        token.setUserModel(userModel);
        this.getUserTokenRepository().save(token);
        return token;
    }

    public UserTokenRepository getUserTokenRepository() {
        return userTokenRepository;
    }

    @Autowired
    public void setUserTokenRepository(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }*/
}
