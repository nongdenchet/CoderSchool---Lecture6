package apidez.com.animation.custom;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by nongdenchet on 11/14/16.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private boolean hidden = false;
    private boolean animating = false;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            animating = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animating = false;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            animating = false;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            animating = true;
        }
    };

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);
        if (animating) return;
        if (dyConsumed > 32 && !hidden) {
            child.animate()
                    .translationYBy(child.getHeight() * 2)
                    .setDuration(300)
                    .setInterpolator(new DecelerateInterpolator())
                    .setListener(animatorListener)
                    .start();
            hidden = true;
        } else if (dyConsumed < -32 && hidden) {
            child.animate()
                    .translationYBy(child.getHeight() * -2)
                    .setDuration(300)
                    .setInterpolator(new AccelerateInterpolator())
                    .setListener(animatorListener)
                    .start();
            hidden = false;
        }
    }
}
