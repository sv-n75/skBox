package task_412;





import java.util.Scanner;

public class Main

//    . Реализуйте статический метод sumDigits, чтобы он считал сумму цифр в числе и
//    возвращал сумму в результате своей работы. Для реализации метода можете использовать методы:
//            •	класса Integer: toString(), parseInt();
//            •	класса String: charAt(), length(), valueOf().
//            3. Выведите в консоль суммы цифр чисел: 12345, 10, 5059191.
//            4. Отправьте в репозиторий Git коммит с выполненным заданием.

//    Изучить методы класса Character.
//        Что нужно сделать
//        1.	Изучите методы класса Character, найдите метод получения int из char.
//        2.	Напишите метод sumDigits() в котором используйте метод класса Character.
//        3.	Отправьте в репозиторий Git отдельный коммит с выполненным заданием.


{
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;
        for (int i = 0; i < 3; i++) {
            System.out.println("введите число");
            Scanner scanner = new Scanner(System.in);
            int number = Integer.parseInt(scanner.nextLine());

            System.out.println(sumDigits(number));
        }
        System.out.println("введите число");
        Scanner scanner1 = new Scanner(System.in);
        String string = scanner1.nextLine();
        System.out.println(sumDigits1(string));

    }

    public static Integer sumDigits(Integer number) {
        //@TODO: write code here
        int summa = 0;

        while (number > 0) {
            summa = summa + number % 10;
            number = number / 10;
        }
        return summa;
    }

    public static Integer sumDigits1(String number) {
        int summa = 0;
        for (int i = 0; i < number.length(); i++) {
            summa = summa + Character.getNumericValue(number.charAt(i));
        }
        return summa;
    }
}
