package com.zkx.entity;

/**
 * @author zkx
 */
public class User {

    private String userName;

    public User(String userName, String userId, String password, double money) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                '}';
    }

    private String userId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public User() {
    }

    private double money;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }





}
