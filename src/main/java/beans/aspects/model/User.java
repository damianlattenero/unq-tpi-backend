package beans.aspects.model;


import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id()
    @GeneratedValue()
    @Column(name = "BASE_ENTITY_ID")
    private int id;

    @ManyToOne
    private Place place;

    public Place getPlace() {
        return place;
    }
}
