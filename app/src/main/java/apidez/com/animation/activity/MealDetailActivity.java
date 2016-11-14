package apidez.com.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Bind(R.id.rvContent)
    RecyclerView rvContent;

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
}
