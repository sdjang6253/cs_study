package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatterMain2 {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 12, 31, 13, 30, 50);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        System.out.println("formattedDateTime = " + formattedDateTime);
        System.out.println("localDateTime = " + localDateTime);

        String dateTimeString = "2030-01-01 12:30:00";
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
        System.out.println("parsedStr = " + parsedDateTime);
    }
}
