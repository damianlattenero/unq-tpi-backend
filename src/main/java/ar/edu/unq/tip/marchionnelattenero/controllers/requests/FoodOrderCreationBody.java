package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class FoodOrderCreationBody {
    private int productId;
    private String userId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
