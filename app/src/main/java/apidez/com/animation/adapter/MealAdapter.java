package apidez.com.animation.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import apidez.com.animation.R;
import apidez.com.animation.databinding.ItemMealBinding;
import apidez.com.animation.model.Meal;
import apidez.com.animation.utils.DataUtils;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nongdenchet on 11/13/16.
 */

public class MealAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Meal> mMeals = DataUtils.meals();;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(mMeals.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMealBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(Meal meal) {
            mBinding.setMeal(meal);
        }

        @OnClick(R.id.container)
        public void onItemClick() {
            EventBus.getDefault().post(new MealDetailEvent(mBinding.ivMeal,
                    mBinding.getMeal()));
        }

        @OnClick(R.id.ivAvatar)
        public void onAvatarClick() {
            EventBus.getDefault().post(new UserDetailEvent(mBinding.ivAvatar,
                    mBinding.getMeal().getUsername(),
                    mBinding.getMeal().getAvatar()));
        }
    }

    public class MealDetailEvent {
        public final ImageView imageView;
        public final Meal meal;

        public MealDetailEvent(ImageView imageView, Meal meal) {
            this.imageView = imageView;
            this.meal = meal;
        }
    }

    public class UserDetailEvent {
        public final ImageView imageView;
        public final String username;
        public final String avatar;

        public UserDetailEvent(ImageView imageView, String username, String avatar) {
            this.imageView = imageView;
            this.username = username;
            this.avatar = avatar;
        }
    }
}
