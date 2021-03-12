**ViewGroup提供**<br/>
**FrameLayoutAdapt，LinearLayoutAdapt，RelativeLayoutAdapt，RadioGroupAdapt**

**View提供**<br/>
**TextViewAdapt，EditTextAdapt，CheckBoxAdapt，RadioButtonAdapt，ImageViewAdapt，ImageButtonAdapt**


| 属性                          | 类型    | 说明                                                                                                                            |
| ------------------------------- | --------- | --------------------------------------------------------------------------------------------------------------------------------- |
| uiDesignWidth                   | dimension | ui设计稿的宽度                                                                                                              |
| uiDesignHeight                  | dimension | ui设计稿的高度                                                                                                              |
| uiAdaptEnable                   | boolean   | 是否开启ui适配                                                                                                              |
| uiAdaptWidth                    | boolean   | true:适配视图宽度,false:适配视图高度,默认:true                                                                      |
| autoAdaptScreenWidthHeight      | boolean   | true:适配根视图显示区域宽高尺寸,false:适配屏幕宽高尺寸<br/>默认:true不以屏幕宽高尺寸适配            |
| layout_adapt_width              | dimension | view宽度,不设置此属性就以layout_width为主<br/>(设计稿标注多少就填多少,下同)                                  |
| layout_adapt_height             | dimension | view高度,不设置此属性就以layout_height为主(同上)                                                                            |
| adapt_padding                   | dimension | 同上                                                                                                                            |
| adapt_paddingHorizontal         | dimension | 同上                                                                                                                            |
| adapt_paddingVertical           | dimension | 同上                                                                                                                            |
| adapt_paddingLeft               | dimension | 同上                                                                                                                            |
| adapt_paddingTop                | dimension | 同上                                                                                                                            |
| adapt_paddingRight              | dimension | 同上                                                                                                                            |
| adapt_paddingBottom             | dimension | 同上                                                                                                                            |
| adapt_paddingStart              | dimension | 同上                                                                                                                            |
| adapt_paddingEnd                | dimension | 同上                                                                                                                            |
| adapt_textSize                  | dimension | 同上                                                                                                                            |
| adapt_autoSizeStepGranularity   | dimension | 同上                                                                                                                            |
| adapt_autoSizeMaxTextSize       | dimension | 同上                                                                                                                            |
| adapt_autoSizeMinTextSize       | dimension | 同上                                                                                                                            |
| layout_adapt_margin             | dimension | 同上                                                                                                                            |
| layout_adapt_marginLeft         | dimension | 同上                                                                                                                            |
| layout_adapt_marginTop          | dimension | 同上                                                                                                                            |
| layout_adapt_marginRight        | dimension | 同上                                                                                                                            |
| layout_adapt_marginBottom       | dimension | 同上                                                                                                                            |
| layout_adapt_marginStart        | dimension | 同上                                                                                                                            |
| layout_adapt_marginEnd          | dimension | 同上                                                                                                                            |
| adapt_include_status_bar_height | boolean   | autoAdaptScreenWidthHeight为true的情况下，该属性无效<br/>适配时是否包含状态栏高度,true:包含(全屏显示情况下需要设置为true)，默认:false |
| ignoreAdaptHeight               | dimension | 适配高度时，需要忽略的尺寸                                                                                           |
| ignoreAdaptWidth                | dimension | 适配宽度时，需要忽略的尺寸                                                                                           |

## 注意!Xml根布局一定要用FrameLayoutAdapt或LinearLayoutAdapt或RelativeLayoutAdapt或RadioGroupAdapt
#### 1.全局设置设计稿宽高
##### application 或者 当前activity设置theme
```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <!-- 适配设置全局style-->
        <item name="LayoutAdaptAttr">@style/globalStyle</item>
</style>
<style name="globalStyle" >
        <item name="uiAdaptEnable">true</item>
        <item name="uiDesignWidth">4000px</item>
        <item name="uiDesignHeight">4000px</item>
</style>
```
##### style设置属性
```xml
<com.zhongrui.FrameLayoutAdapt
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@style/testStyle">
</com.zhongrui.FrameLayoutAdapt>

<style name="testStyle">
     <item name="uiAdaptEnable">true</item>
     <item name="uiDesignWidth">2000px</item>
</style>
```
#### xml设置属性
```xml
<com.zhongrui.FrameLayoutAdapt
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:uiAdaptWidth="true"
      app:uiDesignHeight="1000px"
      app:uiDesignWidth="1000px">
```
#### 属性设置优先级  xml > style > theme


### 2.动态设置view大小、margin和padding
```java
/*父view是FrameLayoutAdapt，就用FrameLayoutAdapt.LayoutParams*/
/*父view是LinearLayoutAdapt，就用LinearLayoutAdapt.LayoutParams*/
/*父view是RelativeLayoutAdapt，就用RelativeLayoutAdapt.LayoutParams*/

FrameLayoutAdapt.LayoutParams layoutParams = 
new FrameLayoutAdapt.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
/*或者*/
FrameLayoutAdapt.LayoutParams layoutParams = (FrameLayoutAdapt.LayoutParams) view.getLayoutParams();

layoutParams.getLayoutAdaptInfo().widthAdapt=500;
layoutParams.getLayoutAdaptInfo().heightAdapt=500;
layoutParams.getLayoutAdaptInfo().widthAdapt=100;
layoutParams.getLayoutAdaptInfo().heightAdapt=100;
layoutParams.getLayoutAdaptInfo().leftMarginAdapt=100;
layoutParams.getLayoutAdaptInfo().topMarginAdapt=100;
layoutParams.getLayoutAdaptInfo().rightMarginAdapt=100;
layoutParams.getLayoutAdaptInfo().bottomMarginAdapt=100;
layoutParams.getLayoutAdaptInfo().startMarginAdapt=100;
layoutParams.getLayoutAdaptInfo().endMarginAdapt=100;

view.setLayoutParams(layoutParams);

/*设置padding*/
view.setPaddingAdapt(10,10,10,10);

/*以上数值按照设计稿的标注设置即可*/
```  
<br/><br/>  

| 版本号 |[![](https://jitpack.io/v/zhongruiAndroid/ViewAdaptProject.svg)](https://jitpack.io/#zhongruiAndroid/ViewAdaptProject)|
| ------ | ---- |

```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
	implementation 'com.github.zhongruiAndroid:ViewAdaptProject:版本号看上面'
}
```
