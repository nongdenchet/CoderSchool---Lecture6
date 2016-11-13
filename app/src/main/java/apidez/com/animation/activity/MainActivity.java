package apidez.com.animation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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
}
