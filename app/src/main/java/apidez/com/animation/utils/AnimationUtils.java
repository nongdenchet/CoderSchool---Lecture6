package apidez.com.animation.utils;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by nongdenchet on 11/14/16.
 */

public class AnimationUtils {

    public static void moveUp(View[] views, int duration) {
        for (View view : views) {
            moveUp(view, duration);
        }
    }

    public static void moveUp(View view, int duration) {
        view.setTranslationY(500);
        ViewCompat.animate(view)
                .translationYBy(-500)
                .setDuration(duration)
                .start();
    }
}
