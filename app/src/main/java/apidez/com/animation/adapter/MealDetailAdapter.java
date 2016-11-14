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
import apidez.com.animation.databinding.ItemOwnerBinding;
import apidez.com.animation.databinding.ItemReviewBinding;
import apidez.com.animation.model.Meal;
import apidez.com.animation.model.Review;
import apidez.com.animation.utils.DataUtils;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nongdenchet on 11/14/16.
 */

public class MealDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER = 0;
    private final int ITEM = 1;
    private List<Review> mReviews;
    private Meal mMeal;

    public MealDetailAdapter(Meal meal) {
        mMeal = meal;
        mReviews = DataUtils.reviews();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new HeaderViewHolder(inflateView(parent, R.layout.item_owner));
            default:
                return new ItemViewHolder(inflateView(parent, R.layout.item_review));
        }
    }

    private View inflateView(ViewGroup parent, int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).bind(mMeal);
        } else if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(mReviews.get(position - 1));
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ItemOwnerBinding mBinding;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(Meal meal) {
            mBinding.setMeal(meal);
        }

        @OnClick(R.id.ivAvatar)
        public void onAvatarClick() {
            EventBus.getDefault().post(new UserDetailEvent(mBinding.ivAvatar,
                    mBinding.getMeal().getUsername(),
                    mBinding.getMeal().getAvatar()));
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemReviewBinding mBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(Review review) {
            mBinding.setReview(review);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        }
        return ITEM;
    }

    @Override
    public int getItemCount() {
        return mReviews.size() + 1;
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
