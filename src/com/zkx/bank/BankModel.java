package com.zkx.bank;

import com.zkx.controller.Login;
import com.zkx.controller.MoneyController;
import com.zkx.controller.Register;
import com.zkx.entity.Result;
import com.zkx.entity.User;

import java.util.Scanner;

/**
 * 页面显示
 * @author zkx
 */
public class BankModel {

    /**
     * 启动界面
     * @param args
     */
    public static void main(String[] args) {
        BankModel bankModel = new BankModel();
        bankModel.bank();
    }

    public void bank() {
        boolean flag = true;
        User user = new User();
        System.out.println("-------------银行系统页面--------------");
        System.out.println("——————————————1.登入—————————————————");
        System.out.println("——————————————2.注册—————————————————");
        System.out.println("——————————————0.退出—————————————————");
        while(flag) {
            System.out.println("请输入你所选择服务的序号");
            Scanner scanner = new Scanner(System.in);
            int checkNum = scanner.nextInt();
            switch (checkNum) {

                case 1:
                    //TODO登入校验
                    Result result = Login.loginCheck();
                    System.out.println(result.getMessage());
                    user = result.getUser();
                    if (user!=null)
                        flag = false;
                    break;
                case 2:
                    //TODO注册校验
                    result = Register.register();
                    System.out.println(result.getMessage());
                    user = result.getUser();
                    flag = false;
                    break;
                case 0:
                    //退出
                    System.out.println("退出成功");
                    System.exit(0);
                    break;
                default:
                    System.out.println("没有该服务，请重新选择");

            }
        }
        //登入或者注册成功
        service(user);

    }

    public void service(User user) {
        while(true) {
            System.out.println("————————————欢迎"+ user.getUserName() +"用户--------------");
            System.out.println("——————————————1.存钱—————————————————");
            System.out.println("——————————————2.取钱—————————————————");
            System.out.println("——————————————3.查询余额—————————————————");
            System.out.println("——————————————4.转账—————————————————");
            System.out.println("——————————————0.退出系统—————————————————");
            System.out.println("请选择你所选择的序号:");
            Scanner scanner = new Scanner(System.in);
            int checkNum = scanner.nextInt();
            switch (checkNum) {
                case 1:
                    Result save = MoneyController.save(user);
                    user = save.getUser();
                    System.out.println(save.getMessage());
                    break;
                case 2:
                    if (user.getMoney() == 0) {
                        System.out.println("余额为0，不能取款");
                        break;
                    }
                    Result withdraw = MoneyController.withdraw(user);
                    user = withdraw.getUser();
                    System.out.println(withdraw.getMessage());
                    break;
                case 3:
                    double money = user.getMoney();
                    System.out.println("你的余额：" + money + "元");
                    break;
                case 4:
                    Result tranfer = MoneyController.transfer(user);
                    if (tranfer.getUser() != null) {
                        user = tranfer.getUser();
                    }
                    System.out.println(tranfer.getMessage());
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("没有该服务请重新选择正确的序号");
            }
        }
    }

}
