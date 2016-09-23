package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;

public class FoodOrderCreationResponse {
    private int id;
    private int productAmount;
    private String productDescription;
    private String productName;

    //TODO PENSAR BIEN QUE RESPONDE, POR AHORA LA CANTIDAD DE PRODS  PENDIENTES


    public FoodOrderCreationResponse(String name, String description, int productAmount, int id) {

        this.productName = name;
        this.productDescription = description;
        this.productAmount = productAmount;
        this.id = id;

    }


    public static FoodOrderCreationResponse build(FoodOrder foodOrder) {
        //TODO: ACTUALIZAR LA CANTIDAD DEL MAPA
        return new FoodOrderCreationResponse(foodOrder.getProduct().getName(), foodOrder.getProduct().getDescription(), foodOrder.getAmount(), foodOrder.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
