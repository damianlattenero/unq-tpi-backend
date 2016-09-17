package beans.model;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;


    public Product() {
    }

    public Product(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

}
