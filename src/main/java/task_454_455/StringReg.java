package task_454_455;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReg {

    public static void main(String[] args) {

        String number = "8-- 01(234)56rtw89ее6";//нифига не в одну строку НЕ ПОЛУЧИЛОСЬ но мне все равно понравилось
        Pattern pattern = Pattern.compile("\\b([78])?\\d{10}\\b");
        Matcher matcher = pattern.matcher(number.replaceAll("[^0-9]", ""));

        if (matcher.find())
            System.out.println(10 == matcher.group().length() ? 7 + matcher.group() : 7 + matcher.group().substring(1));
        else
            System.out.println("Неверный формат");


//        System.out.println(number);
//        char[] digit = number.toCharArray();
//        int start = Integer.parseInt(number.substring(0, 1));
//        StringBuilder stringBuilder = new StringBuilder(number);
//
//        if (digit.length == 10) {
//            stringBuilder.insert(0, 7);
//        }
//
//        if (digit.length == 11) {
//            if (start != 7 && start != 8) {
//                System.out.println("Неверный формат");
//                return;
//            }
//            if (start == 8) {
//                stringBuilder.replace(0, 1, "7");
//            }
//        }
//        System.out.println(stringBuilder);


        // задание 4,5,5

        String text = "ertyjnbfs   fggth<hhfh   ie>hiuu   ig<gug>ooo";
        String placeholder = "URA";
        System.out.println(searchAndReplaceDiamonds(text, placeholder));


    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        text = text.replaceAll("<.+?>", placeholder);
        return text;
    }
} 
