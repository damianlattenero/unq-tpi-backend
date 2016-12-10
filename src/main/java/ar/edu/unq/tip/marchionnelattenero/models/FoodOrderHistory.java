package ar.edu.unq.tip.marchionnelattenero.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    @Column(name = "countOrder")
    private int countOrder;

    @Column(name = "countCancelOrder")
    private int countCancelOrder;

    @Column(name = "countCooked")
    private int countCooked;

    @Column(name = "countCancelCooked")
    private int countCancelCooked;

    public FoodOrderHistory() {
    }

    public FoodOrderHistory(Date date, Product product) {
        this(new Timestamp(date.getTime()), product);
        ;
    }

    public FoodOrderHistory(Timestamp moment, Product product) {
        this.moment = moment;
        this.product = product;
        this.countOrder = 0;
        this.countCancelOrder = 0;
        this.countCooked = 0;
        this.countCancelCooked = 0;
    }

    public int getId() {
        return id;
    }

    public Timestamp getMoment() {
        return moment;
    }

    public Product getProduct() {
        return product;
    }

    public void addAmount(FoodOrderState state, int amount) {
        switch (state) {
            case ORDER:
                this.countOrder += amount;
                break;

            case CANCELORDER:
                this.countCancelOrder += amount;
                break;

            case COOKED:
                this.countCooked += amount;
                break;

            case CANCELCOOKED:
                this.countCancelCooked += amount;
                break;

            default:
                break;
        }
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }

    public int getCountCancelOrder() {
        return countCancelOrder;
    }

    public void setCountCancelOrder(int countCancelOrder) {
        this.countCancelOrder = countCancelOrder;
    }

    public int getCountCooked() {
        return countCooked;
    }

    public void setCountCooked(int countCooked) {
        this.countCooked = countCooked;
    }

    public int getCountCancelCooked() {
        return countCancelCooked;
    }

    public void setCountCancelCooked(int countCancelCooked) {
        this.countCancelCooked = countCancelCooked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodOrderHistory that = (FoodOrderHistory) o;

        if (!moment.equals(that.moment)) return false;
        return product.equals(that.product);

    }

    @Override
    public int hashCode() {
        int result = moment.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
