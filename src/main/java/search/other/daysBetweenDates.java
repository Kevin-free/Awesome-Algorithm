package search.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: Kevin
 * @createDate: 2020/2/23
 * @version: 1.0
 */
public class daysBetweenDates {

    public static void main(String[] args) {
        daysBetweenDates solution = new daysBetweenDates();
        String date1 = "2020-01-15", date2 = "2019-12-31";
        int days = solution.daysBetweenDates(date1, date2);
        System.out.println(days);
    }

    public int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int a = (int) ((d2.getTime() - d1.getTime()) / (1000*3600*24));
        return Math.abs(a);
    }
}
