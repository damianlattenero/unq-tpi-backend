package ar.edu.unq.tip.marchionnelattenero.models;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "FoodOrder")
public class FoodOrder {


    @Id()
    @GeneratedValue()
    @Column(name = "FoodOrder_ID")
    private int id;

    @Column(name = "moment")
    private Timestamp moment;

    @ManyToOne
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Column(name = "user")
    private String user;

    /*
        @ManyToOne
        private Place place;

        @OneToOne(cascade = CascadeType.ALL)
        private OrderState state;
    */
    public FoodOrder() {
    }

    public FoodOrder(Product product, String user) {
        this(product, 1, user);
    }

    public FoodOrder(Product product, int amount, String user) {
        this.moment = new Timestamp(DateTime.now().getMillis());
        this.product = product;
        this.amount = amount;
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public Timestamp getMoment() {
        return moment;
    }
}
