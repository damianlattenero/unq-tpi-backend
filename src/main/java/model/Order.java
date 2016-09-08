package model;

import org.joda.time.DateTime;

public class Order {
    private DateTime moment;
    private Product product;
    private User user;
    private Place place;
    private int amount;
    private OrderState state;

    public Order(Product product, User user, Place place) {
        this.moment = DateTime.now();
        this.product = product;
        this.user = user;
        this.place = place;
        this.amount = 1;
        this.state = new OrderStatePending();
    }

    public Order(Product product, User user, Place place, int amount) {
        this.moment = DateTime.now();
        this.product = product;
        this.user = user;
        this.place = place;
        this.amount = amount;
        this.state = new OrderStatePending();
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
