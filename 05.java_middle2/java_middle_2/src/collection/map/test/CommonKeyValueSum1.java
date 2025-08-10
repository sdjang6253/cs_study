package collection.map.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommonKeyValueSum1 {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        map1.put("C", 3);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 4);
        map2.put("C", 5);
        map2.put("D", 6);

        Map<String , Integer> map3 = new HashMap<>();
        // 코드 작성
        for(Map.Entry<String, Integer> entry : map1.entrySet()){
            if(map2.containsKey(entry.getKey())){
                map3.put(entry.getKey(), map2.get(entry.getKey())+entry.getValue());
            }
        }

        System.out.println(map3);
    }

}
