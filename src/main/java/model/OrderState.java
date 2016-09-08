package model;

public abstract class OrderState {

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
