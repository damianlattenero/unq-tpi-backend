package ar.edu.unq.tip.marchionnelattenero.models;


import javax.persistence.*;
import javax.swing.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Product")
public class Product {

    @Id()
    @GeneratedValue()
    @Column(name = "PRODUCT_ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Transient
    private ImageIcon photo;
    @Column(name = "stock")
    private int stock;

    @Column(name = "point_cost")
    private int pointCost;

    public Product(String name, int stock, int pointCost) {
        this.name = name;
        this.stock = stock;
        this.pointCost = pointCost;
    }
    public Product(){}

    public String getName() {
        return name;
    }

    public int getPointCost() {
        return pointCost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

}
