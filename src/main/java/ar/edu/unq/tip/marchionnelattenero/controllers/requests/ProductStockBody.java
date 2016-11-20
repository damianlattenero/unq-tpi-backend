package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class ProductStockBody {
    private Integer productId;
    private Boolean hasStock;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }
}
