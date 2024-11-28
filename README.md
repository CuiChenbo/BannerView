# BannerView
## 轮播图基本框架<br/>
 基于ViewPager2的BannerView实现了下图的切换效果（缩放+横向位移重叠+Y轴向内旋转）<br/>
<br/>
<img src="https://github.com/CuiChenbo/BannerView/blob/master/imgs/deepBanner.jpg" width = 360>
 <br/>
### 使用两种方式实现Banner效果<br/>
 1、ItemCount+2 的方式，即在原来的Adapter的首尾各增加一个View，形成首尾相连无限滚动的ViewPager<br/>
 2、ItemCount 设置很大的数的方式，然后在滚动到第一个或最后一个时，重置当前位置到中间<br/>


### Thanks

- [banner](https://github.com/youth5201314/banner)
