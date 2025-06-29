package nested.nested;

public class NestedOuter {
    private static int outClassValue =3 ;
    private int outInstanceValue = 2;

    static class Nested{
        private int nestedInstanceValue = 1;
        public void print(){
            System.out.println(nestedInstanceValue);

            //System.out.println(outInstanceValue);

            //완전 밖에서 접근할수 있겠지만 여기는 private 이지만 접근이 가능
            System.out.println(outClassValue);
        }
    }
}
