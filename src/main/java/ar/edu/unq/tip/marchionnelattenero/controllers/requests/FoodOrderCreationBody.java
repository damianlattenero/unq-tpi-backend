package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

import ar.edu.unq.tip.marchionnelattenero.models.Product;

public class FoodOrderCreationBody {
    private String productName;
    private String productDescription;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
