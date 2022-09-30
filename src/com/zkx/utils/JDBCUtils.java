package com.zkx.utils;


import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc数据类
 * @author zkx
 */
public class JDBCUtils {
    private static String userName;
    private static String url;
    private static String password;
    private static String driver;

    static {
        File file = new File("src/resources/jdbc.properties");
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        userName = prop.getProperty("userName");
        password = prop.getProperty("password");
        url = prop.getProperty("url");
        driver = prop.getProperty("driver");
    }

    //建立连接
    public static Connection getConnection() {
        Connection connection = null;


        try {
            //驱动
            Class.forName(driver);
            //建立连接
            connection = DriverManager.getConnection(url,userName,password);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return connection;
    }

    /*关闭结果集、数据库操作对象、数据库连接*/
    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
