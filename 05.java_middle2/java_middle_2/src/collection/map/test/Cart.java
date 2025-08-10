package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product , Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void add (Product product, int count) {
        if(products.containsKey(product)){
            products.put(product, products.get(product) + count);
        } else {
            products.put(product, count);
        }
    }

    public void minus (Product product, int count) {
        int calcCount  = products.getOrDefault(product , 0) - count;
        if(calcCount <= 0){
            products.remove(product);
        }else {
            products.put(product, calcCount);
        }
    }

    public void printAll(){
        System.out.println("==모든 상품 출력==");
        for (Product product : products.keySet()) {
            System.out.println("상품 : " + product + " 수량 : " + products.get(product));
        }
    }




}
