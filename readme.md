HugeWorld 大世界
---
### 玩法
>支持多人同时在线
> 
> [点击前往查看玩法手册](https://docs.qq.com/doc/DTE5BaGdIcXJZdVBs)

### 客户端运行环境

> JAVA 1.8 +

### 服务部署环境

> JAVA 1.8+
> 
> (没有测试linux系统下能否运行(￣y▽,￣)╭ )

### 导出方式
#### 服务端
服务端需要开启三个服务程序HwServer,HwServer2,HwServer3。
这三个服务必备的类资源清单
1. hw/bean/*
2. hw/util/*
3. hw/server/ * [包含了三个服务]
4. sources/server.properties(外加)

你可以将三个服务拆开来导包，不过建议懒人模式： 
导出所有必要的资源后，将主类分别设置成HwServer,HwServer2,HwServer3
到时候三个服务都分别开启。<br/>
__当然啦，你也可以下载release的成品压缩包啦!__
#### 客户端
客户端的启动类在 hw/client/HwClient
客户端必备的类资源清单:
1. hw/audio/ *
2. hw/bean/ *
3. hw/client/ *
4. hw/frame/ *
5. hw/util/ *
6. sources/imgs/ * (外加)
7. sources/mp3/ * (外加)
8. sources/basic.properties (外加)

导出时只要剔除掉server包并且启动类设置成HwClient即可
__同样啦，你也可以下载release的成品压缩包啦!__
   
### 部署服务与配置
#### 服务器
由于分为了三个子服务，这样，倘若你的拥有多个服务器，
你就可以分布在三个服务器上部署啦，当然你也可以在一台服务器
运行三个服务。在启动服务前，修改/hw/sources/server.properties
中的相应属性:
* width 世界地图宽度
* p 世界陆地空闲系数(数值越大，大陆越多,墙越少)
* port1 ->HwServer  服务开启监听的端口
* port2 ->HwServer2服务开启监听的端口
* port3 ->HwServer3服务开启监听的端口

注意:
1. 如果在一台服务器上运行端口设置必须不同哦
2. 端口号建议设置在8090-10000区段
3. HwServer服务的压力相对比较小，可以放在最低配置的服务器上
4. p推荐在0.5-0.9设置

#### 客户端
发布前，配置/hw/sources/basic.properties<br/>
对应了三个服务开启的ip和端口号,有如下说明:
1. 客户端三个端口与服务器三个服务监听端口必须完全一样
2. 如果是仅一台服务器，那么三个ip应该是相同的
3. 如果是三台服务器分布架设，那么监听的端口是可以相同的

### TODO
* 用户控制声音[/hw/audio/AudioCenter:fullAudio]
* 扫描道具:扫描周边敌人心跳信号
* 弹道特效
* 用户访问服务白名单
* 更多的基建设施
* 以上我都不会做

### 开发者的话
当然啦，目前只是一个简单的架设，
如果你注意到了，右边的扫描功能根本就没写，只是虚设😀
这个项目也只是闲得没事干的时候写的，
至于后面是否会维护吗，那大概率是不会的，
因为我走的不是软件方向啊这，所以，
随便魔改什么的也是可以的。
提出Bug什么的我也是不会修的啦！
