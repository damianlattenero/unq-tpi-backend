package model;

public class PendingOrderState extends OrderState {
    private Order order;

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
        this.order.setState(new CookedOrderState());
    }

    @Override
    public void cancel() {
        this.order.setState(new CanceledOrderState());
    }
}
