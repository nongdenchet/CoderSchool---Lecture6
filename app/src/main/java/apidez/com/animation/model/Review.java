package apidez.com.animation.model;

import java.io.Serializable;

/**
 * Created by nongdenchet on 11/14/16.
 */

public class Review implements Serializable {
    private String username;
    private String avatar;
    private String content;
    private String date;
    private int numStars;

    public Review(String username, String avatar, String content, String date, int numStars) {
        this.username = username;
        this.avatar = avatar;
        this.content = content;
        this.date = date;
        this.numStars = numStars;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getNumStars() {
        return numStars;
    }
}
