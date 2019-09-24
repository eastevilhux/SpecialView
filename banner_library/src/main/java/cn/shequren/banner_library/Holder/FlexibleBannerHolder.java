package cn.shequren.banner_library.Holder;

/**
 * Created by Sai on 15/12/14.
 *
 * @param <T> 任何你指定的对象
 */

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class FlexibleBannerHolder extends RecyclerView.ViewHolder {
    public FlexibleBannerHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    protected abstract void initView(View itemView);

    public abstract void updateUI(Object data);
}
