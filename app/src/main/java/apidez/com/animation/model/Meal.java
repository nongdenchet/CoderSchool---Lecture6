package apidez.com.animation.model;

import java.io.Serializable;

/**
 * Created by nongdenchet on 11/13/16.
 */

public class Meal implements Serializable {
    private String title;
    private String image;
    private String username;
    private String avatar;
    private float numStars;
    private int numLikes;
    private int numOrders;

    public Meal(String title, String image, String username, String avatar,
                float numStars, int numLikes, int numOrders) {
        this.title = title;
        this.image = image;
        this.username = username;
        this.avatar = avatar;
        this.numStars = numStars;
        this.numLikes = numLikes;
        this.numOrders = numOrders;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public float getNumStars() {
        return numStars;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public int getNumOrders() {
        return numOrders;
    }
}
