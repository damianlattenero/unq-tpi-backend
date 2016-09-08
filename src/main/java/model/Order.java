package model;

import org.joda.time.DateTime;

public class Order {
    private DateTime moment;
    private Product product;
    private User user;
    private Place place;
    private int amount;
    private OrderState state;

    public void setState(OrderState state) {
        this.state = state;
    }
}
