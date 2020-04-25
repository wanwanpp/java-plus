package com.wp.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class App {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Before
    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动");
        }

        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "980325";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        System.out.println("数据库连接成功");
    }

    @Test
    public void statement() {
        try {
            //创建指针可以自由移动结果集ResultSet
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = statement.executeQuery("SELECT * FROM users");
//            statement.executeUpdate(sql)

            //resultSet.getMetaData可以获取结果集的元信息。
            System.out.println("列数为:" + resultSet.getMetaData().getColumnCount());

            //将指针移到最后一行，然后打印最后一行的行号，即得到了行数.
            resultSet.last();
            System.out.println("行数为：" + resultSet.getRow());
            //将结果集的指针移到第一行
            resultSet.beforeFirst();

            //resultSet.first();//此时指针在第一行,遍历时会从第二行开始
            while (resultSet.next()) {
                //列索引从1开始
                System.out.println("id:" + resultSet.getInt(1) + " " +
                        "name:" + resultSet.getString(2) + " " +
                        "sex:" + resultSet.getString(3) + " " +
                        "birthday:" + resultSet.getTimestamp(4));
                if (resultSet.getRow() == 2) {
                    //这个方法并不执行数据库操作，需要执行insertRow()方法或者updateRow()方法以后，记录集和数据库中的数据才能够真正更新
                    resultSet.updateString(2, "wanwanpp");
                    resultSet.updateRow();//这里有数据库更新操作
                    //     System.out.println("ROW is " + resultSet.getRow());//返回当前的行号
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void prepareStatement() throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            System.out.println("无法获取prepareStatement");
            e.printStackTrace();
        }

        preparedStatement.setString(1, "王萍");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("id:" + resultSet.getInt(1) + " " +
                    "name:" + resultSet.getString(2) + " " +
                    "sex:" + resultSet.getString(3) + " " +
                    "birthday:" + resultSet.getTimestamp(4));
            if (resultSet.getRow() == 2) {

                System.out.println("ROW is " + resultSet.getRow());//返回当前的行号
            }
        }


    }

    @After
    public void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("resultSet关闭失败");
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("statement关闭失败");
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("PreparedStatement关闭失败");
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("connection关闭失败");
                e.printStackTrace();
            }
        }
    }
}
