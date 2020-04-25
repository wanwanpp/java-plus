package com.wp.ArrayList与HashSet比较及Hashcode分析;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by 王萍 on 2017/2/6 0006.
 */
public class Reflect {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        Properties properties = new Properties();
        //使用文件输入流加载
//        InputStream inputStream = new FileInputStream("F:\\IDEA\\j2se深入\\src\\com\\wp\\ArrayList与HashSet比较及Hashcode分析\\config.properties");

        //使用类加载器进行properties文件加载，类加载器用于加载.class文件，更可以加载普通文件
        //该方式是所有文件都从classpath，即根目录进行加载
//        InputStream inputStream = Reflect.class.getClassLoader().getResourceAsStream("com/wp/ArrayList与HashSet比较及Hashcode分析/config.properties");

        //该方式可以从同级目录进行加载
        InputStream inputStream = Reflect.class.getResourceAsStream("config.properties");

        properties.load(inputStream);
        String className = properties.getProperty("className");
        inputStream.close();

        Collection collection = (Collection) Class.forName(className).newInstance();
        Slave slave1 = new Slave(1, 1);
        Slave slave2 = new Slave(5, 5);
        Slave slave3 = new Slave(1, 1);


        collection.add(slave1);
        collection.add(slave2);
        collection.add(slave3);
        collection.add(slave1);

        System.out.println(collection.size());
    }

}
