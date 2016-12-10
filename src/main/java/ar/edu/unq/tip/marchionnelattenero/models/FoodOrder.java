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

    @ManyToOne
    private UserModel user;

    @Enumerated(EnumType.STRING)
    private FoodOrderState state;

    /*
        @ManyToOne
        private Place place;
    */

    @Column(name = "isReadyToDeliver")
    private Boolean isReadyToDeliver;

    @Column(name = "momentCooked")
    private Timestamp momentCooked;

    @Column(name = "archived")
    private Boolean archived;

    public FoodOrder() {
    }

    public FoodOrder(Product product, FoodOrderState state, int amount, UserModel user) {
        this.moment = new Timestamp(DateTime.now().getMillis());
        this.product = product;
        this.state = state;
        this.amount = amount;
        this.user = user;
        this.archived = false;
        this.isReadyToDeliver = false;
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

    public Timestamp getMoment() {
        return moment;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public void setArchived() {
        this.setArchived(true);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Boolean isReadyToDeliver() {
        return isReadyToDeliver;
    }

    public void setReadyToDeliver() {
        this.isReadyToDeliver = true;
        this.setMomentCooked(new Timestamp(DateTime.now().getMillis()));
    }

    public Timestamp getMomentCooked() {
        return momentCooked;
    }

    public void setMomentCooked(Timestamp momentCooked) {
        this.momentCooked = momentCooked;
    }
}

