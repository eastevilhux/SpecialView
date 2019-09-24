# SpecialView是通用控件的一个集合库目前收录了，支持灵活布局的Banner

##持多种布局Banner组件

### 使用

####  1.在项目根build.gradle中添加如下代码

> allprojects {
    repositories {
       ......
        maven { url "https://jitpack.io" }
    }
}
#### 2. 在使用的Module中添加如下引用
> implementation 'com.github.rupertoL:SpecialView:1.1'
> 如果需要使用AndroidX的换请引用  implementation 'com.github.rupertoL:SpecialView:vX1.1'

### 单纯的Banner图使用
初始化：
```

mFlexibleBanner
.setPages(new DefaultImageBannerHolder(15), mBannerUrlLists) //设置默认的Holder和数据源
.startTurning(); //启动轮播
getLifecycle().addObserver(mFlexibleBanner); //添加Lifecycle检测 添加后如果是自动轮播那么在 onPause停止 在onResume开始轮播 

```

更新数据：

```

mFlexibleBanner.update(mBannerUrlLists);

```
单纯的图片轮播就搞定了 


#### 自己实现图片轮播的布局

```

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
        }, mBanner3UrlLists)； //设置默认的Holder和数据源
               
               
```

自己适配布局需要实现 ItmeBuildImpi 接口并且重写createViewHolder 与getItemViewType方法 
其中createViewHolder接口返回一个FlexibleBannerHolder 对象

```
public class TestLocalImageHolderView extends FlexibleBannerHolder {
    public TestLocalImageHolderView(View itemView) {
        super(itemView);
    }

//初始化布局
    @Override
    protected void initView(View itemView) {
        mItemView = itemView;
        imageView = itemView.findViewById(R.id.iv_banner);
    }

//UI更新
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

```
数据更新：

```

mFlexibleBanner3.update(mBannerUrlLists);

```


####  多个布局样式
初始化布局：

```

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
        }, mBanner2UrlLists)；

```

FlexibleBannerHolder 的实现与TestLocalImageHolderView 一样根据布局样式展示不通数据

数据更新：

```

mFlexibleBanner2.update(mBannerUrlLists);

```

### 更多的配置

 - setPageIndicatorAlign(IndicatorConfig align) 设置指示器的位置
 - setPageIndicator(int[] page_indicatorId) 指示器的样式第一个为默认，第二个为选中状态
 - setPageIndicatorMargin(int marginLeft, int marginRight, int marginTop, int marginBottom)  指示器的Margin
 - setPageIndicatorPadding(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom) 指示器的Padding
 - setLoopTask(BannerLoopTaskImpl bannerLoopTask) 自定义轮播机制
 - setAutoSwitch(boolean autoSwitch) 是否自动轮播  默认自动轮播

xml配置：

 1. auto_switch 是否自动轮播 默认true
 2. alone_auto_switch 一个对象时是否轮播 默认false
 3. distance_time 轮播时间间隔 默认5000
 4. aspect_ratio 宽高比 默认-1 不支持宽高比
 5. slide_velocity  一个像素滑动的时间 默认30f
