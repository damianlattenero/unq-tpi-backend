package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class FoodOrderClosureBody {
    private long momentClosure;
    private String user;

    public long getMomentClosure() {
        return momentClosure;
    }

    public void setMomentClosure(long momentClosure) {
        this.momentClosure = momentClosure;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
