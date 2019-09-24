package cn.lp.specialview.banner;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import cn.lp.specialview.R;
import cn.shequren.banner_library.Holder.FlexibleBannerHolder;

/**
 * @author Loren
 * Create_Time: 2019/9/10 14:09
 * description:
 */
public class TestLocalImageHolderView extends FlexibleBannerHolder {


    public TestLocalImageHolderView(View itemView) {
        super(itemView);

    }

    @Override
    protected void initView(View itemView) {
        mItemView = itemView;
        imageView = itemView.findViewById(R.id.iv_banner);


    }

    @Override
    public void updateUI(Object data) {
        Glide.with(mItemView.getContext())
                .load(data.toString())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(imageView);
    }


    private ImageView imageView;
    private View mItemView;


}


