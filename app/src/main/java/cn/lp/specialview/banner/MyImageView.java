package cn.lp.specialview.banner;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2017/12/8 0008 11:48.
 */
public class MyImageView extends AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int min = Math.min(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        super.onMeasure(MeasureSpec.makeMeasureSpec(min, widthMode), MeasureSpec.makeMeasureSpec(min, widthMode));
    }
}
