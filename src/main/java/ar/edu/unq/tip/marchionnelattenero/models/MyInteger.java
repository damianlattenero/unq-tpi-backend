package ar.edu.unq.tip.marchionnelattenero.models;

import javax.persistence.*;

@Entity
@Table(name = "MyInteger")
public class MyInteger {

    @Id()
    @GeneratedValue()
    @Column(name = "MyInteger_ID")
    private int id;

    @Column(name = "value")
    private int value;

    public MyInteger() {
    }

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

