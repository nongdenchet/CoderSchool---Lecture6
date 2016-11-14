package apidez.com.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import apidez.com.animation.R;
import apidez.com.animation.databinding.ActivityMealDetailBinding;
import apidez.com.animation.model.Meal;

public class MealDetailActivity extends AppCompatActivity {
    private static final String MEAL = "meal";
    private ActivityMealDetailBinding mBinding;

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
    }
}
