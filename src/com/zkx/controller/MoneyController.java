package com.zkx.controller;

import com.zkx.entity.Result;
import com.zkx.entity.User;
import com.zkx.utils.JdbcDataUtils;

import java.util.Scanner;

/**
 * @author zkx
 * 金钱管理
 */
public class MoneyController {


    /**
     * 取款功能
     * @param user
     * @return
     */
    public static Result withdraw(User user) {
        double money;
        if (user.getMoney() == 0) {
            return Result.fail("金额为0，不能取款");
        }
        while (true) {
            System.out.println("请输入你的取款金额:");
            Scanner scanner = new Scanner(System.in);
            money = scanner.nextDouble();
            if (money <= 0) {
                System.out.println("你的取款金额不为小于等于0");
            } else if (money > user.getMoney()) {
                System.out.println("余额不足");
            } else {
                break;
            }

        }

        user.setMoney(user.getMoney() - money);

        //更新数据库
        JdbcDataUtils.updateMoney(user.getUserId(), user.getMoney());

        return Result.success("取款成功",user);
    }

    /**
     * 存款功能
     * @param user
     * @return
     */
    public static Result save(User user) {

        double money;
        while (true) {
            System.out.println("请输入你所要的存款金额");
            Scanner scanner = new Scanner(System.in);
            money = scanner.nextDouble();
            if (money <= 0) {
                System.out.println("存款金额不能小于等于0元");
            } else {
                break;
            }
        }

        user.setMoney(user.getMoney() + money);

        //写入文件中
        JdbcDataUtils.updateMoney(user.getUserId(),user.getMoney());

        return Result.success("存款成功",user);
    }

    public static Result transfer(User user) {
        //转账给谁人
        System.out.println("请输入转账人:");
        Scanner scanner = new Scanner(System.in);
        //转账人的id
        String transferId = scanner.next();
        //判断转账人是否存在
        boolean query = JdbcDataUtils.query(transferId);
        if (!query) {
            return Result.fail("转账账号不存在");
        }
        System.out.println("请输入转账数:");
        scanner = new Scanner(System.in);
        double transferMoney = scanner.nextDouble();
        if (transferMoney <= 0) {
            return Result.fail("转账金额不能少用0");
        } else if (transferMoney > user.getMoney()) {
            return Result.fail("你的转账金额超过余额");
        }

        //TODO 尝试转账，使用事务
        boolean transfer = JdbcDataUtils.transfer(user.getUserId(),transferId, transferMoney);

        if (!transfer) {
            return Result.fail("转账失败");
        }



        user.setMoney(user.getMoney() - transferMoney);


        return Result.success("转账成功",user);
    }

}
