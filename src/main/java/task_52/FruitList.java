package task_52;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FruitList {
//    Разработайте список дел, который управляется командами в консоли. Команды: LIST, ADD, EDIT, DELETE.
//    Принцип работы команд:
//            •	LIST — выводит дела с их порядковыми номерами;
//•	ADD — добавляет дело в конец списка или дело на определённое место, сдвигая
// остальные дела вперёд, если указать номер;
//•	EDIT — заменяет дело с указанным номером;
//•	DELETE — удаляет.
//•
//    Примеры команд
//•	LIST
//•	ADD Какое-то дело
//•	ADD 4 Какое-то дело на четвёртом месте
//•	EDIT 3 Новое название дела
//•	DELETE 7
//    Команды вводятся одной строкой пользователем в консоль.

//    Доработай логику следующим образом:
//    После ввода проверяешь соответствует ли ввод формату
//•	"текст пробел число пробел текст", при чем последний текст (2й элемент массива,
// если строку split) может содержать и цифры и пробелы сколько угодно раз. Если да, то это
// добавление с указанием места или корректировка
//•	"текст пробел текст", это добавление в список. Условие для второго текста аналогично
// пункту выше (и пробелы и цифры могут быть)
//•	"текст пробел число", и текст равен DELETE это удаление
//•	ввод равен LIST, это показать список
//    Итого:
//            1.	формат ввода проверяй регулярками, если ни одному из условий не
//            соответствуют входные данные, то выбрось эксепшн "Некорректные данные"
//            2.	таким образом сначала ты определяешь что это за данные тебе пришли, их
//            корректность (например AD.D что-то отсеится), и далее выполняешь определенное
//            действие
//3.	если одно и тоже действие требуется более 2х раз - вынеси его в отдельный метод
//    Чтобы код был понятнее пора всё записывать с использованием методов, а то всё сплошняком идет.
//    Проверки выноси в отдельные методы, вызывай их в цикле и если true, то нужные действия.
//            task_52/FruitList.java структурно очень похожая задача, обе исправляй, потом я сделаю рефакторинг (если потребуется)


    public static void main(String[] args) throws IllegalCommandException {//ПЕРЕДЕЛАЛ ДОРАБОТАЛ НО НА МОЙ ВЗГЛЯД КОРЯВЕНЬКО ВЫШЛО))) НИХРЕНА НИ 3 СТРОКИ С МЕТОДАМИ И ПРОЧЕЙ КРАСОТОЙ И ПРЕЛЕСТЬЮ
        List<String> fruit = new ArrayList<>();
        fruit.add("апельсин");
        fruit.add("яблоко");
        fruit.add("груша");
        fruit.add("мандарин");


        String string = " ";
        Scanner scanner = new Scanner(System.in);

        while (!string.equals("end")) {
            System.out.println("Введите команду");
            string = scanner.nextLine();

            try {
                enterValue(string, fruit);
            } catch (IllegalCommandException ignored) {
            }
        }

    }


    public static List enterValue(String s, List f) throws IllegalCommandException {
        String[] strings;
        int index;

        if (s.equals("LIST")) {//на лист сразу
            for (int i = 0; i < f.size(); i++) {
                System.out.println(f.get(i) + " " + (i + 1));
            }
            return f;
        }

        if (s.matches("^[a-zA-z]+\\s\\d+$")) {//текст пробел число
            strings = s.split(" ");
            index = Integer.parseInt(strings[1]);
            if ((strings[0].equals("DELETE") && index < f.size())) {
                {
                    f.remove(index);
                }

            } else
                enterText(s, f);//совпадает по условию текст пробел текст где текст любой набор символов
            return f;
        }

        if (s.matches("^[a-zA-z]+\\s\\d+\\s.+")) {// текст проб число текст ADD я так понял непроверяем
            strings = s.split(" ", 3);
            index = Integer.parseInt(strings[1]);
            if (index < f.size()) {
                f.add(index, strings[2]);
            } else
                enterText(s, f);//текст пробел число текст совпадает по условию с тест пробел текст
            return f;
        }


        if (s.matches("^[a-zA-z]+\\s.+")) {//текст пробел текст
            enterText(s, f);

        } else {

            throw new IllegalCommandException();

        }
        return f;
    }

    public static void enterText(String s, List f) { //текст пробел текст
        String[] stringsText;
        stringsText = s.split(" ", 2);
        f.add(stringsText[1]);
    }
}


class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {
        System.out.println("Некорректные данные");
    }
}







