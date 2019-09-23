package cn.shequren.banner_library.Holder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import cn.shequren.banner_library.Padding;
import cn.shequren.banner_library.R;
import cn.shequren.banner_library.utlis.DisplayHelper;


/**
 * @author Loren
 * Create_Time: 2019/9/23 9:20
 * description:
 */
public class DefaultImageHolderView extends FlexibleBannerHolder {


    private int imageRadius;
    private Padding padding;
    private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;

    public DefaultImageHolderView(View itemView, int imageRadius, ImageView.ScaleType scaleType) {
        super(itemView);
        this.imageRadius = imageRadius;
        this.scaleType = scaleType;
    }



    public DefaultImageHolderView(View itemView, int imageRadius, ImageView.ScaleType scaleType, Padding paddingRight) {
        super(itemView);
        this.imageRadius = imageRadius;
        this.padding = paddingRight;
        this.scaleType = scaleType;
    }

    public DefaultImageHolderView(View itemView, ImageView.ScaleType scaleType, Padding paddingRight) {
        super(itemView);
        this.padding = paddingRight;
        this.scaleType = scaleType;
    }

    public DefaultImageHolderView(View itemView) {
        super(itemView);
    }



    @Override
    protected void initView(View itemView) {
        mItemView = itemView;
        imageView = itemView.findViewById(R.id.iv_banner);


    }

    @Override
    public void updateUI(Object data) {
        if(imageRadius<=0){
            Glide.with(mItemView.getContext())
                    .load(data.toString())
                    .into(imageView);
        }else{
            Glide.with(mItemView.getContext())
                    .load(data.toString())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(imageRadius)))
                    .into(imageView);
        }

        if(padding!= null){
            imageView.setPadding(DisplayHelper.dp2px(imageView.getContext(),padding.getPaddingLeft()),
                    DisplayHelper.dp2px(imageView.getContext(),padding.getPaddingTop()),
                    DisplayHelper.dp2px(imageView.getContext(),padding.getPaddingRight()),
                    DisplayHelper.dp2px(imageView.getContext(),padding.getPaddingBottom()));
        }

        imageView.setScaleType(scaleType);

    }


    private ImageView imageView;
    private View mItemView;

}
