package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderClosure;

import java.util.List;
import java.util.stream.Collectors;

public class FoodOrderClosureCreationResponse {
    private int id;
    private long moment;
    private long momentClosure;
    private String user;


    public FoodOrderClosureCreationResponse(int id, long moment, long momentClosure, String user) {
        this.id = id;
        this.moment = moment;
        this.momentClosure = momentClosure;
        this.user = user;
    }

    public static FoodOrderClosureCreationResponse build(FoodOrderClosure foodOrderClosure) {
        return new FoodOrderClosureCreationResponse(
                foodOrderClosure.getId(),
                foodOrderClosure.getMoment().getTime(),
                foodOrderClosure.getMomentClosure().getTime(),
                foodOrderClosure.getUser()
        );
    }

    public static List<FoodOrderClosureCreationResponse> buildMany(List<FoodOrderClosure> applicationRequests) {
        return applicationRequests.stream().map(FoodOrderClosureCreationResponse::build).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMoment() {
        return moment;
    }

    public void setMoment(long moment) {
        this.moment = moment;
    }

    public long getMomentClosure() {
        return momentClosure;
    }

    public void setMomentClosure(long momentClosure) {
        this.momentClosure = momentClosure;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
