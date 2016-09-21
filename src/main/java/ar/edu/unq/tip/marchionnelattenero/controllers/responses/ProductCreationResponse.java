package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.Product;

public class ProductCreationResponse {

    private String name;
    private int stock;
    private int pointCost;
    private Integer id;


    public ProductCreationResponse(String name, int stock, int pointCost, Integer id) {
        this.setName(name);
        this.setStock(stock);
        this.setPointCost(pointCost);
        this.setId(id);
    }


    public static ProductCreationResponse build(Product product) {
        return new ProductCreationResponse(product.getName(), product.getStock(), product.getPointCost(),product.getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPointCost() {
        return pointCost;
    }

    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
