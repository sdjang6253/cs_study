package collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain1 {
    public static void main(String[] args) {
        Map<String, Integer> studentMap = new HashMap<String, Integer>();

        studentMap.put("studentA", 10);
        studentMap.put("studentB", 20);
        studentMap.put("studentC", 30);
        studentMap.put("studentD", 40);
        System.out.println(studentMap);

        Integer result = studentMap.get("studentC");
        System.out.println("result = " + result);

        System.out.println("KeySet 활용");
        Set<String> keys  = studentMap.keySet();
        for(String key : keys){
            Integer value = studentMap.get(key);
            System.out.println("key = " + key + " value = " + value);
        }

        System.out.println("EntrySet 활용");
        Set<Map.Entry<String, Integer>> entries = studentMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries){
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }

        System.out.println("values 활용");
        Collection<Integer> values = studentMap.values();
        for(Integer value : values){
            System.out.println("value = " + value);
        }

    }
}
