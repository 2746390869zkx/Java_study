package com.zkx.utils;


import com.zkx.entity.User;

import java.sql.*;

/**
 * 增删改查逻辑
 * @author zkx
 */
public class JdbcDataUtils {

    //查询用户是否存在逻辑
    public static boolean query(String userId) {
        String sql = "select * from user where user_id = ?";
        Connection connection = JDBCUtils.getConnection();

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        boolean res = false;

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1,Integer.parseInt(userId));

            resultSet = statement.executeQuery();

            //表示第一条有无数据
            res = resultSet.first();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,statement,resultSet);
        }
        return res;
    }

    //带有返回值的查询逻辑

    //查询用户是否存在逻辑
    public static User queryAndLogin(String userId,String password) {
        User user = new User();
        String md5_password = Md5Utils.md5(password);
        String sql = "select * from user where user_id = ? and password = ?";
        Connection connection = JDBCUtils.getConnection();

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        boolean res = false;

        try {
            statement = connection.prepareStatement(sql);

            statement.setInt(1,Integer.parseInt(userId));

            statement.setString(2,md5_password);

            resultSet = statement.executeQuery();

            if (!resultSet.first()) {
                //没有数据
                return null;
            }

            user.setUserId(resultSet.getInt("user_id")+"");
            user.setPassword(resultSet.getString("password"));
            user.setUserName(resultSet.getString("user_name"));
            user.setMoney(resultSet.getDouble("money"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,statement,resultSet);
        }
        return user;
    }

    //注册逻辑
    public static boolean register(String userId,String userName,String password) {



        String md5_password = Md5Utils.md5(password);


        /*
        按照INSERT INTO `user` VALUES(123456,'zkx',0,MD5(123456));
        * */
        String sql = "insert into user values(?,?,0,?)";
        Connection connection = JDBCUtils.getConnection();

        PreparedStatement statement = null;
        int row = 0;

        try{

            statement = connection.prepareStatement(sql);

            statement.setInt(1,Integer.parseInt(userId));

            statement.setString(2,userName);

            statement.setString(3,md5_password);

            row = statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,statement,null);
        }

        return row == 1;
    }

    //取款逻辑
    public static boolean updateMoney(String userId,double money) {

        String sql = "UPDATE  `user` SET money = ? WHERE user_id = ?";
        Connection connection = JDBCUtils.getConnection();

        PreparedStatement statement = null;
        int row = 0;

        try{

            statement = connection.prepareStatement(sql);

            statement.setDouble(1,money);

            statement.setInt(2,Integer.parseInt(userId));

            row = statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,statement,null);
        }

        return true;
    }

    //转账逻辑
    public static boolean transfer(String from,String to,double money) {

        String sql = "UPDATE  `user` SET money = money + ? WHERE user_id = ?";
        Connection connection = JDBCUtils.getConnection();

        PreparedStatement statement = null;
        int row = 0;
        try{

            //禁止提交
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql);

            statement.setDouble(1,-money);

            statement.setInt(2,Integer.parseInt(from));

            row = statement.executeUpdate();


            statement.setDouble(1,money);

            statement.setInt(2,Integer.parseInt(to));

            row += statement.executeUpdate();

            if (row != 2) {
                return false;
            } else {
                //提交信息
                connection.commit();
            }



        } catch (SQLException e) {
            e.printStackTrace();
            //出现异常，交易失败
            return false;
        } finally {
            JDBCUtils.release(connection,statement,null);
        }


        return true;

    }
}
