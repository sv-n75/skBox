package task_46;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Calen {

    public static void main(String[] args) {

        int day = 1;
        int month = 10;
        int year = 1975;

        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = new GregorianCalendar(year, month - 1, day);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyy-E ", Locale.ENGLISH);
        int age = calendar.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);

        for (int i = 0; i <= age; i++) {
            System.out.println(i + "-" + df.format(calendar1.getTime()));
            calendar1.set(Calendar.YEAR, calendar1.get(Calendar.YEAR) + 1);
        }
    }
}
