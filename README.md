# MysqlHelper

##  说明

这是一个基于Java JDBC的简单的数据库连接工具类，它封装了一些SQL语句，每次执行操作后都会自动释放资源，并且避免一些频繁的SQL语句编写。



## 使用

### 初始化

通过构造函数以初始化连接信息。本项目提供两种构造函数

```java
MysqlHelper mysql = new MysqlHelper(
                    "localhost",		//主机地址
                    3306,				//端口号
                    "db_name",			//数据库名称
                    "db_user",			//数据库用户名
                    "db_password",		//数据库密码
                    true,				//是否使用Unicode
                    "utf8"				//编码
);
```

本项目提供了省略了一些参数的构造函数，这些参数将设为默认值：

* 端口号`3306`
* 使用`Unicode`
* 编码方式`utf8`

```java
MysqlHelper mysql = new MysqlHelper(
                    "localhost",		//主机地址
                    "db_name",			//数据库名称
                    "db_user",			//数据库用户名
                    "db_password",		//数据库密码
);
```



### 查询

* 通过执行SQL语句查询（不推荐）

```java
/**
* @Description: 通过执行SQL语句来进行查询
* @Param: [sql:sql语句]
* @Return: java.lang.String 对应查询结果的JSON字符串
* @Author: CairBin
* @Dates: 2023/6/11
*/
public String execQuery(String sql) throws SQLException;
```

* 查询表内所有数据

```java
/**
* @Description: 查询表内的所有列的数据
* @Param: [tbName:表名]
* @Return: java.lang.String 查询结果的JSON字符串
* @Author: CairBin
* @Dates: 2023/6/11
*/
public String query(String tbName) throws SQLException;
```

* 指定列的查询

```java
/**
* @Description: 查询表内一个或多个列的所有数据，如果queryKeys长度为0，将返回所有列的数据
* @Param: [tbName:表名, queryKeys:所查询的列名]
* @Return: java.lang.String 查询结果的JSON字符串
* @Author: CairBin
* @Dates: 2023/6/11
*/
public String query(String tbName, String[] queryKeys) throws SQLException;
```

* 指定Where条件的查询

```java
/**
* @Description: 查询表内一个或多个列满足某些条件的数据。如果queryKeys长度为0则指定条件的所有列。如果conditions长度为0则返回指定列的所有数据。
* @Param: [tbName:表名, queryKeys:查询列, conditions:条件]
* @Return: java.lang.String
* @Author: CairBin
* @Dates: 2023/6/11
*/
public String query(String tbName, String[] queryKeys, Map<String,String> conditions)throws SQLException;
```



### 插入

* 执行SQL语句的插入

```java
/**
* @Description: 执行sql语句来插入数据
* @Param: [sql:sql语句]
* @Return: int 插入成功的数量
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int execInsert(String sql) throws SQLException;
```

* 通过传入Map插入数据

```java
/**
* @Description: 向指定表插入一条数据
* @Param: [tbName:表名, data:插入的数据(对应的列名和值)]
* @Return: int 插入成功的数量
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int insert(String tbName,Map<String,String>data) throws SQLException;
```

### 更新

* 执行SQL语句的更新（不推荐）

```java
/**
* @Description: 执行sql语句更新数据
* @Param: [sql:sql语句]
* @Return: int 更新条目数
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int execUpdate(String sql) throws SQLException;
```

* 通过接口传入Map的更新

```java
/**
* @Description: 修改表中的特定一行或多行的数据
* @Param: [tbName:表名, data:数据(对应列名和值), conditions:查询条件]
* @Return: int 更新条目数
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int update(String tbName,Map<String,String>data,Map<String,String>conditions) throws SQLException;
```

### 删除

* 通过执行SQL语句的删除

```java
/**
* @Description: 执行sql语句来删除数据
* @Param: [sql:sql语句]
* @Return: int 删除的条目数
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int execDelete(String sql) throws SQLException;
```

* Map指定条件的删除

