package task_443;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";// условие - посчитат заработок
        List<Integer> list = new ArrayList<>();
        int numberRub = 0;
        int summa = 0;
        while (numberRub != -1) {
            numberRub = text.indexOf(" руб", numberRub + 1);
            if (numberRub != -1) {
                int numberRubStart = text.lastIndexOf(" ", numberRub - 1);
                int value = Integer.parseInt(text.substring(numberRubStart, numberRub).trim());
                System.out.println(value);
                list.add(value);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            summa += list.get(i);
        }
        System.out.println(summa);
    }
}