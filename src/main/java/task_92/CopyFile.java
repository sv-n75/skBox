package task_92;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class CopyFile {
//    •	Напишите код, который копирует одну указанную папку в другую. При копировании должны сохраниться
//    файлы и структура папки.
//            •	Папки запрашивайте у пользователя в консоли.
//            •	Программа должна перехватывать все исключения, возникающие при ошибках
//            чтения файлов и папок, и выводить сообщение об ошибке с трассировкой стека (stack trace).


    public static void main(String[] args) {
        // Path source = Paths.get("C:\\Users\\123\\Desktop\\Javanail");
        //  Path destination = Paths.get("C:\\Users\\123\\Desktop\\newFolder");

        Scanner scanner = new Scanner(System.in);
        String sourceString = "";
        while (!sourceString.equals("END")) {
            System.out.println("Введите папку для копирования и папку куда будете копировать");
            sourceString = scanner.nextLine();
            Path source = Paths.get(sourceString);
            if (Files.exists(source)) {
                String stringDestination = scanner.nextLine();
                File file = new File(String.valueOf(stringDestination));
                file.mkdirs();
                if (file.isDirectory()) {
                    Path destination = Paths.get(stringDestination);
                    destination = destination.resolve(source.getFileName());//папка оказалась в папке}
                    try {
                        Files.walkFileTree(source, new MyFileVisit(source, destination));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else
                    System.out.println("Нельзя создать папку");
            } else
                System.out.println("Такой папки не существует");
        }
        scanner.close();
    }
}

class MyFileVisit extends SimpleFileVisitor<Path> {
    private Path source;
    private Path destination;

    public MyFileVisit(Path source, Path destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path newDestination = destination.resolve(source.relativize(dir));
        Files.copy(dir, newDestination, StandardCopyOption.REPLACE_EXISTING);//если папка существует а не мы ее создали
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path newDestination = destination.resolve(source.relativize(file));
        Files.copy(file, newDestination, StandardCopyOption.REPLACE_EXISTING);
        return super.visitFile(file, attrs);
    }
}

