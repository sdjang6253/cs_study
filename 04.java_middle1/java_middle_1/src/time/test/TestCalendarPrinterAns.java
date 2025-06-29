package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class TestCalendarPrinterAns {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("년을 입력하세요 : ");
        int year = sc.nextInt();

        System.out.println("월을 입력하세요 : ");
        int month = sc.nextInt();

        printCalendar(year,month);
    }

    private static void printCalendar(int year, int month) {

        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate localDateNextMonth = localDate.plusMonths(1);
        int offsetWeekDays = localDate.getDayOfWeek().getValue() % 7;

        System.out.println("Su Mo Tu We Th Fr Sa");

        for(int i = 0 ; i < offsetWeekDays ; i++) {
            System.out.print("   ");
        }

        LocalDate validDate = localDate;
        while (validDate.isBefore(localDateNextMonth)){
            System.out.printf("%2d " , validDate.getDayOfMonth());
            if(validDate.getDayOfWeek() == DayOfWeek.SATURDAY){
                System.out.println();
            }
            validDate = validDate.plusDays(1);
        }

    }
}
