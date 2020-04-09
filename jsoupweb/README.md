# **疫情数据抓取及持久化**

## 一、主要实现

### 后台

通过 **定时任务** 持久化数据，定时策略为每10分钟持久化一次

通过**controller**层暴露数据，api在线文档地址 http://localhost:8080/swagger-ui.html

### 前台

使用Lay-UI、百度Echarts、Vue完成疫情数据的可视化

## 二、使用技术

- 使用jsoup获取网页源代码
- 使用gson做对象转换
- 使用正则获取json数据
- 使用mybatis-plus-genarator生成基础的crud代码
- 使用反射技术将对象转为Map

## 三、问题解决

### 全国疫情详情数据的更新问题

疫情数据使用mybatis-plus的saveOrUpdateBatch方法，即判断主键是否存在，不存在则添加记录，存在则更新记录

### 从数据库获取全国疫情数据

~~一开始使用了分步查询，已弃用~~

现使用左连接查询