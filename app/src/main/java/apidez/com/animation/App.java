package apidez.com.animation;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by nongdenchet on 11/15/16.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
    }
}
