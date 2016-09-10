package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.concurrent.ConcurrentLinkedQueue;

@Entity
@Table(name="Product")
public class Product extends BaseEntity {

    private int pendingCount;

    private ConcurrentLinkedQueue<Order> ordersPending;

    private ConcurrentLinkedQueue<Order> ordersProcessed;

    public Product() {
        pendingCount = 0;
        ordersPending = new ConcurrentLinkedQueue<Order>();
        ordersProcessed = new ConcurrentLinkedQueue<Order>();
    }

    public Product(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public void addOrder(Order order)
    {
        this.ordersPending.add(order);
        pendingCount += order.getAmount();
    }

    public void removeOrder(Order order)
    {
        this.ordersProcessed.add(order);
        this.ordersPending.remove(order);
        pendingCount -= order.getAmount();
    }

    public int getPendingCount()
    {
        return this.pendingCount;
    }

}
