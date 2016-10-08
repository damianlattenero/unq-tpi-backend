package ar.edu.unq.tip.ml.models.oauth;

import javax.persistence.*;

@Entity
@Table(name = "GOOGLE_OAUTH_CREDENTIALS")
public class GoogleOauthCredential {
    @Id()
    @GeneratedValue()
    @Column(name = "GOOGLE_OAUTH_ID")
    private int id;

    @Column(name = "google_user_id")
    private String googleUserId;
    @Column(name = "google_access_token")
    private String googleAccessToken;
    @Column(name = "google_refresh_token")
    private String googleRefreshToken;

    public String getGoogleUserId() {
        return googleUserId;
    }

    public void setGoogleUserId(String googleUserId) {
        this.googleUserId = googleUserId;
    }

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }

    public void setGoogleAccessToken(String googleAccessToken) {
        this.googleAccessToken = googleAccessToken;
    }

    public String getGoogleRefreshToken() {
        return googleRefreshToken;
    }

    public void setGoogleRefreshToken(String googleRefreshToken) {
        this.googleRefreshToken = googleRefreshToken;
    }

    public int getId() {
        return id;
    }

}
