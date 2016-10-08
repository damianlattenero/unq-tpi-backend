package ar.edu.unq.tip.ml.controllers.responses;


import ar.edu.unq.tip.ml.models.Cache;
import ar.edu.unq.tip.ml.models.Product;

public class ProductPendingResponse {

    private int productPending;

    public ProductPendingResponse(int productPending) {
        this.productPending = productPending;
    }

    public static ProductPendingResponse build(Product product) {
        return new ProductPendingResponse(
                Cache.getInstance().getPending(product.getId())
        );
    }

    public int getProductPending() {
        return productPending;
    }

    public void setProductPending(int productPending) {
        this.productPending = productPending;
    }
}
