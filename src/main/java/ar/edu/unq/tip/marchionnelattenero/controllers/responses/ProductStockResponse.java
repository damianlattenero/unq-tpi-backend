package ar.edu.unq.tip.marchionnelattenero.controllers.responses;

import ar.edu.unq.tip.marchionnelattenero.models.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductStockResponse {

    private Boolean hasStock;

    public ProductStockResponse(Boolean hasStock) {
        this.setHasStock(hasStock);
    }

    public static ProductStockResponse build(Product product) {
        return new ProductStockResponse(product.getHasStock());
    }

    public static List<ProductStockResponse> buildMany(List<Product> all) {
        return all.stream().map(ProductStockResponse::build).collect(Collectors.toList());
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }
}
