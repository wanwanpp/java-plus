package reflect.之前的代码.反射的扩展性;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by ��Ƽ on 2017/2/5 0005.
 */
public class MainTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Person person = new Person();
        person.produce();

        File properties = new File("F:\\IDEA\\reflect\\src\\com\\wp\\反射的扩展性\\special.properties");
        Properties prop = new Properties();
        FileInputStream inputStream= new FileInputStream(properties);

//        prop.load(inputStream);

        /**
         * 需要使用reader接口，可以指定编码方式。注意调整编码
         */
        prop.load(new InputStreamReader(inputStream,"utf-8"));

        for (int i=0;i<prop.size();i++){
            String className = prop.getProperty("special" + (i + 1));
            Class<?> clazz = Class.forName(className);
            Special o = (Special) clazz.newInstance();
            person.can(o);
        }

        inputStream.close();
    }
}
