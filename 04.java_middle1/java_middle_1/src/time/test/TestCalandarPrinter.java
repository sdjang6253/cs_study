package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestCalandarPrinter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] weeks = new String[]{"SUNDAY" , "MONDAY" , "TUESDAY" , "WEDNESDAY" , "THURSDAY" , "FRIDAY" , "SATURDAY" };
        List<String> weeksList = Arrays.asList(weeks);
        System.out.println("년도를 입력하세요 : ");
        int year = scanner.nextInt();
        System.out.println("월을 입력하세요 : ");
        int month = scanner.nextInt();

        LocalDate localDate = LocalDate.of(year, month, 1);
        DayOfWeek firstDayofWeek = localDate.getDayOfWeek();
        LocalDate lastDate = localDate.with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("Su Mo Tu We Th Fr Sa");
        for (int i = 0 ; i < weeksList.indexOf(firstDayofWeek.toString()) ; i++) {
            System.out.print("   ");
        }
        for(int i = 0 ; i < lastDate.get(ChronoField.DAY_OF_MONTH) ; i++ ){
            System.out.printf("%2d ", i+1);
            if(weeksList.indexOf(localDate.plus(i , ChronoUnit.DAYS).getDayOfWeek().toString()) == 6){
                System.out.println();
            }
        }


    }
}
