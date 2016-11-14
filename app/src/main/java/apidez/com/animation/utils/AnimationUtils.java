package apidez.com.animation.utils;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

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

    public static void addToCart(View view, int[] origin, int[] des, AnimatorListenerAdapter adapter) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, View.X, origin[0], des[0]);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, View.Y, origin[1], des[1]);
        animatorY.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playTogether(animatorX, animatorY, animatorScaleX, animatorScaleY);
        set.addListener(adapter);
        set.start();
    }
}
