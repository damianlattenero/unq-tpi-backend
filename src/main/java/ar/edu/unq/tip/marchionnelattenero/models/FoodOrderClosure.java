package ar.edu.unq.tip.marchionnelattenero.models;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "FoodOrderClosure")
public class FoodOrderClosure {


    @Id()
    @GeneratedValue()
    @Column(name = "FoodOrderClosure_ID")
    private int id;

    @Column(name = "momentClosure")
    private Timestamp momentClosure;

    @Column(name = "USER_ID")
    private String user;

    @Column(name = "moment")
    private Timestamp moment;

    public FoodOrderClosure() {
    }

    public FoodOrderClosure(Timestamp momentClosure, String user) {
        this.moment = new Timestamp(DateTime.now().getMillis());;
        this.momentClosure = momentClosure;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Timestamp getMoment() {
        return moment;
    }

    public Timestamp getMomentClosure() {
        return momentClosure;
    }

    public String getUser() {
        return user;
    }
}
