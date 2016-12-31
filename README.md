# Ews-java-api-Demo
ews-java-api-2.0 demo  
由于工作需要时刻检查好几个outlook邮箱，而outlook客户端的通知总是不准，再加上看到了ews-java-api，于是就动手写了个小程序。

该程序需要将邮箱账户密码以键值对的形式配置在config.properties中,可配置多个。也可以在程序界面添加删除邮箱帐号密码。

程序设定每30秒去服务器检查一遍未读邮件，当读到邮件时会播放MP3,播放程序根目录下的“contra.mp3”,你可以自己加一个名为contra.mp3的文件放根目录。

程序界面点击stop会停止当前播放的MP3。
