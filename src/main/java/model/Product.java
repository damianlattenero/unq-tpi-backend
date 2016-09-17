package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product extends BaseEntity {

    private Order order;

    public Product() {
    }

    public Product(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
