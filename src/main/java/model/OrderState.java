package model;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "OrderState")
public abstract class OrderState {

    @Id()
    @GeneratedValue()
    @Column(name = "ORDER_STATE_ID")
    private int id;

    @Column(name = "order")
    protected Order order;

    @Column(name = "moment")
    private DateTime moment;

    public OrderState(Order order) {
        this.order = order;
        this.moment = DateTime.now();
    }

    public abstract boolean isPending();

    public abstract boolean isCooked();

    public abstract boolean isCanceled();

    public abstract void cook();

    public abstract void cancel();

}
