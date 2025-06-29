package time.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TestBewteen {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 11, 21);

        System.out.println("시작 날짜: " + startDate);
        System.out.println("목표 날짜: " + endDate);
        System.out.println("남은 기간: " + Period.between(startDate, endDate).getYears() + "년 " +
                Period.between(startDate, endDate).getMonths() + "개월 " +
                Period.between(startDate, endDate).getDays() + "일" );
        System.out.println("디데이: " + ChronoUnit.DAYS.between(startDate, endDate) + "일 남음" );
    }
}
