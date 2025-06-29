package nested.local;

import java.lang.reflect.Field;

public class LocalOuterV3 {
    private int outInstanceVar = 3;

    public Printer process(int paramVar){

        int localVar = 1; //지역변수는 스택 프레임이 종료될때 제거

        class LocalPrinter implements Printer {
            int value = 0;

            @Override
            public void print(){
                System.out.println("value = " + value);

                //인스턴스는 지역변수보다 오래 살아남는다.
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstanceVar = " + outInstanceVar);

            }
        }

        LocalPrinter printer = new LocalPrinter();
        //printer.print(); //를 여기서 실행하지 않고 인스턴스 반환
        return printer;
    }

    public static void main(String[] args) {
        LocalOuterV3 outer = new LocalOuterV3();
        Printer printer = outer.process(10);
        //process() 의 스택 프레임이 종료된 다음 수행
        printer.print();

        System.out.println("필드 확인");

        Field[] declaredFields = printer.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("field = " + declaredField);
        }

    }
}
