package com.zkx.utils;

import com.zkx.entity.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zkx
 */
public class FileInputUtils {

    /**
     * 写入数据的工具类
     */

    public static void saveInfo(User user) {

        File file = new File("src/resources/" + user.getUserId() + ".properties");

        Properties prop = new Properties();


        prop.setProperty("password",user.getPassword());
        prop.setProperty("userName",user.getUserName());
        prop.setProperty("userId",user.getUserId());
        prop.setProperty("money",(user.getMoney()+""));
        //将信息写入文件中
        FileOutputStream fos = null;
        try {
            fos  = new FileOutputStream(file);
            prop.store(fos,"");
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
