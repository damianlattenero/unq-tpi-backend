package model;

import exception.InvalidTransitionException;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderStateCanceled extends OrderState {

    public OrderStateCanceled(Order order) {
        super(order);
        order.getProduct().removeOrder(order);
    }

    @Override
    public boolean isPending() {
        return false;
    }

    @Override
    public boolean isCooked() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return true;
    }

    @Override
    public void cook() {
        throw new InvalidTransitionException("Can't pass from Canceled to Cook.");
    }

    @Override
    public void cancel() {
        throw new InvalidTransitionException("Can't pass from Canceled to Cancel.");
    }
}
