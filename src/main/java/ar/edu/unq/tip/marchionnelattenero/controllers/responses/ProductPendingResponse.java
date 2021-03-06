package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.Product;

public class ProductPendingResponse {

    private int productPending;

    public ProductPendingResponse(int productPending) {
        this.productPending = productPending;
    }

    public static ProductPendingResponse build(Product product) {
        return new ProductPendingResponse(
                0);
    }

    public int getProductPending() {
        return productPending;
    }

    public void setProductPending(int productPending) {
        this.productPending = productPending;
    }
}
