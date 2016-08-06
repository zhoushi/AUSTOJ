package cn.edu.aust.util;

import java.io.*;
import java.util.Properties;

/**
 * 读取properties文件的工具类
 */
public class PropertiesUtil {

    private static Properties properties;
    private static String url;
    static {
        url = System.getProperty("web.root") + "WEB-INF"+File.separator+"classes"+File.separator+"config.properties";
        properties = new Properties();
        try {
           InputStream in = new BufferedInputStream(new FileInputStream(url));
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取属性
     * @param key
     * @return
     */
    public static String getProperty(String key){
       return properties.getProperty(key);
    }

    /**
     * 设置属性
     * @param key
     * @param value
     */
    public static void setProperty(String key,String value) throws IOException {
        OutputStream out = new FileOutputStream(url);
        properties.setProperty(key,value);
        properties.store(out,"『comments』Update key：" + key);
    }
}
