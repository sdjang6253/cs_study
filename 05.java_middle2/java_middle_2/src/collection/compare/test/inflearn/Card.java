package collection.compare.test.inflearn;

public class Card implements Comparable<Card>{
    private Suit shape;
    private int number;

    public Card(Suit shape, int number) {
        this.shape = shape;
        this.number = number;
    }

    @Override
    public int compareTo(Card o) {
        if(this.number != o.number){
            return Integer.compare(this.number, o.number);
        } else{
            return this.shape.compareTo(o.shape); // enum 이 이미 구현해 놓음
        }
    }

    public Suit getShape() {
        return shape;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + "(" + shape.getIcon() + ")";
    }
}
