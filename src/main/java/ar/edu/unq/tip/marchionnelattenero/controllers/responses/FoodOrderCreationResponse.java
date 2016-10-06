package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FoodOrderCreationResponse {
    private int id;
    private int productAmount;
    //TODO Pensar si enviar en vez de Date los milisecs para que se vea hora
    private long moment;
    private String productDescription;
    private String productName;


    //TODO PENSAR BIEN QUE RESPONDE, POR AHORA LA CANTIDAD DE PRODS  PENDIENTES


    public FoodOrderCreationResponse(String name, String description, int productAmount, int id, long moment) {

        this.productName = name;
        this.productDescription = description;
        this.productAmount = productAmount;
        this.id = id;
        this.moment = moment;

    }

    public static FoodOrderCreationResponse build(FoodOrder foodOrder) {
        //TODO: ACTUALIZAR LA CANTIDAD DEL MAPA
        return new FoodOrderCreationResponse(
                foodOrder.getProduct().getName(),
                foodOrder.getProduct().getDescription(),
                foodOrder.getAmount(),
                foodOrder.getId(),
                foodOrder.getMoment().getTime()
        );
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


}
