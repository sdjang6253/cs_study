package lang.system;

import java.util.Arrays;
import java.util.Random;

public class SystemMain {
    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis );

        long currentTimeNano = System.nanoTime();
        System.out.println(currentTimeNano);

        System.out.println(System.getenv());

        System.out.println(System.getProperties());

        System.out.println(System.getProperty("java.version"));

        char[] oriArray = {'h','e','l','l','o'};
        char[] copyArray = new char[5];
        System.arraycopy(oriArray , 0 , copyArray , 0 , oriArray.length);

        System.out.println("copiedAray = " + copyArray);
        System.out.println(Arrays.toString(copyArray));

        Random random = new Random();
    }
}
