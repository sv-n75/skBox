package task_451;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zarplata {


    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";// условие - посчитат заработок

        int summa = 0;
        Pattern pattern = Pattern.compile("\\b[\\d]+\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            summa += Integer.parseInt(matcher.group());
        }
        System.out.println("сумма заработка " + summa);

    }
}
