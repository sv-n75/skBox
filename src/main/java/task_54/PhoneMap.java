package task_54;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneMap {

    //        Научиться работать с коллекцией Map.
//                Что нужно сделать
//        1. Напишите программу, которая будет работать как телефонная книга:
//•	Если пишем новое имя, программа просит ввести номер телефона и запоминает его. Если новый номер
// телефона — просит ввести имя и также запоминает.
//•	Если вводим существующее имя или номер телефона, программа выводит всю информацию о контакте.
//•	При вводе команды LIST программа печатает в консоль список всех абонентов в алфавитном порядке с номерами.
//        2. Определяйте имя и телефон с помощью регулярных выражений.
//        3. Подумайте, что выбрать в качестве ключа и значения для Map, выберите
//        лучший вариант по вашему мнению. Опишите, какие минусы и плюсы видите в вашем выборе.

    public static void main(String[] args) {
        Map<String, Integer> phoneBook = new TreeMap<>();
        phoneBook.put("a", 132);
        phoneBook.put("b", 145);
        phoneBook.put("g", 6);
        phoneBook.put("c", 687);

        int number = 0;

        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        String enter = scanner.nextLine();

        while (!enter.equals("END")) {
            if (enter.equals("LIST")) {
                phoneBook.forEach((key, value) -> {
                    System.out.println(key + " -- " + value);
                });
                System.out.println("Введите команду");
                enter = scanner.nextLine();
                continue;
            }

            if (enter.matches("\\d+")) {// если содержит число то номер  - полная корректность ввода не проверяется - в условии этого нет
                number = Integer.parseInt(enter);
                System.out.println(number);
            }

            for (Map.Entry<String, Integer> entry : phoneBook.entrySet()) {
                if (entry.getKey().equals(enter) || entry.getValue().equals(number)) {
                    System.out.println(entry.getKey() + " -- " + entry.getValue());
                    System.out.println("Введите команду");
                    enter = scanner.nextLine();
                    continue;
                }
            }
            if (number == 0) {
                System.out.println("введите номер");
                int nunberNew = Integer.parseInt(scanner.nextLine());
                phoneBook.put(enter, nunberNew);
            } else {
                System.out.println("ведите имя");
                String name = scanner.nextLine();
                phoneBook.put(name, number);
            }
            System.out.println("Введите команду");
            enter = scanner.nextLine();
        }
    }
}