```java
/**
* @Description: 执行sql语句来删除数据
* @Param: [sql:sql语句]
* @Return: int 删除的条目数
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int execDelete(String sql) throws SQLException;
```

### 其他方法

* 获取端口号

```java
/**
* @Description: 获取端口号
* @Param: []
* @Return: int 端口号
* @Author: CairBin
* @Dates: 2023/6/11
*/
public int getPort();
```

* 获取主机地址

```java
/**
* @Description: 获取数据库主机地址
* @Param: []
* @Return: java.lang.String 主机地址
* @Author: CairBin
* @Dates: 2023/6/11
*/
public String getAddress();
```

* 获取编码方式

```java
/**
* @Description: 获取连接数据库的编码方式
* @Param: []
* @Return: java.lang.String 编码字符集
* @Author: CairBin
* @Dates: 2023/6/11
*/
public String getCharacterEnconding();
```

* 判断是否启用Unicode

```java
/**
* @Description: 判断是否使用了Unicode
* @Param: []
* @Return: boolean 返回结果
* @Author: CairBin
* @Dates: 2023/6/11
*/
public boolean isUseUnicode();
```

* 设置端口号

```java
/**
* @Description: 设置端口号
* @Param: [port:端口号]
* @Return: void
* @Author: CairBin
* @Dates: 2023/6/11
*/
public void setPort(int port);
```

* 设置主机地址

```java
/**
* @Description: 设置数据库主机地址
* @Param: [address：主机地址]
* @Return: void
* @Author: CairBin
* @Dates: 2023/6/11
*/
public void setAddress(String address);
```

* 设置用户名

```java
/**
* @Description: 设置连接数据库的用户名
* @Param: [user:用户名]
* @Return: void
* @Author: CairBin
* @Dates: 2023/6/11
*/
public void setUser(String user);
```

* 设置密码

```java
/**
* @Description: 设置连接密码
* @Param: [password:连接密码]
* @Return: void
* @Author: CairBin
* @Dates: 2023/6/11
*/
public void setPassword(String password);
```

* 启用Unicode

```java
/**
* @Description: 设置启用Unicode
* @Param: [flag:true为启用false为不启用]
* @Return: void
* @Author: CairBin
* @Dates: 2023/6/11
*/
public void useUnicode(boolean flag);
```

* 设置编码规则

```java
/**
* @Description: 设置编码方式
* @Param: [encoding: 编码规则名称]
* @return: void
* @Author: CairBin
* @Dates: 2023/6/11
*/
public void setCharacterEncoding(String encoding);
```

* 以单引号修饰JAVA字符串的两端

```java
/**
* @Description: 静态方法，将JAVA字符串的两端添加单引号，以转换成sql语句中的字符串类型，防止执行sql语句时报错。 如"sql" --> "'sql'"。
* @Param: [str:所要修饰的字符串]
* @Return: java.lang.String 修饰后的字符串
* @Author: CairBin
* @Dates: 2023/6/11
*/
public static String toSqlString(String str);
```

## 注意事项（必看）

由于是通过拼接字符串实现的，当数据库中字段类型为字符串时，在SQL语句中其值要在两端添加单引号(')以消除歧义，通过本项目使用传入`Map`的接口并不会自动完成这一过程，**请通过**`MysqlHelper.toSqlString(String str)`**方法来解决这一问题**

例如

```java
//mysqlHelper是MysqlHelper实例化对象
mysqlHelper.delete("tb_name",new HashMap<String,String>{{
    put("name",MysqlHelper.toSqlString("cairbin"));
}});
```

这等价于SQL语句：

```sql
DELETE FROM tb_name WHERE name='cairbin';
```



以下是**错误**的使用方法

```java
//mysqlHelper是MysqlHelper实例化对象
mysqlHelper.delete("tb_name",new HashMap<String,String>{{
    put("name","cairbin");
}});
```

其等价于SQL语句：

```sql
DELETE FROM tb_name WHERE name=cairbin;
```

