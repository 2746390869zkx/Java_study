package com.zkx.controller;

import com.zkx.entity.Result;
import com.zkx.entity.User;
import com.zkx.utils.JdbcDataUtils;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author zkx
 */
public class Register {



    /**
     * 注册逻辑
     * @return
     */
    public static Result register() {
        boolean flag = true;
        String userId = null;
        while (flag) {
            System.out.println("请输入用户id,6位数字");
            Scanner scanner = new Scanner(System.in);
            //消除空格问题
            userId = scanner.next().trim();
            //正则匹配，不以0开头的6位数字
            String regId = "^[1-9][0-9]{5}$";
            if (!Pattern.matches(regId, userId)) {
                System.out.println("输入的账号不合要求");
                continue;
            }

            //TODO 连接数据库，查询是否存在

            //不存在，就进行退出
            boolean queryRes = JdbcDataUtils.query(userId);
            if (!queryRes) {
                break;
            }
            System.out.println("账号已存在，请重新输入");
        }
        //输入密码，和用户名
        System.out.println("请输入密码,回车键结束输入，中间不要输入空格");
        //密码确保在6-16位的英文加数字
        Scanner scanner = new Scanner(System.in);
        String password = scanner.next().trim();
        //输入密码，和用户名
        System.out.println("请输入用户名,回车键结束输入，中间不要输入空格:");
        //密码确保在6-16位的英文加数字
        String userName = scanner.next().trim();


        User user = new User();
        user.setUserName(userName);
        user.setUserId(userId);

        //TODO 写入数据库
        JdbcDataUtils.register(userId,userName,password);

        return Result.success("成功注册",user);
    }

}
