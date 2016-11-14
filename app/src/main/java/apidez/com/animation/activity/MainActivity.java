package apidez.com.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.target.ViewTarget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import apidez.com.animation.R;
import apidez.com.animation.adapter.MealAdapter;
import apidez.com.animation.utils.BindingUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private int[] fabLocations;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.rvMeals)
    RecyclerView rvMeals;

    @Bind(R.id.activity_main)
    ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ViewTarget.setTagId(R.id.glide_tag);
        rvMeals.setAdapter(new MealAdapter());
        rvMeals.setLayoutManager(new GridLayoutManager(this, 2));
        fab.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fabLocations = new int[2];
                fab.getLocationOnScreen(fabLocations);
                fab.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @OnClick(R.id.fab)
    public void openCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void onEvent(MealAdapter.MealDetailEvent event) {
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, event.imageView,
                        getString(R.string.transition_image));
        startActivity(MealDetailActivity.getIntent(this, event.meal), options.toBundle());
    }

    @Subscribe
    public void onEvent(MealAdapter.UserDetailEvent event) {
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, event.imageView,
                        getString(R.string.transition_image));
        startActivity(UserDetailActivity.getIntent(this, event.username, event.avatar),
                options.toBundle());
    }

    @Subscribe
    public void onEvent(MealAdapter.OrderEvent event) {
        int[] locations = new int[2];
        event.imageView.getLocationOnScreen(locations);

        final View view = createTempView(event.imageView, event.image);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view,
                View.X, locations[0], fabLocations[0] - fab.getWidth());
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view,
                View.Y, locations[1], fabLocations[1] - fab.getHeight() * 2);
        animatorY.setInterpolator(new AccelerateInterpolator());
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view,
                View.SCALE_X, 1f, 0f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view,
                View.SCALE_Y, 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playTogether(animatorX, animatorY, animatorScaleX, animatorScaleY);
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                fab.show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                parent.removeView(view);
            }
        });
        set.start();
    }

    public View createTempView(ImageView imageView, String url) {
        View rootView = LayoutInflater.from(this)
                .inflate(R.layout.item_image, parent, true);
        ImageView view = (ImageView) rootView.findViewById(R.id.image);
        view.setLayoutParams(new RelativeLayout.LayoutParams(imageView.getWidth(),
                imageView.getHeight()));
        BindingUtils.setImageUrl(view, url);
        return view;
    }
}
