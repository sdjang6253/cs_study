package enumeration.ex3;

public class EnumRefMain {

    public static void main(String[] args) {
        System.out.println("BASIC = " + Grade.BASIC.getClass());
        System.out.println("GOLD = " + Grade.GOLD.getClass());
        System.out.println("DIAMOND = " +  Grade.DIAMOND.getClass() );

        System.out.println("ref value = " + refValue(Grade.BASIC));
        System.out.println("ref value = " + refValue(Grade.GOLD));
        System.out.println("ref value = " + refValue(Grade.DIAMOND));

    }

    public static String refValue (Object grade){
        return Integer.toHexString(System.identityHashCode(grade));
    }
}
