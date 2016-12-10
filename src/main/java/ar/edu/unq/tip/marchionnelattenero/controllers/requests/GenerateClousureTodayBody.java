package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

/**
 * Created by damian on 10/12/16.
 */
public class GenerateClousureTodayBody {


    String userId;

    public GenerateClousureTodayBody(String userId) {
        this.userId = userId;
    }

    public GenerateClousureTodayBody() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
