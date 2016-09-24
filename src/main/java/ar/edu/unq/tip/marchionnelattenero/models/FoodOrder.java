package ar.edu.unq.tip.marchionnelattenero.models;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "FoodOrder")
public class FoodOrder {


    @Id()
    @GeneratedValue()
    @Column(name = "FoodOrder_ID")
    private int id;

    @Column(name = "moment")
    private Date moment;

    @ManyToOne
    private Product product;

    @Column(name = "amount")
    private int amount;

    /*

        @ManyToOne
        private User user;

        @ManyToOne
        private Place place;



            @OneToOne(cascade = CascadeType.ALL)
            private OrderState state;

    */
    public FoodOrder() {}

    public FoodOrder(Product product) {
        this(product, 1);
    }

    public FoodOrder(Product product, int amount) {
        this.moment = new Date(DateTime.now().toInstant().getMillis());
        this.product = product;
        this.amount = amount;
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
}
