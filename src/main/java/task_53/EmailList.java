package task_53;

import javax.security.sasl.SaslClient;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {


//    Научиться работать со множеством TreeSet.
//    Что нужно сделать
//1.	Напишите программу, в которой будет храниться перечень адресов электронной почты. Адреса можно
// добавлять через консоль командой ADD и печатать весь список командой LIST.
//2.	Программа должна проверять корректность вводимых email’ов и печатать сообщение об ошибке
// при необходимости.
//    Принцип работы команд
//•	LIST — выводит список электронных адресов.
//            •	ADD — проверяет и, если формат адреса верный, добавляет в множество.
//•
//    Примеры команд
//•	LIST
//•	ADD hello@skillbox.ru
//    Команды вводятся одной строкой пользователем в консоль.

    public static void main(String[] args) {
        Set<String> eMail = new TreeSet<>();
        eMail.add("a@mail.ru");
        eMail.add("c@rrt.ru");
        eMail.add("dfg@dssdfdsf.com");

        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        String emailADD = scanner.nextLine();

        while (!emailADD.equals("END")) {
            String[] mail = emailADD.split(" ");
            if (mail[0].equals("LIST")) {
                for (String s : eMail) {
                    System.out.println(s);
                }
            }
            if (mail[0].equals("ADD")) {
                if (isValidEmail(mail[1])) {
                    eMail.add(mail[1]);
                } else
                    System.out.println("Неверный формат, введите еще раз");
            }
            emailADD = scanner.nextLine();
        }
        
    }

    public static boolean isValidEmail(String email) {
        if (email != null) {
            Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
            Matcher matcher = pattern.matcher(email);
            return matcher.find();
        }
        return false;
    }


}
