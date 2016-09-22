package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

import ar.edu.unq.tip.marchionnelattenero.models.Product;

public class FoodOrderCreationBody{
    private int idProduct;
    private int amount;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
