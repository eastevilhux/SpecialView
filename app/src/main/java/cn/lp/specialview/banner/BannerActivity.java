package cn.lp.specialview.banner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lp.specialview.R;
import cn.shequren.banner_library.Holder.DefaultImageBannerHolder;
import cn.shequren.banner_library.Holder.FlexibleBannerHolder;
import cn.shequren.banner_library.Holder.ItmeBuildImpi;
import cn.shequren.banner_library.Ui.FlexibleBanner;
import cn.shequren.banner_library.congif.IndicatorConfig;
import cn.shequren.banner_library.listener.BannerItemListener;

public class BannerActivity extends AppCompatActivity {

    private FlexibleBanner mFlexibleBanner, mFlexibleBanner2, mFlexibleBanner3;

    private List<String> mBannerUrlLists = new ArrayList<>();
    private List<String> mBanner3UrlLists = new ArrayList<>();
    private List<Banner> mBanner2UrlLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mFlexibleBanner = findViewById(R.id.fl_root);
        mFlexibleBanner2 = findViewById(R.id.fl_root2);
        mFlexibleBanner3 = findViewById(R.id.fl_root3);

        getLifecycle().addObserver(mFlexibleBanner);

        initBanner();
        setBannerData();
    }

    /**
     * Banner
     */
    private void initBanner() {

        mFlexibleBanner.setPages(new DefaultImageBannerHolder(15), mBannerUrlLists).startTurning();

        mFlexibleBanner.setOnItemClickListener(new BannerItemListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getBaseContext(),(String)mBannerUrlLists.get(position),Toast.LENGTH_LONG).show();
            }
        });

        getLifecycle().addObserver(mFlexibleBanner);

        mFlexibleBanner2.setPages(new ItmeBuildImpi() {

            @Override
            public FlexibleBannerHolder createViewHolder(ViewGroup parent, int viewType) {
                //返回布局类型1
                if (viewType == 1) {
                    View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.banner_item, parent, false);
                    return new TestLocalImageHolderView1(itemView);
                } else {
                    //返回布局样式2
                    View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.banner2_item, parent, false);
                    return new TestLocalImageHolderView2(itemView);
                }

            }

            @Override
            public int getItemViewType(Object t) {
                //根据数据返回布局样式类型
                Banner banner = (Banner) t;
                return banner.getType();
            }
        }, mBanner2UrlLists)
                .setPageIndicatorAlign(IndicatorConfig.INDICATOR_orientation_BOTTOM_CENTER)
                .setPageIndicatorPadding(0, 0, 10, 10);

        getLifecycle().addObserver(mFlexibleBanner2);
        mFlexibleBanner2.setOnItemClickListener(new BannerItemListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getBaseContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });

        mFlexibleBanner3.setPages(new ItmeBuildImpi() {

            @Override
            public FlexibleBannerHolder createViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.banner_item, parent, false);

                return new TestLocalImageHolderView(itemView);
            }

            @Override
            public int getItemViewType(Object t) {
                return 0;
            }
        }, mBanner3UrlLists).
                setPageIndicatorAlign(IndicatorConfig.INDICATOR_orientation_BOTTOM_CENTER)
                .setPageIndicatorPadding(0, 0, 10, 10);

        getLifecycle().addObserver(mFlexibleBanner3);

    }

    public void setBannerData() {


        mBannerUrlLists.add("http://photo.tuchong.com/425546/f/9054810.jpg");
        mBannerUrlLists.add("http://img1.cache.netease.com/catchpic/F/FC/FC1CD53DCB59E973E060E127866CA81D.jpg");
        mBannerUrlLists.add("http://www.bcsc.com.cn/userfiles/image/tzdqwct.jpg");
        mBannerUrlLists.add("http://pic16.nipic.com/20110922/7008856_110943000000_2.jpg");
        mFlexibleBanner.update(mBannerUrlLists);

        mBanner3UrlLists.add("http://hbimg.b0.upaiyun.com/50521a8f188e5bf55b6538845e8224e54f0eff03532ac-vidXCP_fw658");
        mBanner3UrlLists.add("http://p3.pstatp.com/large/56660007263d857d8163");
        mBanner3UrlLists.add("http://photocdn.sohu.com/20130904/Img385856827.jpg");
        mBanner3UrlLists.add("http://hbimg.b0.upaiyun.com/5563db2e5195ab13da625d6e06b62c98cd6716d21ceec-I0jNrt_fw658");
        mFlexibleBanner3.update(mBanner3UrlLists);


        Banner banner = new Banner();
        banner.setType(1);
        banner.setUrl("http://5b0988e595225.cdn.sohucs.com/images/20190216/4a7c34b9b9df4852be5029876db337bb.jpeg");
        mBanner2UrlLists.add(banner);

        Banner banner2 = new Banner();
        banner2.setType(2);
        List<Banner.Goods> goods2 = new ArrayList<>();
        goods2.add(new Banner.Goods("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569578559&di=61e3fd3bb62d084d5ec15a6d87fca2ff&imgtype=jpg&er=1&src=http%3A%2F%2Fm.360buyimg.com%2Fpop%2Fjfs%2Ft22885%2F315%2F2060265358%2F20803%2Ff549b2c7%2F5b727da5N74ec59cb.jpg", "123"));
        goods2.add(new Banner.Goods("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569578597&di=cf7536e44d6dfc00cfde9bcf805e7902&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qnong.com.cn%2Fuploadfile%2F2014%2F0222%2F20140222094954105.jpg", "456"));
        goods2.add(new Banner.Goods("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568983878048&di=57691e331ed7e5ce27b54bca351ee4ba&imgtype=0&src=http%3A%2F%2Fbpic.ooopic.com%2F13%2F49%2F46%2F35b2OOOPIC4d.jpg", "789"));
        banner2.setGoods(goods2);
        mBanner2UrlLists.add(banner2);

        Banner banner3 = new Banner();
        banner3.setType(2);
        List<Banner.Goods> goods3 = new ArrayList<>();
        goods3.add(new Banner.Goods("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568983957953&di=458cb53a55b05d8d0b41bb9f48c9f8d9&imgtype=0&src=http%3A%2F%2Fimg.daimg.com%2Fuploads%2Fallimg%2F120517%2F1-12051H34951924.jpg", "963"));
        goods3.add(new Banner.Goods("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568983957954&di=36867c312502a74f77b5620a27de31ca&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171115%2Fc316641e4ce644bbb853ce7f472badd9.jpeg", "852"));
        banner3.setGoods(goods3);
        mBanner2UrlLists.add(banner3);


        Banner banner4 = new Banner();
        banner4.setType(1);
        banner4.setUrl("http://www.hinews.cn/pic/003/020/629/00302062908_a4035f65.jpg");
        mBanner2UrlLists.add(banner4);
        mFlexibleBanner2.update(mBanner2UrlLists);
        if (mBanner2UrlLists.size() < 2) {
            mFlexibleBanner2.stopTurning();
        } else {
            mFlexibleBanner2.startTurning(5000);
        }

    }

}
