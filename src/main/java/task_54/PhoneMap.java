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

//    они это подразумевали во фразе "Определяйте имя и телефон с помощью регулярных
//    выражений."
//    Хотя бы ограничь 11ю числами регулярку)
//    в entry находятся записи "имя - телефон", проверкой ты сравниваешь равно ли имя 
//    введенному номеру? Это либо заведомо странно, т.к. всегда будет false, либо 
//    допускается что с качестве имени будет номер, и это тоже странно
//    Вся эта проверка под вот это условие?
//    Если пишем новое имя, программа просит ввести номер телефона и запоминает его.
//    Если новый номер телефона — просит ввести имя и также запоминает.
//    Как определяется номер или имя новые или нет? Надо по существующему списку пройти 
//    и это понять, а потом запрашивать дополнительные данные, если в списке не найдено.
//    Несколько раз встречается
//System.out.println("Введите команду");
//    enter = scanner.nextLine();
//    Достаточно это вынести в начало цикла, один раз. Если отработал какой-то блок 
//    и надо заново запросить команду, то начиная новую итерацию цикла будет запрошен 
//     новый ввод команды.


    public static void main(String[] args) {
        Map<String, Integer> phoneBook = new TreeMap<>();
        phoneBook.put("a", 132);
        phoneBook.put("b", 145);
        phoneBook.put("g", 6);
        phoneBook.put("c", 687);

        String enter = " ";
        Scanner scanner = new Scanner(System.in);

        while (!enter.equals("END")) {
            System.out.println("Введите команду");
            enter = scanner.nextLine();
            try {
                checkEnter(enter, phoneBook);
            } catch (IllegalCommandException e) {
            }
        }
    }


    public static void checkEnter(String string, Map<String, Integer> pB) {

        Scanner scanner = new Scanner(System.in);
        if (string.equals("LIST")) {
            pB.forEach((key, value) -> {
                System.out.println(key + " -- " + value);
            });
            return;
        }

        if (string.matches("^[a-zA-Z]+$")) {//начало и конец строки буквы
            if (!checkName(string, pB)) {
                System.out.println("введите номер");
                if (scanner.nextLine().matches("^[0-9]{1,11}$")) {
                    int number = Integer.parseInt(scanner.nextLine());
                    pB.put(string, number);
                } else throw new IllegalCommandException();
            }
            return;
        }


        if (string.matches("^[0-9]{1,11}$")) {// как заказывал 11 ограничение
            int number = Integer.parseInt(string);
            if (!checkNumber(number, pB)) {
                System.out.println("введите имя");
                String name = scanner.nextLine();
                if (name.matches("^[a-zA-Z]+$")) {
                    pB.put(name, number);
                    return;
                }
            }

        }
        throw new IllegalCommandException();
    }


    public static boolean checkName(String stringName, Map<String, Integer> pB) {
        for (Map.Entry<String, Integer> entry : pB.entrySet()) {
            if (entry.getKey().equals(stringName)) {
                System.out.println(entry.getKey() + " -- " + entry.getValue());
                return true;
            }
        }
        return false;
    }


    public static boolean checkNumber(int stringNumber, Map<String, Integer> pB) {

        for (Map.Entry<String, Integer> entry : pB.entrySet()) {
            if (entry.getValue().equals(stringNumber)) {
                System.out.println(entry.getKey() + " -- " + entry.getValue());
                return true;
            }
        }
        return false;
    }
}

class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {
        System.out.println("Некорректные данные");
    }
}






