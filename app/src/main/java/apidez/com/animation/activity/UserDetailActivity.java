package apidez.com.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import apidez.com.animation.R;

public class UserDetailActivity extends AppCompatActivity {
    private static final String USERNAME = "username";

    public static Intent getIntent(Context context, String username) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
    }
}
