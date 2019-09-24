package cn.shequren.banner_library.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.shequren.banner_library.Holder.FlexibleBannerHolder;
import cn.shequren.banner_library.Holder.ItmeBuildImpi;
import cn.shequren.banner_library.listener.BannerItemListener;


public class FlexilbeBasePageAdapter<T> extends RecyclerView.Adapter<FlexibleBannerHolder> {
    protected List<T> datas;
    private ItmeBuildImpi creator;
    private boolean autoSwitch = false;
    private BannerItemListener onItemClickListener;

    public FlexilbeBasePageAdapter(ItmeBuildImpi creator, List<T> datas) {
        this.creator = creator;
        this.datas = datas;
    }



    @Override
    public FlexibleBannerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return creator.createViewHolder(parent,viewType);
    }

    @Override
    public int getItemViewType(int position) {
        int realPosition = position % datas.size();
        return creator.getItemViewType(datas.get(realPosition));
    }

    public void update(List<T> datas) {
        this.datas = datas;
    }



    @Override
    public void onBindViewHolder(FlexibleBannerHolder holder, int position) {
        int realPosition = position % datas.size();
        holder.updateUI(datas.get(realPosition));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new OnPageClickListener(realPosition));
        }
    }

    @Override
    public int getItemCount() {
        //循环因为需要可能两边滑动所有需要Item是原来的3倍
        if (datas.size() == 0)
            return 0;
        return autoSwitch ? 3 * datas.size() : datas.size();
    }

    public void setAutoSwitch(boolean autoSwitch) {
        this.autoSwitch = autoSwitch;
    }

    public int getRealItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public boolean isAutoSwitch() {
        return autoSwitch;
    }

    public void setOnItemClickListener(BannerItemListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class OnPageClickListener implements View.OnClickListener {
        private int position;

        public OnPageClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position);
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
