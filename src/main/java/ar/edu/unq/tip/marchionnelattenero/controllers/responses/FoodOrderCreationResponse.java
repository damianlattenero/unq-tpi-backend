package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;

public class FoodOrderCreationResponse {

    private String name;
    private String description;
    private int amount;
    private Integer id;


    public FoodOrderCreationResponse(String name, String description, int amount, int id) {
        this.setName(name);
        this.setDescription(description);
        this.setAmount(amount);
        this.setId(id);
    }


    public static FoodOrderCreationResponse build(FoodOrder foodOrder) {
        return new FoodOrderCreationResponse(foodOrder.getProduct().getName(), foodOrder.getProduct().getDescription(), foodOrder.getAmount(), foodOrder.getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
