package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class FoodOrderClosureBody {
    private String user;
    private long from;
    private long to;

    @Override
    public String toString() {
        return "{ " +
                "User: " + this.user +
                ", From: " + this.from +
                ", To: " + this.to +
                "}" ;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

}
