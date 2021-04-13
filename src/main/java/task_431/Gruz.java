package task_431;

import java.util.Scanner;

public class Gruz {
//    Вы доставляете гуманитарную помощь в ящиках одинакового размера. У вас есть грузовики и контейнеры.
//    В каждый грузовик помещается максимум 12 контейнеров. В каждый контейнер — не более 27 ящиков.
//    Ящики, контейнеры и грузовики пронумерованы.
//    Напишите программу, которая распределит ящики по контейнерам и грузовикам в зависимости
//    от их количества. Программа должна выводить необходимое для этого число грузовиков и контейнеров.
//
//    Принцип работы программы
//    Указываем число ящиков — получаем результат в виде строк:
//    Грузовик 1:
//    Контейнер 1:
//    Ящик 1
//    Ящик 2
//    Ящик 3
//            …
//    Контейнер 2
//    Ящик 28
//    Ящик 29
//            …
//    Необходимо:
//    грузовиков — 1 шт.


    public static void main(String[] args) {
        System.out.println("Введите число ящиков");
        Scanner scanner = new Scanner(System.in);
        int value = Integer.parseInt(scanner.nextLine());
        printValue(value);

    }

    public static void printValue(int value) {
        int truckValue = 1;
        int conteinerValue = 1;
        String otstup = " ";

        System.out.println("Грузовик " + truckValue + ":");
        System.out.println("Контейнер " + conteinerValue + ":");

        for (int i = 1; i <= value; i++) {

            String string = "Ящик " + i;
            int otstupBox = string.length() + 2;
            // System.out.print(string.indent(6));
            System.out.printf("%6s%s \n", otstup, string);// УБИЛСЯ но не смог заменить 6 пробелов длиной строки плюс6
            if (i % (27 * 12) == 0) {
                truckValue++;
                conteinerValue++;
                System.out.println("Грузовик " + truckValue + ":");
                System.out.println("Контейнер " + conteinerValue + ":");
            }
            if (i % 12 == 0 && i % (12 * 27) != 0) {
                conteinerValue++;
                String string1 = "Контейнер " + conteinerValue + ":";
                System.out.printf("%2s%s \n", otstup, string1);
            }
        }
        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + truckValue + " шт.");
        System.out.println("контейнеров - " + conteinerValue + " шт.");
    }
}







