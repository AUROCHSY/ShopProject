### 背景
大三的暑假学校安排了去中软国际昌平校区做项目，在两周时间内开发一个叫"口袋小食"的web商城，当然这个项目不会投入实际使用, 只是用来练手的...
前端的静态页面和数据库表大部分是老师已经给好的，对前端的修改主要体现在添加了 `thymeleaf` 将静态页面转为动态页面,也适当修改了下数据库表以更适合我们的需求



### 人员分工

我们是一个三人小团队，另外两位同学统一了开发环境、数据库表、实体层后，他们负责了商城的前台，我负责后台。可以说我们三人都是"全栈"工程师吧(手动狗头)，任务是按照功能划分的而不是技术要求，每个人都要进行从数据库( `database` )到数据操作层( `dao` )、实体层 `entity` 、服务层 `service` 、控制层 (controller) 、视图层( `view` ) 的全流程实现...



### 项目演示与源码

`github` 项目地址:
["口袋小食"web商城](https://github.com/AUROCHSY/ShopProject)

线上演示地址: 
//to supply
本地部署指导:
//to supply



### 技术栈

**架构模式：**MVC

**前端：**
`html` 、`css` 、 `javascript` 、 `jquery` 、 `thymeleaf`  、`Ajax`
**后端：**
`java` `Spring boot`   `maven` `mybatis`
**数据库：**
`MySQL`



### 实现了的功能
#### 前台
- 登录
- 注册
- 商品搜索
- 首页最新商品展示
- 首页二级分类动态展示及跳转(依据固定的一级分类)
- 购物车
  - 商品增删
  - 结算
  - 收货地址编辑
- 个人中心
  - 个人信息展示
- 我的交易
  - 订单管理(订单列表显示)



#### 后台

- 用户管理
  - 列表显示
  - 信息编辑
  - 信息搜索
- 类型管理
  - 类型列表显示
  - 类型添加
  - 类型编辑
  - 类型删除
  - 类型搜索
- 品牌管理：
  - 品牌列表显示
  - 品牌添加
  - 品牌编辑
  - 品牌删除
  - 品牌搜索
- 商品管理
  - 商品列表显示
  - 商品添加
  - 商品编辑
  - 商品搜索
- 账单管理 
  - 账单列表显示
  - 账单状态编辑



### 进一步完善思路

#### 前台需要完善的地方

bug比较多，比如说登录时没有未注册账号反馈、新注册用户登录后点击个人中心会报错(因为会执行查询余额的SQL语句，而数据库返回为空会报错)

还有很多功能可以实现，在实现了的功能中来回跳转也会报错(因为在各个页面的跳转链接没有完善...)

#### 后台需要完善的地方

数据库中账单表是商品表的子表，所以删除商品的前提是删除所有其有关的账单，而实际场景中账单是不应该被删除的，所以商品最好做个 `归档` 的功能而不是删除。

代码复用做得也不好，控制层和前端的传值的相关代码有很多重复，应该可以通过封装函数来减少代码重复。

各个功能的页面其实是直接 `th:replace` 在主页面 `index.html` 里的，这是老师一开始给的静态文件的方案，这种将全部子功能的页面放在一个页面上不太妥，而且我也是每次刷新主页后将全部5个主功能的对象全部传递到 `index.html` 里的，感觉这种设计很不好，实际使用时不太有必要，不应该是一次就使5个功能就绪的



### 技术介绍与扩展

#### MVC 设计模式介绍
MVC是Model-View-Controller(*模型-视图-控制器)*的缩写，是一种混合设计模式。用到这种设计模式时，我们所创建的对象要分为：Model 对象，View对象和Controller对象。

Model对象：负责存储数据以及定义如何操作这些数据。

View对象：负责展示而且允许用户编辑来自应用程序的Model对象，View对象用来构建用户界面，与用户交互。

Controller对象：是Model对象与View对象的中间人，负责传递数据，监听各种事件，管理其他对象的生命周期等。



#### MVC与经典三层结构的关系

> 三层架构绝不是MVC！！
>
> 三层架构是界面层（UI）业务逻辑层（BLL）和数据访问层（DAL）构成的，而MVC是模型层（M）界面层（View）和控制层（Controller）构成的，而且他们之间也不对应。
> 如果硬要给他们对应的话，那么三层架构中的UI对应MVC中的view（jsp），都是用于显示以及获取界面的数据；三层架构中的BLL层和DAL层对应MVC中的Model（javabean）层都是用于处理上层传递来的数据以及从数据库获取的数据的；MVC中的Controller（Servlet）最多算是三层架构中的UI的一部分，也就我们常说的是Servlet。
>
> 其实三层架构和MVC是一样的！！！我们所看到的不一样只是表面上的不一样。核心的东西是一致的，那么什么是核心？
> 答曰：分层，解耦！
> 如果从解耦的角度来看三层架构和MVC其实他们是一致的，只不过划分的方法不一样罢了，就像上面的图所示。从这一点说他们可以说是一个东西。这就相当于我们看到馒头和面条一样，表面上看他们不一样（注意仅仅是表面）但是他们核心是一致的，都是面……

拓展链接：[mvc与三层结构终极区别](https://blog.csdn.net/csh624366188/article/details/7183872)



#### `Spring`框架是什么

Spring是一个开源的轻量级Java SE（Java 标准版本）/Java EE（Java 企业版本）开发应用框架，其目的是用于简化企业级应用程序开发。应用程序是由一组相互协作的对象组成

拓展链接：[Spring是什么](https://blog.csdn.net/lp1052843207/article/details/51253071)



#### `Spring` 、`Spring boot`、`Spring MVC` 概念辨析

`Spring`  是一个“引擎”；

`Spring MVC` 是基于`Spring` 的一个 `MVC`  框架，只是 `Spring`  处理 `web` 层请求的一个模块。

`Spring Boot` 是基于 `Spring4` 的条件注册的一套快速开发整合包, 是通过 `Spring` 提供的一系列解决方案，包括MVC, boot不是框架，是一个集成很多优秀框架的脚手架。

- `Spring` 框架就像一个家族，有众多衍生产品例如 boot、security、jpa等等。

  但他们的基础都是Spring 的 ioc和 aop ioc 提供了依赖注入的容器 aop ，解决了面向横切面的编程，然后在此两者的基础上实现了其他延伸产品的高级功能。

- `Spring MVC` 是Spring的一个模块，是基于 `Servlet` 的一个 `MVC` 框架。

  主要解决 WEB 开发的问题，因为 Spring 的配置非常复杂，各种XML、 JavaConfig、hin处理起来比较繁琐。于是为了简化开发者的使用，从而创造性地推出了Spring boot，约定优于配置，简化了spring的配置流程。

  Spring MVC提供了一种轻度耦合的方式来开发web应用，通过Dispatcher Servlet, ModelAndView 和 View Resolver，开发web应用变得很容易。解决的问题领域是网站应用程序或者服务开发——URL路由。Session、模板引擎、静态Web资源等等。

- `Spring Boot` 实现了自动配置，降低了项目搭建的复杂度,它并不是用来替代 `Spring` 的解决方案，而是和`Spring` 框架紧密结合用于提升 `Spring` 开发者体验的工具。

  众所周知Spring框架需要进行大量的配置，Spring Boot引入自动配置的概念，让项目设置变得很容易。Spring Boot本身并不提供Spring框架的核心特性以及扩展功能，只是用于快速、敏捷地开发新一代基于Spring框架的应用程序。也就是说，它并不是用来替代Spring的解决方案，而是和Spring框架紧密结合用于提升Spring开发者体验的工具。

  同时它集成了大量常用的第三方库配置(例如Jackson, JDBC, Mongo, Redis, Mail等等)，Spring Boot应用中这些第三方库几乎可以零配置的开箱即用(out-of-the-box)，大部分的Spring Boot应用都只需要非常少量的配置代码，开发者能够更加专注于业务逻辑。

- **Spring Boot只是承载者，辅助你简化项目搭建过程的。如果承载的是WEB项目，并且使用Spring MVC作为MVC框架，那么工作流程不变的，因为这部分工作是Spring MVC做的而不是Spring Boot。**

对使用者来说，换用 `Spring Boot` 以后，项目初始化方法变了，配置文件变了，另外就是不需要单独安装 `Tomcat` 这类容器服务器了，`maven` 打出 `jar` 包直接跑起来就是个网站，但你最核心的业务逻辑实现与业务流程实现没有任何变化。



拓展链接：[spring boot与spring mvc的区别是什么？]([https://www.zhihu.com/question/64671972](https://www.zhihu.com/question/64671972)
)



#### `Servlet` 是什么

`Java Servlet` 是运行在 Web 服务器或应用服务器上的 Java 程序。

它是作为来自 Web 浏览器 / 其他 HTTP 客户端的请求和 HTTP 服务器上的数据库或应用程序之间的中间层。

使用 Servlet，您可以收集来自网页表单的用户输入，呈现来自数据库或者其他源的记录，还可以动态创建网页。

拓展链接： [Servlet 是什么？](https://www.runoob.com/servlet/servlet-intro.html)



#### `Tomcat` 是什么

`Tomcat` 是一个被广泛使用的Java Web应用服务器。

是一个`Servlet` 容器，是Apache的扩展，处理动态网页部分。

> ```
> 在做web项目时，多数需要http协议，也就是基于请求和响应，比如你在百度输入一行内容搜索，
> 那么百度服务器如何处理这个请求呢，他需要创建servlet来处理，servlet其实就是java程序，只是在服务器端的java程序
> servlet通过配置文件拦截你的请求，并进行相应处理，然后展示给你相应界面，那么servlet如何创建？ 这时候tomcat用到了，
> 它就是帮助你创建servlet的东西，所以也称web容器
> ```

Tomcat是用Java语言编写的，需要运行在Java虚拟机上，所以一般需要先安装JDK，以提供运行环境。

类似功能的还有：`Jetty` 、`Resin` 、`Websphere` 、`weblogic` 、`JBoss` 、`Glassfish` 、`GonAS` 等



拓展链接：

[Toncat是什么](https://blog.csdn.net/yilaguandemei/article/details/78994650)

[Tomcat(一) Tomcat是什么：Tomcat与Java技术 Tomcat与Web应用 以及 Tomcat基本框架及相关配置](https://blog.csdn.net/tjiyu/article/details/54590258)



#### `MyBatis` 是什么
`MyBatis` 是一个 Java 持久化框架，它通过`XML` 描述符或注解把对象与存储过程或 `SQL` 语句关联起来。

`MyBatis` 对纯 `JDBC` 做了封装，使程序员能够以面向对象的思想操作数据库。 
**说白了，Mybatis就是JDBC的壳儿！**

MyBatis是众多ORM框架的一种，常见的ORM框架有 `jpa` ，`hibernate` ，`mybatis` 。

ORM: 对象关系映射(Object-relational mapping)

> 当我们使用一种面向对象的编程语言来进行应用程序开发时，从项目一开始就采用的是面向对象分析、面向对象设计、面向对象编程，但是到了访问数据库时，又必须重返关系数据库的访问方式，即写sql。这是一种很糟糕的感觉。于是人们需要一种工具，它可以把关系数据库包装成面向对象的模型，这个工具就是ORM。随着面向对象数据库的广泛使用，orm也会逐渐消亡。
> 只要依然采用面向对象程序设计语言，底层依然采用关系数据库，中间就少不了mybatis。采用mybatis后，我们的程序就不再直接访问底层数据库，而是以面向对象的方式来操作持久化对象（例如，创建、修改、删除等）。而mybatis则将这些操作转换成底层的sql操作。——-mybaits其实是个壳

使用示例

```xml
<!--更新--> 	
<update id="update" parameterType="zhongfucheng.Student">  		
    update students set name=#{name},sal=#{sal} where id=#{id};  	
</update>
```



拓展链接：

[MyBatis是什么？](https://blog.csdn.net/weixin_36571185/article/details/78554514)

[MyBatis入门示例](https://juejin.im/post/5aa5c6fb5188255587232e5a)



#### `JDBC` 是什么

`JDBC` 是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，提供了诸如查询和更新数据库中数据的方法。



#### `Maven` 是什么
`Maven` 是一个项目管理工具。

它包含了一个项目对象模型 (POM：Project Object Model)，一组标准集合，一个项目生命周期(Project Lifecycle)，一个依赖管理系统(Dependency Management System)，和用来运行定义在生命周期阶段(phase)中插件(plugin)目标(goal)的逻辑。

通俗地说，`Maven` 可以简化和标准化项目建设过程，即能帮你构建工程，管理 `jar` 包，编译代码，还能帮你自动运行单元测试，打包，生成报表，部署项目等。

参考链接：[Maven是什么](Maven是什么)



#### `Thymeleaf` 是什么

`Thymeleaf` 是一个Java库。它是一个XML / XHTML / HTML5模板引擎,能够应用于转换模板文件,以显示您的应用程序产生的数据和文本。

它尤其适合于基于XHTML / HTML5的web服务应用程序,同时它可以作为web或独立的应用程序处理任何XML文件.

`Thymeleaf` 的主要目标是提供一种优雅的、可维护性强的方法来创建模版。

简单来说就是它可以接收来自后台的数据并展示在前端页面上。

在HTML页面中使用 `Thymeleaf`的方式：

在网页头加上

```
<html xmlns:th="http://www.thymeleaf.org">
```

就可以在网页上使用 `Thymeleaf` 的语法了

[Thymeleaf语法简介](https://www.jianshu.com/p/a7056b023df0)

`Thymeleaf` 接收后台的语法

[Spring boot+Thymeleaf传递数据](https://www.jianshu.com/p/778779e1d40d)
