package ar.edu.unq.tip.marchionnelattenero.controllers.requests;

public class ProductCreationBody {
    private String name;
    private int stock;
    private int pointCost;

    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public int getPointCost() {
        return pointCost;
    }

    public int getStock() {
        return stock;
    }

}
