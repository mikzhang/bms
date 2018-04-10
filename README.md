# EasyWeb
## 简介
前后端分离模式的后端管理系统模板，前端页面使用路由实现单页面应用，后端接口遵循RESTful风格。<br/>
演示地址：[http://115.159.40.243:8080/EasyWeb](http://115.159.40.243:8080/EasyWeb)。<br/>
演示账号：easyweb 密码：123456  ，服务器比较渣，第一次访问速度会慢一点(主要是远程数据库连接比较慢)，请多多包涵！ 
   
## 使用技术
   本项目所选用的全部技术框架如下表，数据库sql文件位于/WebRoot/sql/目录中，sql导入之后把admin的密码改成d9892738d09afe06f886cc4dd7296da3，密码是123456：

后端 | ... 
:---:|:---
核心框架 | spring、springmvc、mybatis
连接池 | Alibaba Druid
缓存框架 | Redis、[RedisUtil](https://github.com/whvcse/RedisUtil)
权限框架 | [JwtPermission](https://github.com/whvcse/JwtPermission)、jjwt
图片验证码(支持gif) | [EasyCaptcha](https://github.com/whvcse/EasyCaptcha)
密码加密 | [EndecryptUtil](https://github.com/whvcse/EndecryptUtil)

前端 | ... 
:---:|:---
核心框架(轻量简洁) | [Layui](http://www.layui.com/)、[jQuery](http://jquery.cuishifeng.cn/)
路由框架(纯js打造) | [Q.js](https://github.com/itorr/q.js) (超级轻量、简单易学)

------------------------

## API接口
部分接口示例，全部接口请查看项目的controller层：

|              |         api        | 请求方式 |        请求参数       |
|:--------------:|:------------------|:----------:|:---------------------|
|     登录     | /api/login         |   post   | account、password     |
|   导航菜单   | /api/menu          |    get   | token                 |
|   用户列表   | /api/user          |    get   | token                 |
|   添加用户   | /api/user          |   post   | token、user           |
|   修改用户   | /api/user          |    put   | token、user           |
| 修改用户状态 | /api/user/status   |    put   | token、userId、status |
|   删除用户   | /api/user/{usetId} |  delete  | token                 |
|   用户信息   | /api/user/{userId} |    get   | token                 |
|      ...     |         ...        |    ...   |          ...          |

这里接口全部加/api/前缀是因为前后端在一个项目里面，为了方便区分静态资源和接口，实际项目建议前后端分开部署，分开部署需要解决跨域问题，跨域一般采用node进行代理，详情可加群深度交流:smirk:。

-----

## 项目截图
![登录](https://raw.githubusercontent.com/whvcse/EasyWeb/master/WebRoot/assets/images/screenshot_login.png) 
![用户管理](https://raw.githubusercontent.com/whvcse/EasyWeb/master/WebRoot/assets/images/screenshot_user.png)
![角色管理](https://raw.githubusercontent.com/whvcse/EasyWeb/master/WebRoot/assets/images/screenshot_role.png)
![权限管理](https://raw.githubusercontent.com/whvcse/EasyWeb/master/WebRoot/assets/images/screenshot_permission.png)
![登录日志](https://raw.githubusercontent.com/whvcse/EasyWeb/master/WebRoot/assets/images/screenshot_loginrecode.png)
 
 ---------------------------
 
## 技术选型说明
### 说明1、为什么不用Shiro、Auth2.0等知名权限框架？
  Shiro是基于Session的权限框架，并太适合于前后端分离的项目，和REST的规范([什么是RESTful规范？](#1什么是restful)),JWT(JSON Web Token)比较适用于前后端分离的项目。 
网上也有Shiro整合JWT的教程，步骤有些繁琐，并且这种做法有点伤筋动骨。 Auth2.0主要是做第三方应用授权的框架，像
我们常见的QQ、微信、微博授权登录这样的，学习成本高，对于企业项目的内部账号登录验证有点大材小用。 所以这里选用了[JwtPermission](https://github.com/whvcse/JwtPermission)来作为权限框架。
        
### 说明2、为什么不用Vue、React来做前后端分离？
一般公司的后台管理系统都是表格，界面也比较简单，所以前端的工作基本都是有后端程序员来完成的，后端人员大多都只会一个jquery和bootstrap，所以目前的管理系统大多都使用iframe、SiteMesh技术，包括还有使用jsp的，所以此项目选用了Q.js作为路由框架，选用layui作为核心框架，layui的数据表格还是不错的，相比Vue、react来说后端人员看看文档就会了。
       
### 说明3、为什么使用前后端分离？
具体为什么要分离可以看看论坛别人的讨论，我个人有以下几个观点：
1. 通过controller跳转和渲染页面(jstl、freemark等)，页面的加载速度会变得缓慢，不利于用户体验。 
2. APP的登录跟web不一样，app是只登一次并持久化(三个月、半年、甚至更久)，web是根session的过期时间来决定的，所以app项目的后端都会独立成一个项目，如果换成前后端分离的模式，换成基于token的验证，就可以只用一个后台叫api，前端不管是web、安卓、ios都可以公用。
3. 基于token的验证还可以解决多系统单点登录的问题，只要系统之间对于token验证方式一致，就可以一个token多个系统使用，如果是session还要共享session，很麻烦。
<p>注： 
    后端输出动态页面网页会打开慢，但是ajax渲染数据会不利于SEO优化，像文章、博客、新闻之类的网站最好是用后端动态输出页面可以利于SEO优化。</p> 

### 说明4、为什么使用RESTful风格的接口？
RESTful不是一种框架，也不是一种技术，更不是一种标准，只是一种规范、约束，我也是作为学习REST来使用于这个项目的，前后端分离也不一定要用RESTful，接口风格有RESTful、SOAP、JSON RPC等，各有所长，都可以来使用！
     
### 说明5、为什么使用路由实现单页面效果？
自古以来，网站都是分头部、左侧和底部的，在传统项目中，大多数都使用iframe或SiteMesh来实现，路由是最近比较流行的解决方案：
1. iframe，每个页面都要引入css、js，会重复加载资源，造成浪费。
2. SiteMesh，利用后端技术拼接渲染，浏览器加载页面还是全部刷新，左侧、头部、底部每次都重新加载一遍(访问速度慢)，用户体验不好。
3. 路由，算是真正的局部加载、局部刷新了，资源可以重复利用，提升用户操作体验！<br/>
<p>单页面的效果在客户端是必不可少的，比如APP中我们常见的底部三四个Tab切换，就是一个单页面的效果。 </p>  

-------------------
       
## 知识补充
### 1、什么是RESTful
大致可以总结以下三点：
1. URL代表资源。
   <p>例如关于用户的操作，url都应该是/user，不要带有动词</p>
2. HTTP请求代表动作。
  <p>HTTP有八种请求，分别代表请求服务器的不同的动作方式，添删改查分别对应post、delete、put、get请求，以前我们选用请求方式会考虑安全用post，乱码了换成post，这里说明一下，安全用HTTPS协议，POST也不安全，获取用GET，乱码可以用代码解决的</p>
3. HTTP是无状态的请求。
   <p>传统的WEB中，浏览器传递cookie，服务器保存session，两者一一对应保持请求的状态，代表是一次会话，在RESTful的规范中，每一次请求都是独立的，HTTP的请求是无状态的</p>
       
### 2、什么是token
   有人把token翻译成令牌，这个就很形象了，传统的web登录认证是把登录后的user存储在session中，判断session中有无user决定是否登录，如果要做记住密码或者持久化登录(APP)就有很大缺陷，浏览器会默认传递cookie代表一次会话，但是app客户端的请求是没有cookie的(虽然有第三方的工具可以实现cookie)，并且多系统单点登录还要共享session。<br/><br/>
   token认证的概念就是，使用账号密码验证之后服务器认为用户合法，给用户发一个令牌，令牌存储在客户端，js可以用localStorage，app存储在手机中，这个令牌包含一些权限，用户拿着这个令牌可以访问服务器的一些资源，客户端再访问每一个接口都需要传递这个令牌，可以放在header中统一设置，也可以放在参数中。  只要用户没有弄丢这个令牌，他就可以一直拿着这个令牌访问服务器的资源，不需要每次输入账号密码登录了，令牌有一个过期时间，过期了就失效了。如果用户弄丢令牌或者过期都需要重新登录，重新签发令牌 <br/><br/>
   举一个形象的例子，去酒店住房，第一次进去需要用身份证登记，然后给一个房卡，用身份证登记的过程就好比用账号密码登录，房卡就好比令牌token，然后你再进出酒店就拿着房卡就行了，不需要每次拿出身份证，这个房卡也是有一定的权限的，它只能开一个房间，不能开其他房间，我们token也是有权限的，你把房卡弄丢了你就要用身份证重新领一张房卡。
     
### 3、什么是前端路由
   我理解的就是页面局部加载，跟传统的局部刷新不同的就是url也会变化，是把一个url的内容加载到网页的局部区域，而不是整个页面跳转。  因为网站的url改变后浏览器默认是会跳转页面的，所以现在的路由普遍采用hash的方式，就是在url后面加#的方式，因为浏览器会忽略#后面的url，认为是同一页面，也有另一种方式，有兴趣的可以去详细了解一下。 
    
----------------
    
## 联系方式
### 1、欢迎加入“前后端分离技术交流群”：
![群二维码](https://raw.githubusercontent.com/whvcse/EasyWeb/master/WebRoot/assets/images/images_qqgroup.png)
      
### 2、点个star再走：
如果这个项目对您有帮助，请帮我留下一颗小星星，非常感谢！

### https://github.com/whvcse/EasyWeb 
