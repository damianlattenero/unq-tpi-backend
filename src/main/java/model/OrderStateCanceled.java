package model;

import exception.InvalidTransitionException;

public class OrderStateCanceled extends OrderState {
    public OrderStateCanceled(Order order) {
        super(order);
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
