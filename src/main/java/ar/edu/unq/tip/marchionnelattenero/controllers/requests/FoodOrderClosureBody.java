package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class FoodOrderClosureBody {
    private long from;
    private long to;
    String userId;


    @Override
    public String toString() {
        return "{ " +
                "  From: " + this.from +
                ", To: " + this.to +
                "}";
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
