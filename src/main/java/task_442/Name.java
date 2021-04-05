package task_442;

import java.util.Scanner;

public class Name {

    public static void main(String[] args) {
        System.out.println("введите ФИО полностью");
        Scanner scanner = new Scanner(System.in);
        String[] name = scanner.nextLine().split(" ");
        System.out.println("Фамилия : " + name[0]);
        System.out.println("Имя : " + name[1]);
        System.out.println("Отчество : " + name[2]);
    }

}
