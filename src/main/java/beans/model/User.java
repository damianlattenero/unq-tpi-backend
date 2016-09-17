package beans.model;


import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id()
    @GeneratedValue()
    @Column
    private int id;

    @ManyToOne
    private Place place;

    public Place getPlace() {
        return place;
    }
}
