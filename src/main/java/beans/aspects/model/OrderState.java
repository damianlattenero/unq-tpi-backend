package beans.aspects.model;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "OrderState")
public abstract class OrderState {
    private static final long serialVersionUID = 1L;


    @Id()
    @GeneratedValue()
    @Column(name = "ORDER_STATE_ID")
    private int id;

    @ManyToOne
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
