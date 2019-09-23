package cn.shequren.banner_library.Holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.shequren.banner_library.Padding;
import cn.shequren.banner_library.R;


/**
 * @author Loren
 * Create_Time: 2019/9/23 9:18
 * description:
 */
public class DefaultImageBannerHolder implements ItmeBuildImpi {

    private int imageRadius;
    private Padding padding;
    private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;

    public DefaultImageBannerHolder() {
    }

    public DefaultImageBannerHolder(int imageRadius) {

        this.imageRadius = imageRadius;

    }


    public DefaultImageBannerHolder(int imageRadius, ImageView.ScaleType scaleType) {

        this.imageRadius = imageRadius;
        this.scaleType = scaleType;
    }

    public DefaultImageBannerHolder(int imageRadius, ImageView.ScaleType scaleType, Padding paddingRight) {
        this.imageRadius = imageRadius;
        this.padding = paddingRight;
        this.scaleType = scaleType;
    }

    @Override
    public FlexibleBannerHolder createViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_default_image_item, parent, false);

        return new DefaultImageHolderView(itemView,imageRadius,scaleType,padding);
    }

    @Override
    public int getItemViewType(Object t) {
        return 0;
    }
}
