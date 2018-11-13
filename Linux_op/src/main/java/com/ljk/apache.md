##### 在Windows上部署apache服务,可以通过wget命令获取Windows上的文件
###### 下载 
```aidl
    在官网上下载Windows版本的apache安装包
```
###### 部署
    * 解压后在conf文件夹下有一个httpd.conf的文件
    * Define SRVROOT "C:\Users\lijk_\Downloads\Apache24",设置apache所在的目录
    * Listen 12.34.56.78:80
      Listen 8080 访问端口 需要在防火墙的入站规则处增加8080端口
    * ServerName 10.0.7.196:8080 [IP:port]
    * DocumentRoot "C:\A-MyWork\GitR\git\kylo" 设置根目录
      <Directory "C:\A-MyWork\GitR\git\kylo">
##### 可以通过monitor启动