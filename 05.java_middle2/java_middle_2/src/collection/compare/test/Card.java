package collection.compare.test;

import java.util.*;

public class Card implements Comparable<Card>{
    private String shape;
    private int number;

    public Card(String shape, int number) {
        this.shape = shape;
        this.number = number;
    }

    @Override
    public int compareTo(Card o) {
        String[] shapes = { "♠" , "♥", "◆" , "♣" };
        List<String> shapesList = List.of(shapes);
        if(this.number < o.number){
            return -1;
        }else if (this.number > o.number){
            return 1;
        } else {
            int thisShapeIndex = shapesList.indexOf(this.shape);
            int targetShapeIndex = shapesList.indexOf(o.shape);
            return (thisShapeIndex < targetShapeIndex) ?  -1 : (thisShapeIndex > targetShapeIndex) ? 1 : 0;
        }

    }

    public String getShape() {
        return shape;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + "(" + shape + ")";
    }
}
