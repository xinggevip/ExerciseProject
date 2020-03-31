# MybatisPlus笔记
## 一、默认规则
### 插入
默认对象属性为null或空则不插入
### 映射字段
默认开启驼峰属性映射
## 二、常用注解
### @TableName
默认对象名映射表名，如果不一致则可以使用@TableName("mp_user")指定表名
~~~.java
@Data
@TableName("mp_user")
public class User {
...
}
~~~
### @TableId
~~~.java
@Data
@TableName("mp_user")
public class User {

    // 如果默认的主键不是id，则需要在其字段上加上此注解指定
    @TableId
    private Long userId;
}
~~~
###　@TableField

@TableField("name")
private String realName;
    
~~~.java
@Data
@TableName("mp_user")
public class User {

    // 指定数据库中的字段
    @TableField("name")
    private String realName;
}
~~~
## 三、排除非字段的三种方式

- transient 关键字
  ~~~.java
  // 将不需要序列化的属性前添加关键字transient
  private transient String remark;
  ~~~
- static 关键字
- @TableField(exist = false)
  ```.java
  // 此注解exist属性默认为true，为数据库存在的字段，fasle，为数据不存在的字段
  @TableField(exist = false)
  private String remark;
  ```