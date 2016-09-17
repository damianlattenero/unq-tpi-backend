package beans.model;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name="FoodOrder")
public class FoodOrder {


    @Id()
    @GeneratedValue()
    @Column(name = "FoodOrder_ID")
    private int id;

    @Column(name = "moment")
    private DateTime moment;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @ManyToOne
    private Place place;

    @Column(name = "amount")
    private int amount;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderState state;

    public FoodOrder(Product product, User user) {
        this(product, user, 1);
    }

    private FoodOrder(Product product, User user, int amount) {
        this();
        this.moment = DateTime.now();
        this.product = product;
        this.user = user;
        this.place = user.getPlace();
        this.amount = amount;
        this.state = new OrderStatePending(this);
    }

    public FoodOrder() {

    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void cook() {
        this.state.cook();
    }

    public void cancel() {
        this.state.cancel();
    }

    public boolean isCooked() {
        return this.state.isCooked();
    }

    public boolean isPending() {
        return this.state.isPending();
    }

    public boolean isCanceled() {
        return this.state.isCanceled();
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}