package task_55;

import java.util.*;

public class CarNumber {
//    1. Напишите генератор «красивых» автомобильных номеров и методы поиска элементов в коллекциях:
//            •	прямым перебором по ArrayList,
//•	бинарным поиском по сортированному ArrayList,
//            •	поиском в HashSet,
//            •	поиском в TreeSet.
//2. Измерьте и сравните длительность каждого метода поиска.
//    Формат вывода результатов поиска:
//    Поиск перебором: номер найден/не найден, поиск занял 34нс
//    Бинарный поиск: номер найден/не найден, поиск занял 34нс
//    Поиск в HashSet: номер найден/не найден, поиск занял 34нс
//    Поиск в TreeSet: номер найден/не найден, поиск занял 34нс
//•	Сортировка не входит в учёт времени для бинарного поиска.
//•	Для детального сравнения методов поиска используйте время в наносекундах:
//            System.nanoTime()
//            •	Используйте правила генерации номеров для получения более 2 млн номеров:
//    XYZ — различные буквы, N — цифры, R — регион (от 01 до 199);
//    XNNNYZR — пример, A111BC197, Y777HC66,
//    таким образом, количество номер будет достаточно для оценки времени поиска даже в миллисекундах.


    public static void main(String[] args) {


        List<String> numberList = new ArrayList<>();

        String[] word = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};// "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
//long startTime = System.currentTimeMillis();
        for (int r = 1; r < 190; r++) {// регион

            for (int i = 0; i < word.length; i++) {//  по буквам один
                for (int j = 1; j <= 10; j++) {// первые 00
                    String string = String.format("%s%03d%s%02d", word[i], j, word[i] + word[i], r);

                    numberList.add(string);

                }
            }
        }
        for (int r = 1; r < 190; r++) {
            for (int i = 111; i <= 999; i += 111) {//одинаковые цифры буквы любые
                for (int a = 0; a < word.length; a++) {
                    for (int b = 0; b < word.length; b++) {
                        for (int c = 0; c < word.length; c++) {
                            String string = String.format("%s%03d%s%02d", word[a], i, word[b] + word[c], r);
                            numberList.add(string);

                        }
                    }
                }

            }
        }

        Set<String> numberTree = new TreeSet<>(numberList);
        Set<String> numberSet = new HashSet<>(numberList);
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime);

//for (int i =numberList.size()-1; i > numberList.size()-201; i--){
//    System.out.print(numberList.get(i));
//}
//        System.out.println(numberSet.size());
//        System.out.println(numberList.size());
//        System.out.println(numberTree.size());

        String searchNumber = "M111MM50";
        boolean search = false;

        long nano_startTime1 = System.nanoTime();
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i).equals(searchNumber)) {
                long endTime1 = System.nanoTime() - nano_startTime1;
                System.out.println("номер  в LIST перебором найден, поиск занял " + endTime1);
                search = true;
            }
        }
        if (!search) {
            long endTime1 = System.nanoTime() - nano_startTime1;
            System.out.println("номер в LIST перебором не найден, поиск занял " + endTime1);
        }


        Collections.sort(numberList);
        int begin = 0;
        int end = numberList.size() - 1;
        int mid = 0;
        boolean search1 = false;

        long nano_Start2 = System.nanoTime();
        while (begin <= end) {
            mid = (begin + end) / 2;
            if (numberList.get(mid).compareTo(searchNumber) < 0) {
                begin = mid + 1;
            } else if (numberList.get(mid).compareTo(searchNumber) > 0) {
                end = mid - 1;
            } else {
                long nan0_end = System.nanoTime() - nano_Start2;
                System.out.println("номер в sortList найден, поиск занял " + nan0_end);
                end = -1;
                search1 = true;

            }
        }
        if (!search1) {
            long nano_END = System.nanoTime() - nano_Start2;
            System.out.println("номер  в sortList не найден, поиск занял " + nano_END);
        }


        long nano_start = System.nanoTime();

        if (numberSet.contains(searchNumber)) {
            long nano_end = System.nanoTime() - nano_start;
            System.out.println("номер в Set найден, поиск занял " + nano_end);
        } else {
            long nano_end = System.nanoTime() - nano_start;
            System.out.println("номер в Set не найден поиск занял " + nano_end);
        }


        long nano_start1 = System.nanoTime();

        if (numberTree.contains(searchNumber)) {
            long nano_end1 = System.nanoTime() - nano_start;
            System.out.println("номер в TreeSet найден, поиск занял " + nano_end1);
        } else {
            long nano_end1 = System.nanoTime() - nano_start1;
            System.out.println("номер в TreeSet не найден поиск занял " + nano_end1);
        }


    }

}

