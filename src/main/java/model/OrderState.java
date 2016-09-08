package model;

public abstract class OrderState {

    public abstract boolean isPending();

    public abstract boolean isCooked();

    public abstract boolean isCanceled();

    public abstract void cook();

    public abstract void cancel();

}
