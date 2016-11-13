package apidez.com.animation.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;


/**
 * Created by nongdenchet on 7/31/16.
 */

public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()
                .override(500, Target.SIZE_ORIGINAL)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }
}
