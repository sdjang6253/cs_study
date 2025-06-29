package time;

import java.time.LocalDate;

public class LocalDateMain {

    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();
        LocalDate ofDate = LocalDate.of(2011,11,21);

        System.out.println("오늘 날짜 = "  + nowDate);
        System.out.println("지정 날짜 = "  + ofDate);

        ofDate = ofDate.plusDays(20);
        System.out.println("계산 시간 = " + ofDate);
    }
}
