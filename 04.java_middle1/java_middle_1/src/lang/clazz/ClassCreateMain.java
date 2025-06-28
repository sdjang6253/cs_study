package lang.clazz;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Class.forName;

public class ClassCreateMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class helloClass = Class.forName("lang.clazz.Hello");

        Hello hello  = (Hello) helloClass.getDeclaredConstructor().newInstance();
        String str = hello.hello();
        System.out.println(hello );
        System.out.println("result = " + str);
    }
}
