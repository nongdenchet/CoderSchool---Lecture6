package apidez.com.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import apidez.com.animation.R;
import apidez.com.animation.adapter.ImageAdapter;
import apidez.com.animation.databinding.ActivityUserDetailBinding;
import apidez.com.animation.utils.AnimationUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class UserDetailActivity extends AppCompatActivity {
    private static final String USERNAME = "username";
    private static final String AVATAR = "avatar";
    private ActivityUserDetailBinding mBinding;

    public static Intent getIntent(Context context, String username, String avatar) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(USERNAME, username);
        intent.putExtra(AVATAR, avatar);
        return intent;
    }

    @Bind(R.id.rvMeals)
    RecyclerView rvMeals;

    @Bind({R.id.tvUsername, R.id.btnFollow, R.id.infoContainer, R.id.rvMeals})
    View[] views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra(USERNAME);
        String avatar = getIntent().getStringExtra(AVATAR);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);
        mBinding.setAvatar(avatar);
        mBinding.setUsername(username);
        ButterKnife.bind(this);
        rvMeals.setAdapter(new ImageAdapter());
        rvMeals.setLayoutManager(new GridLayoutManager(this, 2));
        AnimationUtils.moveUp(views, 400);
    }
}
