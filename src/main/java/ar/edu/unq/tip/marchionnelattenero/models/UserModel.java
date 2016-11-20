package ar.edu.unq.tip.marchionnelattenero.models;

import ar.edu.unq.tip.marchionnelattenero.models.oauth.GoogleOauthCredential;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "UserModel")
@XmlRootElement(name = "userModel")
public class UserModel {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue()
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "picture")
    private String picture;

    @Enumerated(EnumType.STRING)
    private Place place;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private GoogleOauthCredential googleOauthCredential;

    public UserModel() {
    }

    public UserModel(String name, String nickname, String email) {
        super();
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public GoogleOauthCredential getGoogleOauthCredential() {
        return googleOauthCredential;
    }

    public void setGoogleOauthCredential(GoogleOauthCredential googleOauthCredential) {
        this.googleOauthCredential = googleOauthCredential;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
