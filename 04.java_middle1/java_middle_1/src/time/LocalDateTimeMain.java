package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeMain {

    public static void main(String[] args) {
        LocalDateTime nowDt = LocalDateTime.now();
        LocalDateTime ofDt = LocalDateTime.of(2024,10,11,3,58,1);

        System.out.println("nowDt = " + nowDt);
        System.out.println("ofDt = " + ofDt);

        LocalDate localDate = ofDt.toLocalDate();
        LocalTime localTime = ofDt.toLocalTime();

        System.out.println("localDate = " + localDate);
        System.out.println("localTime = " + localTime);

        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime ofDtPlus = ofDt.plusDays(2000);
        System.out.println("날짜 시간 + 2000d =" + ofDtPlus);
        LocalDateTime ofDtPLusYear = ofDt.plusYears(2);
        System.out.println("날짜 시간 + 2y = " +  ofDtPLusYear);

        System.out.println(ofDt.isBefore(ofDtPlus));
        System.out.println(ofDt.isAfter(ofDtPlus));
        System.out.println(ofDt.isEqual(ofDt));


    }
}
