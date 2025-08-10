package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapMain2 {
    public static void main(String[] args) {
        Map<String , Integer> map = new HashMap<>();
        map.put("studentA" , 90);
        System.out.println("map = " + map);

        map.put("studentA" , 100);
        System.out.println("map = " + map);

        boolean containsKey = map.containsKey("studentA");
        System.out.println("containsKey = " + containsKey);

        map.remove("studentA");
        System.out.println("map = " + map);
    }
}
