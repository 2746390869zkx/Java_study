package com.zkx.entity;

/**
 * @author zkx
 */
public class Result {

    private Integer code;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }

    /**
     * 成功响应
     * @param message
     * @param user
     * @return
     */
    public static Result success(String message,User user) {

        Result result = new Result();
        result.setCode(200);
        result.setMessage(message);
        result.setUser(user);

        return result;
    }
    /**
     * 成功响应
     * @param message
     * @return
     */
    public static Result success(String message) {

        Result result = new Result();
        result.setCode(200);
        result.setMessage(message);

        return result;
    }

    /**
     * 404表示没有用户存在
     * @param message
     * @return
     */
    public static Result fail(String message) {

        Result result = new Result();
        result.setCode(404);
        result.setMessage(message);

        return result;
    }

    public Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private String message;

    private User user;

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }


}
