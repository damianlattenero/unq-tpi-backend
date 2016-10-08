package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class FoodOrderCreationBody{
    private int productId;
    private int productAmount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
}
