package model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderStatePending extends OrderState {

    public OrderStatePending(Order order) {
        super(order);
        order.getProduct().addOrder(order);
    }

    @Override
    public boolean isPending() {
        return true;
    }

    @Override
    public boolean isCooked() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void cook() {
        this.order.setState(new OrderStateCooked(this.order));
    }

    @Override
    public void cancel() {
        this.order.setState(new OrderStateCanceled(this.order));
    }
}
