package time.test;

import java.time.LocalDateTime;

public class TestPlus {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        System.out.println("기준시각 = " + localDateTime);
        LocalDateTime plusDateTime = localDateTime.plusYears(1).plusMonths(2).plusDays(3).plusHours(4);
        System.out.println("후의 시각 = " + plusDateTime);
    }
}
