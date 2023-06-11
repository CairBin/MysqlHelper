package pers.cairbin.mysqlhelper;

/**
 * @Description: Mysql操作辅助工具类
 * @Author: CairBin
 * Date:2023-06-11
 */

import java.sql.*;
import java.util.Map;
import org.json.*;

public class MysqlHelper {
    private String connStr;         //连接字符串
    private String user;            //用户名
    private String password;        //密码
    private String host_address;    //数据库主机地址
    private int host_port;          //数据库主机端口
    private boolean useUnicode;     //启用Unicode的标志变量
    private String charaEncoding;   //编码规则

    /**
    * @Description: 构造函数，初始化数据库连接信息和加载MySQL驱动
    * @Param: [host_address:主机地址, host_port:主机端口, dbName:数据库名称, user:用户名, password:密码, useUnicode:启用Unicode, characterEncoding:编码方式]
    * @Return:
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public MysqlHelper(String host_address,int host_port,String dbName,String user,String password,boolean useUnicode,String characterEncoding) throws ClassNotFoundException {
        this.user = user;
        this.password = password;
        this.host_port = host_port;
        this.host_address = host_address;
        this.useUnicode = useUnicode;
        this.charaEncoding = characterEncoding;
        this.connStr = String.format("jdbc:mysql://%s:%d/%s?useUnicode=%s&characterEncoding=%s",host_address,host_port,dbName,Boolean.toString(useUnicode),characterEncoding);
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
    * @Description: 构造函数。默认3306端口，启用Unicode,使用utf8编码，其他参数由用户指定。
    * @Param: [host_address:主机地址, dbName:数据库名, user:用户名, password:密码]
    * @Return:
    * @Author: CairBin
    * @Dates: 2023/6/12
    */
    public MysqlHelper(String host_address,String dbName,String user,String password) throws ClassNotFoundException {
        this(host_address,3306,dbName,user,password,true,"utf8");
    }

