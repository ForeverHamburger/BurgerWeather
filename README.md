# 【Android】BurgerWeather

> ##### 基于MVP架构的天气预报APP
>
> ##### 基于汉堡制作技术的Android应用（不是）

![Burger 副本](https://gitee.com/ForeverHamburger/picgo_imgs1/raw/master/202409231748229.png)

Logo如上~

## 功能展示

主界面：

![48ed08de8feeb8be8c0e0d49560ea9b8](https://gitee.com/ForeverHamburger/picgo_imgs1/raw/master/202409231749704.jpg)

详情页：

![2ed48c8e019353bc68464231d0e0ac0b](https://gitee.com/ForeverHamburger/picgo_imgs1/raw/master/202409231749809.jpg)

城市管理页面：

![3afd94d585b814f97ea4edd9bacb4b11](https://gitee.com/ForeverHamburger/picgo_imgs1/raw/master/202409231750010.jpg)

热门城市页面：

![](https://gitee.com/ForeverHamburger/picgo_imgs1/raw/master/202409231750830.jpeg)

## 使用的技术：

#### 项目架构

项目整体框架使用MVP架构完成，每个UI界面都单独划分出一套MVP的体系。

#### 数据获取部分

1. Model层的数据库部分，使用Room数据库作为本地持久化的存储方案，在此APP中，主要分为两个库：一为本地CityList数据库，用于保存主界面展示的所有城市列表。二为城市信息管理类，用于保存网络获取到的所有的城市信息（例如位置，天气信息等）
2. Model层的网络部分，使用Okhttp3作为网络请求的相关类。
3. 使用Gson库解析网络获取到的Json数据。

#### UI部分

使用ViewPager2、PtrFrameLayout下拉刷新、circleindicator3指示器、以及MotionLayout实现了一个简易的动画。

## 当前仍然存在的问题：

1. ViewPager2和RecyclerView之间嵌套，导致了滑动冲突问题，对体验感影响较大。
2. 跳转Activity之后，原先Activity中Fragment的数据丢失，目前采用的解决方案并不完美（在跳转回去之后重新创建数据）。
3. 热门城市添加后，无法立刻改变Button的颜色。
4. Model层的数据库并没有采用单例的模式，导致创建过多数据库，由于数据库的引用和创建较多，暂时并未做出修改。
