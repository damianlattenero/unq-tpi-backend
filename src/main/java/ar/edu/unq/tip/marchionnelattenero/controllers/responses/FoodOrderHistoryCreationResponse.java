package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;

import java.util.List;
import java.util.stream.Collectors;

public class FoodOrderHistoryCreationResponse {
    private int id;
    private int productAmount;
    private long moment;
    private String productDescription;
    private String productName;
    private String state;

    public FoodOrderHistoryCreationResponse(int id, long moment, String name, String description, int productAmount, String state) {
        this.id = id;
        this.moment = moment;
        this.productName = name;
        this.productDescription = description;
        this.productAmount = productAmount;
        this.state = state;
    }

    public static FoodOrderHistoryCreationResponse build(FoodOrderHistory foodOrderHistory) {
        return new FoodOrderHistoryCreationResponse(
                foodOrderHistory.getId(),
                foodOrderHistory.getMoment().getTime(),
                foodOrderHistory.getProduct().getName(),
                foodOrderHistory.getProduct().getDescription(),
                foodOrderHistory.getAmount(),
                foodOrderHistory.getState().toString()
        );
    }

    public static List<FoodOrderHistoryCreationResponse> buildMany(List<FoodOrderHistory> applicationRequests) {
        return applicationRequests.stream().map(FoodOrderHistoryCreationResponse::build).collect(Collectors.toList());
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
