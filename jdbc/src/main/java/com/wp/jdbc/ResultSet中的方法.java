package com.wp.jdbc;//package com.wp.jdbc;
//
//import javax.naming.InitialContext;
//import java.sql.*;
//
///**
// * Created by 王萍 on 2017/6/28 0028.
// */
//public class ResultSet中的方法 {
//    1 新定义了若干个常数
//
//    这些常数用于指定ResultSet 的类型游标移动的方向等性质，如下所示：
//    public static final int FETCH_FORWARD; 该常数的作用是指定处理记录集中行的顺序，是由前到后即从第一行开始处理一直到最后一行；
//    public static final int FETCH_REVERSE; 该常数的作用是指定处理记录集中行的顺序，是由后到前即从最后一行开始处理一直到第一行；
//    public static final int FETCH_UNKNOWN; 该常数的作用是不指定处理记录集中行的顺序，由JDBC 驱动程序和数据库系统决定；
//    public static final int TYPE_FORWARD_ONLY; 该常数的作用是指定数据库游标的移动方向是向前，不允许向后移动即只能使用ResultSet 接口的next()方法而不能使用previous()方法否则会产生错误；
//    public static final int TYPE_SCROLL_INSENSITIVE; 该常数的作用是指定数据库游标可以在记录集中前后移动，并且当前数据库用户获取的记录集对其他用户的操作不敏感；就是说，当前用户正在浏览记录集中的数据，与此同时，其他用户更新了数据库中的数据，但是当前用户所获取的记录集中的数据不会受到任何影响。
//    public static final int TYPE_SCROLL_SENSITIVE; 该常数的作用是指定数据库游标可以在记录集中前后移动，并且当前数据库用户获取的记录集对其他用户的操作敏感，就是说，当前用户正在浏览记录集，但是其它用户的操作使数据库中的数据发生了变化，当前用户所获取的记录集中的数据也会同步发生变化，这样有可能会导致非常严重的错误产生建议慎重使用该常数。
//    public static final int CONCUR_READ_ONLY; 该常数的作用是指定当前记录集的协作方式(concurrencymode)，为只读；一旦使用了这个常数，那么用户就不可以更新记录集中的数据。
//    public static final int CONCUR_UPDATABLE; 该常数的作用是指定当前记录集的协作方式(concurrencymode)，为可以更新；一旦使用了这个常数，那么用户就可以使用updateXXX()等方法更新记。
//
//            2 ResultSet 接口提供了一整套的定位方法
//    这些可以在记录集中定位到任意一行：
//    public boolean absolute(int row); 该方法的作用是将记录集中的某一行设定为当前行，亦即将数据库游标移动到指定的行，参数row 指定了目标行的行号，这是绝对的行号，由记录集的第一行开始计算不是相对的行号。
//    public boolean relative(int rows); 该方法的作用也是将记录集中的某一行设定为当前行，但是它的参数rows 表示目标行相对于当前行的行号。
//    public boolean first(); 该方法的作用是将当前行定位到数据库记录集的第一行。
//    public boolean last(); 该方法的作用刚好和first()方法相反。
//    public boolean isFirst();
//    public boolean isFirst(); 该方法的作用是检查当前行是否记录集的第一行，如果是返回true， 否则返回false。
//    public boolean isLast(); 该方法的作用是检查当前行是否记录集的最后一行，如果是返回true ，否则返回false。
//    public void afterLast(); 该方法的作用是将数据库游标移到记录集的最后，位于记录集最后一行的后面，如果该记录集不包含任何的行该方法不产生作用。
//    public void beforeFirst(); 该方法的作用是将数据库游标移到记录集的最前面，位于记录集第一行的前面，如果记录集不包含任何的行该方法不产生作用。
//    public boolean isAfterLast(); 该方法检查数据库游标是否处于记录集的最后面，如果是返回true ，否则返回false。
//    public boolean isBeforeFirst(); 该方法检查数据库游标是否处于记录集的最前面，如果是返回true ，否则返回false。
//    public boolean next(); 该方法的作用是将数据库游标向前移动一位，使得下一行成为当前行，当刚刚打开记录集对象时，数据库游标的位置在记录集的最前面，第一次使用next()方法将会使数据库游标定位到记录集的第一行，第二次使用next()方法将会使数据库游标定位到记录集的第二行，以此类推。
//    public boolean previous(); 该方法的作用是将数据库游标向后移动一位，使得上一行成为当前行。
//
//            3 ResultSet 接口添加了对行操作的支持（最令人心动之处）
//
//    修改了的记录集接口(ResultSet 接口)的方法，使它支持可以滚动的记录集，即数据库游标可以在返回的记录集对象中自由地向前或向后滚动，或者定位到某个特殊的行。利用ResultSet 接口中定义的新方法，JSP/Servlet 程序员可以用Java 语言来更新记录集，比如插入记录，更新某行的数据，而不是靠执行SQL 语句，这样就大大方便了程序员的开发工作，享受Java 编程的乐趣了。
//    ResultSet 接口中新添加的部分方法如下所示：
//    public boolean rowDeleted(); 如果当前记录集的某行被删除了，那么记录集中将会留出一个空位；调用rowDeleted()方法，如果探测到空位的存在，那么就返回true； 如果没有探测到空位的存在，就返回false 值。
//    public boolean rowInserted(); 如果当前记录集中插入了一个新行，该方法将返回true ，否则返回false。
//    public boolean rowUpdated(); 如果当前记录集的当前行的数据被更新，该方法返回true ，否则返回false。
//    public void insertRow(); 该方法将执行插入一个新行到当前记录集的操作。
//    public void updateRow(); 该方法将更新当前记录集当前行的数据。
//    public void deleteRow(); 该方法将删除当前记录集的当前行。
//    public void updateString(int columnIndex String x); 该方法更新当前记录集当前行某列的值，该列的数据类型是String(指Java 数据类型是String ，与之对应的JDBC 数据类型是VARCHAR 或NVARCHAR 等数据类型) 。该方法的参数columnIndex 指定所要更新的列的列索引，第一列的列索引是1 ，以此类推，第二个参数x 代表新的值，这个方法并不执行数据库操作，需要执行insertRow()方法或者updateRow()方法以后，记录集和数据库中的数据才能够真正更新。
//    public void updateString(String columnName String x); 该方法和上面介绍的同名方法差不多，不过该方法的第一个参数是columnName ，代表需要更新的列的列名，而不是columnIndex。
//
//    往数据库当前记录集插入新行的操作流程如下：
//            1 调用moveToInsertRow()方法；
//            2 调用updateXXX()方法指定插入行各列的值；
//            3 调用insertRow()方法往数据库中插入新的行。
//
//    更新数据库中某个记录的值(某行的值)的方法是：
//            1 定位到需要修改的行(使用absolute() relative()等方法定位)；
//            2 使用相应updateXXX()方法设定某行某列的新值；XXX 所代表的Java 数据类型，必须可以映射为某列的JDBC 数据类型，如果希望rollback 该项操作，请在调用updateRow()方法以前，使用cancelRowUpdates()方法，这个方法可以将某行某列的值复原；
//            3 使用updateRow()方法完成UPDATE 的操作。
//
//    删除记录集中某行(亦即删除某个记录)的方法：
//            1 定位到需要修改的行(使用absolute() relative()等方法定位)；
//            2 使用deleteRow()
//    删除记录集中某行(亦即删除某个记录)的方法：
//            1 定位到需要修改的行(使用absolute() relative()等方法定位)；
//            2 使用deleteRow()方法。
//
//    JDBC API 3.0 中还在ResultSet 接口中添加了updateArray() updateBlob() updateClob() updateRef()等方法
//
//
//
//1、java数据库操作基本流程
//
//　　2、几个常用的重要技巧：
//
//            　　可滚动、更新的记录集
//
//　　批量更新
//
//　　事务处理
//
//　　java数据库操作基本流程：取得数据库连接 - 执行sql语句 - 处理执行结果 - 释放数据库连接
//
//　　1、取得数据库连接
//
//　　1）用DriverManager取数据库连接
//
//　　例子：
//
//    String className,url,uid,pwd;
//    className = "oracle.jdbc.driver.OracleDriver";
////MySQL：className = "com.mysql.jdbc.Driver";
////SQLServer：className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    url = "jdbc:oracle:thin:@127.0.0.1:1521:orasvr";
////MySQL：url = "jdbc:mysql://127.0.0.1:3306/数据库名";
////SQLServer：url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=数据库名";
//    uid = "system";
//    pwd = "manager";
//Class.forName(className);
//    Connection cn = DriverManager.getConnection(url,uid,pwd);
//
//　　2）用jndi(java的命名和目录服务)方式
//
//　　例子
//
//    String jndi = "jdbc/db";
//    Context ctx = (Context) new InitialContext().lookup("java:comp/env");
//    DataSource ds = (DataSource) ctx.lookup(jndi);
//    Connection cn = ds.getConnection();
//
//　　多用于jsp中
//
//　　2、执行sql语句
//
//　　1）用Statement来执行sql语句
//
//    String sql;
//    Statement sm = cn.createStatement();
//sm.executeQuery(sql); // 执行数据查询语句（select）
//sm.executeUpdate(sql); // 执行数据更新语句（delete、update、insert、drop等）statement.close();
//
//　　2）用PreparedStatement来执行sql语句
//
//    String sql;
//    sql = "insert into user (id,name) values (?,?)";
//    PreparedStatement ps = cn.prepareStatement(sql);
//ps.setInt(1,xxx);
//ps.setString(2,xxx);
//...
//    ResultSet rs = ps.executeQuery(); // 查询
//    int c = ps.executeUpdate(); // 更新
//
//　　3、处理执行结果
//
//　　查询语句，返回记录集ResultSet。
//
//            　　更新语句，返回数字，表示该更新影响的记录数。
//
//            　　ResultSet的方法：
//
//            　　1、next()，将游标往后移动一行，如果成功返回true；否则返回false。
//
//            　　2、getInt("id")或getSting("name")，返回当前游标下某个字段的值。
//
//            　　3、释放连接。
//
//            cn.close();
//
//　　一般，先关闭ResultSet，然后关闭Statement（或者PreparedStatement）；最后关闭Connection
//
//　　可滚动、更新的记录集
//
//　　1、创建可滚动、更新的Statement
//
//    Statement sm = cn.createStatement(ResultSet.TYPE_SCROLL_ENSITIVE,ResultSet.CONCUR_READ_ONLY);
//
//　　该Statement取得的ResultSet就是可滚动的
//
//　　2、创建PreparedStatement时指定参数
//
//    PreparedStatemet ps = cn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//
//ResultSet.absolute(9000);
//
//　　批量更新
//
//　　1、Statement
//
//    Statement sm = cn.createStatement();
//sm.addBatch(sql1);
//sm.addBatch(sql2);
//...
//        sm.executeBatch()
//
//        　　一个Statement对象，可以执行多个sql语句以后，批量更新。这多个语句可以是delete、update、insert等或兼有
//
//　　2、PreparedStatement
//
//    PreparedStatement ps = cn.preparedStatement(sql);
//    {
//　ps.setXXX(1,xxx);
//　...
//　ps.addBatch();
//    }
//ps.executeBatch();
//
//　　一个PreparedStatement，可以把一个sql语句，变换参数多次执行，一次更新。
//
//            　　事务的处理
//
//　　1、关闭Connection的自动提交
//
//cn.setAutoCommit(false);
//
//　　2、执行一系列sql语句
//
//　　要点：执行每一个新的sql语句前，上一次执行sql语句的Statement（或者PreparedStatemet）必须先close
//
//    Statement sm ;
//    sm = cn.createStatement("insert into user...");
//sm.executeUpdate();
//sm.close();
//
//    sm = cn.createStatement("insert into corp...");
//sm.executeUpdate();
//sm.close();
//
//　　3、提交
//
//cn.commit();
//
//　　4、如果发生异常，那么回滚
//
//cn.rollback();
//}
