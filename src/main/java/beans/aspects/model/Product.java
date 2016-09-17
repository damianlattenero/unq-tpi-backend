package beans.aspects.model;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;


    @OneToMany
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
