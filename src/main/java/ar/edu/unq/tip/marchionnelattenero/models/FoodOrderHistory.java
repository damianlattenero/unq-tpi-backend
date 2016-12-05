package ar.edu.unq.tip.marchionnelattenero.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

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

/*
    @Column(name = "amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    private FoodOrderState state;
*/

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "FoodOrderHistory_Amounts", joinColumns = @JoinColumn(name = "FoodOrderHistory_ID"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "state")
    @Column(name = "amount")
    private Map<FoodOrderState, Integer> amounts;

    public FoodOrderHistory() {
        this.amounts = new HashMap<FoodOrderState, Integer>();
    }

    public FoodOrderHistory(Timestamp moment, Product product, FoodOrderState state, int amount) {
        this.moment = moment;
        this.product = product;
        this.amounts = new HashMap<FoodOrderState, Integer>();
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Timestamp getMoment() {
        return moment;
    }

    public Map<FoodOrderState, Integer> getAmounts() {
        return amounts;
    }

    public void addAmount(FoodOrderState state, int amount) {
        int count = this.getAmount(state);
        this.amounts.put(state, count + amount);
    }

    public Integer getAmount(FoodOrderState state) {
        return this.amounts.getOrDefault(state, 0);
    }

}
