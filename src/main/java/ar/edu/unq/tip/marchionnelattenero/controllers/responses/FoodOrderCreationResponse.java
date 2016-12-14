package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;

import java.util.List;
import java.util.stream.Collectors;

public class FoodOrderCreationResponse {
    private int id;
    private int productAmount;
    private long moment;
    private String productDescription;
    private String productName;
    private String user;
    private String state;

    public FoodOrderCreationResponse(int id, long moment, String name, String description, int productAmount, String user, String state) {
        this.id = id;
        this.moment = moment;
        this.productName = name;
        this.productDescription = description;
        this.productAmount = productAmount;
        this.user = user;
        this.state = state;
    }

    public static FoodOrderCreationResponse build(FoodOrder foodOrder) {
        return new FoodOrderCreationResponse(
                foodOrder.getId(),
                foodOrder.getMoment().getTime(),
                foodOrder.getProduct().getName(),
                foodOrder.getProduct().getDescription(),
                foodOrder.getAmount(),
                foodOrder.getUser().getNickname(),
                ConvertStateToString(foodOrder.getState())
        );
    }

    private static String ConvertStateToString(FoodOrderState state) {
        String message = "";
        switch (state) {
            case ORDER:
                message = "Producto Ordenado";
                break;

            case CANCELORDER:
                message = "Cancelación de Prod. Ordenado";
                break;

            case COOKED:
                message = "Cocción de Producto";
                break;

            case CANCELCOOKED:
                message = "Cancelación de Cocción";
                break;

            default:
                message = state.toString();
                break;
        }
        return message;
    }

    public static List<FoodOrderCreationResponse> buildMany(List<FoodOrder> applicationRequests) {
        return applicationRequests.stream().map(FoodOrderCreationResponse::build).collect(Collectors.toList());
    }

    public long getMoment() {
        return moment;
    }

    public void setMoment(long moment) {
        this.moment = moment;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
