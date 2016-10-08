package ar.edu.unq.tip.marchionnelattenero.models;


import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product{




    @Id()
    @GeneratedValue()
    @Column(name = "Product_ID")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;



    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {
    }

}
