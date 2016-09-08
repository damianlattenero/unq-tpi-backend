package model;

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

    public OrderState(Order order) {
        this.order = order;
    }

    public abstract boolean isPending();

    public abstract boolean isCooked();

    public abstract boolean isCanceled();

    public abstract void cook();

    public abstract void cancel();

}
