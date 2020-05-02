## session共享

#### 1.项目打包为两个，一个端口为8080，另一个端口为8081

#### 2.上传到linux的root目录

#### 3.依次运行以下两条命令

~~~.java
nohup java -jar sessionshare01-0.0.1-SNAPSHOT1.jar > 8080.log &
nohup java -jar sessionshare01-0.0.1-SNAPSHOT2.jar > 8081.log &
~~~

#### 4.浏览器访问 ip:8080

然后用户名输入user,密码用`cat 8080.log`命令获取

#### 5.登录成功后可以依次在浏览器访问查看

~~~.java
ip:8080/set
ip:8080/get
ip:8081/set
ip:8081/get
~~~

#### 6.配置Nginx负载均衡

执行

~~~.java
vi /usr/local/nginx/conf/nginx.conf
~~~

修改以下地方

~~~.json
...
 http {
     ...
     upstream qiangssvip.com{
         server 127.0.0.1:8080 weight=1;
         server 127.0.0.1:8081 weight=1;
     }
 
     server {
         ...
         location / {
             proxy_pass http://qiangssvip.com;
             proxy_redirect default;
             #root   html;
             #index  index.html index.htm;
         }
...
~~~

 

#### 7.重载Nginx配置

~~~.java
/usr/local/nginx/sbin/nginx -s reload
~~~

#### 8.浏览器访问

ip/get

http://192.168.163.128/get

会发现：网页循环显示

~~~.java
高大侠8080

高大侠8081
...
~~~

说明Nginx负载均衡配置成功

