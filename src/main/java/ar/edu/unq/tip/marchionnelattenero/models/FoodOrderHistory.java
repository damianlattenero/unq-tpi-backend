package ar.edu.unq.tip.marchionnelattenero.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
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
    @MapKeyColumn(name = "state")
    @Column(name="amount")
     */
//    @OneToMany(cascade = CascadeType.ALL)
//    @MapKeyEnumerated(EnumType.STRING)
/*
    @MapKey(name = "state")
    @MapKeyEnumerated(EnumType.STRING)
    @OneToMany(mappedBy = "FoodOrderHistory", cascade = CascadeType.ALL)
*/
    //@OneToMany(mappedBy = "foodOrderHistory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@MapKeyColumn(name = "state", insertable = false, updatable = false)

    /*
        @OneToMany(mappedBy = "foodOrderHistory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @MapKeyEnumerated(EnumType.STRING)
        @MapKeyColumn(name = "states")
         */
    //@OneToMany//(cascade={CascadeType.ALL,CascadeType.PERSIST})

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<FoodOrderState, MyInteger> amounts;

    public FoodOrderHistory() {
    }

    public FoodOrderHistory(Date date, Product product) {
        new FoodOrderHistory(new Timestamp(date.getTime()), product);
    }

    public FoodOrderHistory(Timestamp moment, Product product) {
        this.moment = moment;
        this.product = product;
        this.amounts = new HashMap<FoodOrderState, MyInteger>();
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

    public Map<FoodOrderState, MyInteger> getAmounts() {
        return amounts;
    }

    public void addAmount(FoodOrderState state, int amount) {
        int count = this.getAmount(state).getValue();
        this.amounts.put(state, new MyInteger(count + amount));
    }

    public MyInteger getAmount(FoodOrderState state) {
        return this.amounts.getOrDefault(state, new MyInteger(0));
    }

}
