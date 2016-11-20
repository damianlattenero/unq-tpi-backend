package ar.edu.unq.tip.marchionnelattenero.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "FoodOrderHistory")
public class FoodOrderHistory {


    @Id()
    @GeneratedValue()
    @Column(name = "FoodOrderHistory_ID")
    private int id;

    @Column(name = "moment")
    private Timestamp moment;

    @ManyToOne
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    private FoodOrderState state;

    public FoodOrderHistory() {
    }

    public FoodOrderHistory(Timestamp moment, Product product, FoodOrderState state, int amount) {
        this.moment = moment;
        this.product = product;
        this.state = state;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public FoodOrderState getState() {
        return state;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.setAmount(this.amount + amount);
    }

    public Timestamp getMoment() {
        return moment;
    }

}