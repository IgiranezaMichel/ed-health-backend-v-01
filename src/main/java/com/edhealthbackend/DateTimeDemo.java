package com.edhealthbackend;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
public class DateTimeDemo {
public static void main(String[] args) {
    LocalDateTime today=LocalDateTime.parse("2024-02-17T09:00");
    System.out.println(today);
    String pattern = "#,####";
DecimalFormat decimalFormat = new DecimalFormat(pattern);
String format = decimalFormat.format(123456789.123);
System.out.println(format);
}

}
