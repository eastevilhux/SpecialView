package cn.shequren.banner_library.LayoutManager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * @author Loren
 * Create_Time: 2019/9/10 16:23
 * description:
 */
public class SmoothScrollLayoutManager extends LinearLayoutManager {


    private float slideVelocity;

    public float getSlideVelocity() {
        return slideVelocity;
    }

    public void setSlideVelocity(int slideVelocity) {
        this.slideVelocity = slideVelocity;
    }

    public SmoothScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }


    public SmoothScrollLayoutManager(Context context, int orientation, boolean reverseLayout,float slideVelocity) {
        super(context, orientation, reverseLayout);
        this.slideVelocity =  slideVelocity;
    }
    public SmoothScrollLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView,
                                       RecyclerView.State state, final int position) {

        LinearSmoothScroller smoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    // 返回：滑过1px时经历的时间(ms)。
                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return slideVelocity / displayMetrics.densityDpi;
                    }
                };
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }
}
