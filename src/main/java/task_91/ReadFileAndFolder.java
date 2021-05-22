package task_91;
//Напишите программу. Она должна:
//        •	получать через консоль путь от пользователя до папки;
//        •	суммировать размер файлов папки и вложенных папок;
//        •	выводить полученную сумму файлов в удобочитаемом виде — в байтах, килобайтах, мегабайтах или гигабайтах;
//        •	программа должна перехватывать все исключения, возникающие при ошибках чтения файлов и папок, и выводить сообщение об ошибке с трассировкой стека (stack trace).
//        •
//        Примеры работы программы
//        Введите путь до папки:
//        D:\Games
//        Размер папки D:\Games cоставляет 56,6 Гб

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class ReadFileAndFolder {
    public static void main(String[] args) {

        // D:\java
        Scanner scanner = new Scanner(System.in);
        String readFile = "";
        while (!readFile.equals("END")) {
            System.out.println("Введите папку для получения размера");
            readFile = scanner.nextLine();
            Path source = Paths.get(readFile);
            if (Files.exists(source)) {
                long size = 0;
                try (Stream<Path> walk = Files.walk(source)) {
                    size = walk
                            .filter(Files::isRegularFile)
                            .mapToLong(p -> {
                                try {
                                    return Files.size(p);
                                } catch (IOException e) {
                                    System.out.printf("Невозможно получить размер файла %s%n%s", p, e);
                                    return 0L;
                                }
                            })
                            .sum();
                } catch (UncheckedIOException e) {
                    System.out.printf("Непроверяемое исключение %s%n", e);
                } catch (IOException e) {
                    System.out.printf("Ошибка при подсчёте размера директории %s", e);
                }
                printSize(size);
            } else {
                System.out.println("Неверный путь");
            }
        }
    }


    public static void printSize(long size) {
        int kb = 1000;
        int mb = 1000000;
        int gb = 1000000000;
        double normalSize;
        if (size >= kb && size < mb) {
            normalSize = Math.round((double) size / (kb / 10));
            System.out.println(normalSize / 10 + " кб.");
        }
        if (size >= mb && size < gb) {
            normalSize = Math.round((double) size / (mb / 10));
            System.out.println(normalSize / 10 + " мб.");
        }
        if (size > gb) {
            normalSize = Math.round((double) size / (gb / 10));
            System.out.println(normalSize / 10 + " гб.");
        }
        if (size < kb)
            System.out.println(size + " байт");

    }
}
