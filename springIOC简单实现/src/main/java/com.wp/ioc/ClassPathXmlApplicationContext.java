package com.wp.ioc;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * Created by 王萍 on 2017/2/10 0010.
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private static final Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    public ClassPathXmlApplicationContext(String xmlPath) throws Exception {
        SAXBuilder sb = new SAXBuilder();
        // 构造文档对象

        //同级目录下加载
//        Document doc = sb.build(this.getClass().getResourceAsStream(xmlPath));
        //从classpath下加载   这里maven项目加载的是resources下面的beans.xml
        Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
        // 获取根元素Beans
        Element root = doc.getRootElement();
        // 获取子元素bean
        List<Element> list = root.getChildren("bean");
        // 遍历
        for (int i = 0; i < list.size(); i++) {
            Element element = list.get(i);// 第i个
            // 获取id的值
            String id = element.getAttributeValue("id");
            // 获取class的值
            String className = element.getAttributeValue("class");
            // 利用反射得到一个真实对象
            Object obj = Class.forName(className).newInstance();
            // 存入Map
            beanMap.put(id, obj);
            List<Element> propertyElements = element.getChildren("property");
            for (Element propertyElement : propertyElements) {
                // 得到userDao
                String propertyName = propertyElement.getAttributeValue("name");
                String beanName = propertyElement.getAttributeValue("ref");
                Object refObject = beanMap.get(beanName);
                // 构造一个属性的setter方法
                String methodName = "set"
                        + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                // 获取指定的方法
                //第一个参数为方法名，第二个为userDao的接口的class。
                Method method = obj.getClass().getMethod(methodName,
                        refObject.getClass().getInterfaces()[0]);
                method.invoke(obj, refObject);
            }
        }
    }

    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
