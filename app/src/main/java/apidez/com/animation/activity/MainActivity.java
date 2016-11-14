package apidez.com.animation.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import apidez.com.animation.R;
import apidez.com.animation.adapter.MealAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rvMeals)
    RecyclerView rvMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rvMeals.setAdapter(new MealAdapter());
        rvMeals.setLayoutManager(new GridLayoutManager(this, 2));
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
        startActivity(UserDetailActivity.getIntent(this, event.username), options.toBundle());
    }
}
