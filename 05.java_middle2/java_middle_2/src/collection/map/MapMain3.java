package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapMain3 {
    public static void main(String[] args) {
        Map<String , Integer> map = new HashMap<>();
        map.put("studentA" , 50);
        System.out.println("map = " + map);

        //학생이 없는 경우에만 추가1
        if(!map.containsKey("studentA")){
            map.put("studentA" , 100);
        }
        System.out.println("map = " + map);

        //학생이 없는 경우에만 추가1
        map.putIfAbsent("studentA" , 900);
        map.putIfAbsent("studentB" , 500);
        System.out.println("map = " + map);
    }
}
