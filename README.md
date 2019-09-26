# SpecialView是通用控件的一个集合库目前收录
#### 支持灵活布局的Banner 
#### 一个之自定义的带边框的输入控件BorderPWEditText以及一个简易的自定义布局键盘NumberKeyboardView

#### 后期将会陆续整理其他的功能分享出来

## 1.持多种布局Banner组件

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
 
 ## 2.BorderPWEditText使用

### 添加依赖

#### 3.布局Xml中使用：

```
  <cn.lp.input_library.BorderPWEditText
        android:id="@+id/BorderPWEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:inputType="text"
        app:borderRadius="5dp"
        app:conceal="true"
        app:employFillColor="#FF9800"
        app:employLColor="#F44336"
        app:employLineWidth="2dp"
        app:focusFillColor="#009688"
        app:focusLColor="#673AB7"
        app:focusLineWidth="2dp"
        app:height="40dp"
        app:isContinuousChar="true"
        app:lineWidth="2dp"
        app:width="40dp"
        />
```
根据前面的属性说明自行添加相应属性实现效果

#### 4.监听输入

```
 BorderPWEditText.setmInputOverListener(object : BorderPWEditText.InputOverListener {
            override fun InputOver(string: String?) {
                Toast.makeText(baseContext, "当前接收的数据为：${string}", Toast.LENGTH_LONG).show()
            }

            override fun InputHint(string: String?) {
                Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
            }

        })
```
#### 可以选择的属性：
| 属性名称 |作用  |
|--|--|
| textColor  | 文字颜色默认黑色 |
| textSize | 文文字尺寸默认 22|
| count | 输入框个数默认6|
| width| 输入框宽度默认40dp|
| height| 输入框高度默认40dp |
| lineColor|  默认状态的边框颜色 默认黑色 |
| fillColor  | 默认状态的填充颜色  默认白色|
| lineWidth| 默认状态的边框宽度  默认1dp|
| focusLColor|  默认状态的边框颜色 默认黑色 |
| focusFillColor| 默认状态的填充颜色  默认白色|
| focusLineWidth| 默认状态的边框宽度  默认1dp|
| employLColor|  默认状态的边框颜色 默认黑色 |
| employFillColor| 默认状态的填充颜色  默认白色|
| employLineWidth| 默认状态的边框宽度  默认1dp|
| isContinuous|  输入框是否连续（方便以后其他需要就添加了）默认true 连续|
| borderRadius| 文输入框边角半径 默认0dp|
| conceal | 是否隐藏文字 默认false 不隐藏 |
| replaceString| 文字隐藏替换字符 默认没有 |
| replaceDrawable| 文字隐藏替换图片（优先级高于replaceString）  默认没有 |
| circleRadius| 默认替换图案半径（圆形） 默认为width的三分之一 |
| circleColor| 默认替换图案颜色 默认与textColor  一致|
| isContinuousRepeatChar| 是否过滤连续重复的字符  默认false 不过滤 |
| isContinuousChar| 是否过滤连续的字符  默认false 不过滤 |
| isInvokingKeyboard | 是否使用系统键盘  默认为true 如果为false的换需要自己手动调起键盘 |
