package apidez.com.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AccelerateInterpolator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import apidez.com.animation.R;
import apidez.com.animation.adapter.MealDetailAdapter;
import apidez.com.animation.databinding.ActivityMealDetailBinding;
import apidez.com.animation.model.Meal;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MealDetailActivity extends AppCompatActivity {
    private static final String MEAL = "meal";
    private ActivityMealDetailBinding mBinding;
    private Handler mHandler;

    @Bind(R.id.rvContent)
    RecyclerView rvContent;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    public static Intent getIntent(Context context, Meal meal) {
        Intent intent = new Intent(context, MealDetailActivity.class);
        intent.putExtra(MEAL, meal);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Meal meal = (Meal) getIntent().getSerializableExtra(MEAL);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_meal_detail);
        mBinding.setMeal(meal);
        ButterKnife.bind(this);
        rvContent.setAdapter(new MealDetailAdapter(meal));
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fab.show();
            }
        }, 500);
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

    @Subscribe
    public void onEvent(MealDetailAdapter.UserDetailEvent event) {
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, event.imageView,
                        getString(R.string.transition_image));
        startActivity(UserDetailActivity.getIntent(this, event.username, event.avatar),
                options.toBundle());
    }

    @Override
    public void onBackPressed() {
        fab.animate().alpha(0)
                .scaleX(0)
                .scaleY(0)
                .setDuration(300)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        MealDetailActivity.super.onBackPressed();
                    }
                })
                .start();
    }
}
