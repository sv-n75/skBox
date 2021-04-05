package task_52;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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


    public static void main(String[] args) {
        List<String> fruit = new ArrayList<>();
        fruit.add("апельсин");
        fruit.add("яблоко");
        fruit.add("груша");
        fruit.add("мандарин");

        String fr = "кокос";

        System.out.println("Введите команду");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        while (!string.equals("END")) {
            String stringEnd = string.replaceAll("[0-9]", "");
            String number = string.replaceAll("[^0-9]", "");
            int index;

            if (!number.isEmpty()) {
                index = Integer.parseInt(number) - 1;
                if (stringEnd.equals("ADD")) {
                    fruit.add(index, fr);
                }
                if (stringEnd.equals("EDIT")) {
                    fruit.set(index, fr);
                }
                if (stringEnd.equals("DELETE")) {
                    fruit.remove(index);
                }
            } else {
                if (string.equals("ADD")) {
                    fruit.add(fr);
                }
                if (string.equals("LIST")) {
                    for (int i = 0; i < fruit.size(); i++) {
                        System.out.println(fruit.get(i) + " " + (i + 1));
                    }

                }

            }

            string = scanner.nextLine();
        }
    }


}
