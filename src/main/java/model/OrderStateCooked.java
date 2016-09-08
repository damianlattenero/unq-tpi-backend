package model;

import exception.InvalidTransitionException;

public class OrderStateCooked extends OrderState{
    public OrderStateCooked(Order order) {
        super(order);
    }

    @Override
    public boolean isPending() {
        return false;
    }

    @Override
    public boolean isCooked() {
        return true;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void cook() {
        throw new InvalidTransitionException("Can't pass from Cooked to Cook.");
    }

    //TODO:Think about the posibility for cancel a Cooked Order
    @Override
    public void cancel() {
        throw new InvalidTransitionException("Can't pass from Cooked to Cancel.");
    }
}
