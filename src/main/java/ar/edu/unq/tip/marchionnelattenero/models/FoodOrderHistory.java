package ar.edu.unq.tip.marchionnelattenero.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FoodOrderHistory")
public class FoodOrderHistory {


    @Id()
    @GeneratedValue()
    @Column(name = "FoodOrderHistory_ID")
    private int id;

    @Column(name = "moment")
    private Timestamp moment;

    @OneToMany
    private Set<Product> products;

    @Column(name = "amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    private FoodOrderState state;

    public FoodOrderHistory() {
    }

    public FoodOrderHistory(Timestamp moment, Product product, String state, int amount) {
        this(moment, product, FoodOrderState.valueOf(state), amount);
    }

    public FoodOrderHistory(Timestamp moment, Product product, FoodOrderState state, int amount) {
        this.moment = moment;
        this.products = new HashSet<>();
        this.products.add(product);
        this.state = state;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Set<Product> getProduct() {
        return products;
    }

    public FoodOrderState getState() {
        return state;
    }

    public int getAmount() {
        return amount;
    }

    public Timestamp getMoment() {
        return moment;
    }

}
