package org.example.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeService {
    public String getDateTimeString(String timezone) {
        String result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (timezone == null) {
            LocalDateTime dateTime= LocalDateTime.now();
            result = dateTime.format(formatter) + " UTC";
        }else {
            timezone = timezone.replace(" ","+");
            LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(timezone));
            result = dateTime.format(formatter) + " " + timezone;
        }
        return result;
    }
}
