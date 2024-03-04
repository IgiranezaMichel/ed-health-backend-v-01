package com.edhealthbackend;

import java.util.Random;

public class DateTimeDemo {
public static void main(String[] args) {
    Random random=new Random();
    long raString=random.nextLong(2000l);

    // LocalDateTime today=LocalDateTime.parse("2024-02-17T09:00");
    System.out.println(raString);
    // String pattern = "#,####";
// DecimalFormat decimalFormat = new DecimalFormat(pattern);
}

}