    /**
    * @Description: 获取端口号
    * @Param: []
    * @Return: int 端口号
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int getPort(){
        return this.host_port;
    }
    
    /**
    * @Description: 获取数据库主机地址
    * @Param: []
    * @Return: java.lang.String 主机地址
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public String getAddress(){
        return this.host_address;
    }
    
    /**
    * @Description: 获取连接数据库的编码方式
    * @Param: []
    * @Return: java.lang.String 编码字符集
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public String getCharacterEnconding(){
        return this.charaEncoding;
    }
    
    /**
    * @Description: 判断是否使用了Unicode
    * @Param: []
    * @Return: boolean 返回结果
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public boolean isUseUnicode(){
        return this.useUnicode;
    }
    
    /**
    * @Description: 设置端口号
    * @Param: [port:端口号]
    * @Return: void
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public void setPort(int port){
        this.host_port = port;
    }

    /**
    * @Description: 设置数据库主机地址
    * @Param: [address：主机地址]
    * @Return: void
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public void setAddress(String address){
        this.host_address = address;
    }

    /**
    * @Description: 设置连接数据库的用户名
    * @Param: [user:用户名]
    * @Return: void
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public void setUser(String user){
        this.user = user;
    }

    /**
    * @Description: 设置连接密码
    * @Param: [password:连接密码]
    * @Return: void
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public void setPassword(String password){
        this.password = password;
    }

    /**
    * @Description: 设置启用Unicode
    * @Param: [flag:true为启用false为不启用]
    * @Return: void
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public void useUnicode(boolean flag){
        this.useUnicode = flag;
    }

    /**
    * @Description: 设置编码方式
    * @Param: [encoding: 编码规则名称]
    * @return: void
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public void setCharacterEncoding(String encoding){
        this.charaEncoding = encoding;
    }

    /**
    * @Description: 私有方法，用于为每个操作提供数据库连接
    * @Param: []
    * @Return: java.sql.Connection 连接对象
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.connStr,user,password);
    }

    /**
    * @Description: 将Map对象转化成SQL语句需要的字符串，如("id",1) --> "id=1"
    * @Param: [data:Map对象]
    * @Return: java.lang.String
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    private String mapToString(Map<String,String> data){
        int lenMap = data.size();
        if(lenMap==0)
            return "";

        String result = "";
        for(Map.Entry<String,String>entry:data.entrySet()){
            lenMap--;
            result+=entry.getKey()+"="+entry.getValue();
            if(lenMap!=0)
                result+=",";
        }

        return result;
    }

    /**
    * @Description: 将数组中的字符串以逗号(,)连接起来以用于插入SQL语句
    * @Param: [array:数组对象]
    * @Return: java.lang.String
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    private String arrayToString(String[] array){
        int len = array.length;
        if(len==0)
            return "";
        String res = "";
        for(int i=0;i<len-1;i++)
            res += array[i]+",";

        res+=array[len-1];
        return res;
    }

    /**
    * @Description: 通过执行SQL语句来进行查询
    * @Param: [sql:sql语句]
    * @Return: java.lang.String 对应查询结果的JSON字符串
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public String execQuery(String sql) throws SQLException {
        Connection conn = this.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();

        int cols = metaData.getColumnCount();
        JSONArray array = new JSONArray();

        while(rs.next()){
            JSONObject obj = new JSONObject();
            for(int i=1;i<=cols;++i){
                String colName = metaData.getColumnLabel(i);
                String value = rs.getString(colName);
                obj.put(colName,value);
            }
            array.put(obj);
        }

        rs.close();
        stat.close();
        conn.close();

        return array.toString();
    }

    /**
    * @Description: 查询表内的所有列的数据
    * @Param: [tbName:表名]
    * @Return: java.lang.String 查询结果的JSON字符串
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public String query(String tbName) throws SQLException {
        String sql = "SELECT * FROM " + tbName;
        return this.execQuery(sql);
    }

    /**
    * @Description: 查询表内一个或多个列的所有数据，如果queryKeys长度为0，将返回所有列的数据
    * @Param: [tbName:表名, queryKeys:所查询的列名]
    * @Return: java.lang.String 查询结果的JSON字符串
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public String query(String tbName, String[] queryKeys) throws SQLException {
        int lenKeys = queryKeys.length;
        String sel = "";
        if(lenKeys==0){
            sel="*";
        }else{
            sel = this.arrayToString(queryKeys);
        }

        String sql = String.format("SELECT %s FROM %s",sel,tbName);
        return this.execQuery(sql);
    }

    /**
    * @Description: 查询表内一个或多个列满足某些条件的数据。如果queryKeys长度为0则指定条件的所有列。如果conditions长度为0则返回指定列的所有数据。
    * @Param: [tbName:表名, queryKeys:查询列, conditions:条件]
    * @Return: java.lang.String
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public String query(String tbName, String[] queryKeys, Map<String,String> conditions)throws SQLException{
        int lenKeys = queryKeys.length;
        String sel = "";
        if(lenKeys==0){
            sel="*";
        }else{
            sel = this.arrayToString(queryKeys);
        }

        String where = "";
        int lenCond = conditions.size();
        if(lenCond==0){
            where = "";
        }else{
            where=" WHERE ";
            where+=this.mapToString(conditions);
        }
        String sql = String.format("SELECT %s FROM %s%s",sel,tbName,where);
        return this.execQuery(sql);
    }

    /**
    * @Description: 执行sql语句来插入数据
    * @Param: [sql:sql语句]
    * @Return: int 插入成功的数量
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int execInsert(String sql) throws SQLException {
        Connection conn = this.getConnection();
        Statement stat = conn.createStatement();

        int res = stat.executeUpdate(sql);
        stat.close();
        conn.close();

        return res;
    }

    /**
    * @Description: 向指定表插入一条数据
    * @Param: [tbName:表名, data:插入的数据(对应的列名和值)]
    * @Return: int 插入成功的数量
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int insert(String tbName,Map<String,String>data) throws SQLException {
        int dataSize = data.size();
        if(dataSize == 0)
            return -1;
        String keys = "";
        String vals = "";
        for(Map.Entry<String,String>entry:data.entrySet()){
            dataSize--;
            keys+=entry.getKey();
            vals += entry.getValue();
            if(dataSize!=0){
                keys += ",";
                vals +=",";
            }
        }


        String sql = String.format("INSERT INTO %s(%s) VALUES(%s)",tbName,keys,vals);

        return this.execInsert(sql);
    }

    /**
    * @Description: 执行sql语句更新数据
    * @Param: [sql:sql语句]
    * @Return: int 更新条目数
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int execUpdate(String sql) throws SQLException {
        Connection conn = this.getConnection();
        Statement stat = conn.createStatement();

        int res = stat.executeUpdate(sql);
        stat.close();
        conn.close();

        return res;
    }

    /**
    * @Description: 修改表中的特定一行或多行的数据
    * @Param: [tbName:表名, data:数据(对应列名和值), conditions:查询条件]
    * @Return: int 更新条目数
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int update(String tbName,Map<String,String>data,Map<String,String>conditions) throws SQLException{
        int dataSize = data.size();
        int lenCond = conditions.size();

        if(dataSize==0 || lenCond == 0)
            return -1;

        String sets = this.mapToString(data);

        String where = this.mapToString(conditions);

        String sql = String.format("UPDATE %s SET %s WHERE %s",tbName,sets,where);
        return this.execUpdate(sql);
    }

    /**
    * @Description: 执行sql语句来删除数据
    * @Param: [sql:sql语句]
    * @Return: int 删除的条目数
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int execDelete(String sql) throws SQLException {
        Connection conn = this.getConnection();
        Statement stat = conn.createStatement();
        int res = stat.executeUpdate(sql);

        stat.close();
        conn.close();
        return res;
    }

    /**
    * @Description: 删除表中特定一行或多行的数据
    * @Param: [tbName:表名, conditions:条件]
    * @Return: int 删除的条目数
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public int delete(String tbName, Map<String,String>conditions) throws SQLException {
        if(conditions.size()==0)
            return -1;
        String where = this.mapToString(conditions);
        String sql = String.format("DELETE FROM %s WHERE %s",tbName,where);

        return this.execDelete(sql);
    }

    /**
    * @Description: 静态方法，将JAVA字符串的两端添加单引号，以转换成sql语句中的字符串类型，防止执行sql语句时报错。 如"sql" --> "'sql'"。
    * @Param: [str:所要修饰的字符串]
    * @Return: java.lang.String 修饰后的字符串
    * @Author: CairBin
    * @Dates: 2023/6/11
    */
    public static String toSqlString(String str){
        return "'"+str+"'";
    }

}
