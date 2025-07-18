package generic.test.ex3;

import generic.test.ex3.unit.BioUnit;
import generic.test.ex3.unit.Marine;

public class Shuttle <T extends BioUnit> {
    T unit;

    public void in(T t1) {
        unit = t1;
    }

    public void showInfo() {
        System.out.println("이름 :" + unit.getName() + ", HP :  " + unit.getHp());
    }

    public T out(){
        return unit;
    }

}
