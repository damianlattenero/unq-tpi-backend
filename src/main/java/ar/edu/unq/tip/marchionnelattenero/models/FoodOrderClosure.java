package ar.edu.unq.tip.marchionnelattenero.models;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    public FoodOrderClosure(Date momentClosure, String user) {
        this.moment = new Timestamp(DateTime.now().getMillis());;
        this.momentClosure = new Timestamp(momentClosure.getTime());
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
