package by.parakhnevich.keddit.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DataCreator class return a String value of current Date
 */
public class DateCreator {
    /**
     * Create string.
     *
     * @return the string
     */
    public String create() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
