package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {
    @Id()
    @GeneratedValue()
    @Column(name = "BASE_ENTITY_ID")
    private int id;

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;


    public BaseEntity() {
    }

    public BaseEntity(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
