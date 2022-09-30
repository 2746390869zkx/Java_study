package com.zkx.controller;

import com.zkx.entity.Result;
import com.zkx.entity.User;
import com.zkx.utils.JdbcDataUtils;
import java.util.Scanner;

/**
 * @author zkx
 */
public class Login {

    public static void main(String[] args) {
        Result result = Login.loginCheck();
        System.out.println(result);

    }

    public static Result loginCheck() {

        System.out.println("请输入你的账号:");
        Scanner scanner = new Scanner(System.in);
        String inputUserId = scanner.next().trim();
        System.out.println("请输入你的密码:");
        String inputPassword = scanner.next().trim();

        //查询数据库
        User user1 = JdbcDataUtils.queryAndLogin(inputUserId,inputPassword);
        if (user1 == null) {
            return Result.fail("账号或者密码错误");
        }

        return Result.success("登入成功",user1);
    }
}
