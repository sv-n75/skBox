package task_95;

//Научиться получать данные из HTML-страницы, создавать и читать JSON-файлы.
//        Что нужно сделать
//        Напишите программу, которая:
//        1. Получает HTML-код страницы «Список станций Московского метрополитена»
//        https://www.moscowmap.ru/metro.html#lines с помощью библиотеки jsoup.
//        2. Парсит полученную страницу и получает из неё:
//        •	Линии московского метро (получаете имя линии, номер линии, цвет парсить не надо).
//        •	Станции московского метро (получаете имя станции, номер линии).
//        3. Создаёт и записывает на диск JSON-файл со списком станций по линиям и списком линий по
//        формату JSON-файла из проекта SPBMetro (файл map.json).
//        4. Читает файл и выводит в консоль количество станций на каждой линии.
//        Рекомендации
//        •	По умолчанию Jsoup читает 2 Мб данных с запрашиваемой страницы. Чтобы снять это ограничение,
//        необходимо добавить вызов метода maxBodySize(0), устанавливающий максимальное значение получаемых данных:
//        Document doc = Jsoup.connect(URL).maxBodySize(0).get();
//        значение 0 означает, что нет ограничений на принимаемый объём данных.
//        •	При изучении кода страницы удобно использовать консоль разработчика в браузере. Для этого нажмите F12,
//        перейдите во вкладку Elements и найдите тег <div id="metrodata">. В нём содержатся таблицы с линиями,
//        станциями и пересадками. Обращайте внимание на классы, напишите селекторы на основе найденных классов.
//        Посмотрите, как получать элементы по селекторам в документации JSoup.
//        •	Для более быстрого поиска нужных селекторов используйте онлайн сервис jsoup https://try.jsoup.org/
//
//        Критерии оценки
//        «Зачёт» — программа получает данные с сайта, записывает в файл и выводит информацию о  количестве
//        станций на линиях.
//        «Незачёт» — задание не выполнено.
//
//
//        Дополнительное задание*
//        Цель задания
//        Потренироваться в работе с библиотекой jsoup и формированием JSON-файлов.
//        Что нужно сделать
//        1.	Пропарсите и записывайте в JSON-файл переходы между станциями в дополнение к линиям и
//        станциям (коллекции имя станции, номер линии, между которым есть переходы).
//        Выведите в консоль количество переходов в метро


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import task_95.core.Line;
import task_95.core.MoscowMetroParser;
import task_95.core.MoscowMetroStationsLines;
import task_95.core.Station;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReadHtmlCreateJson {


    private final static String JSON_FILE = "D:\\skBox\\data\\mapmetro.json";
    private static Map<String, Station> stations = new TreeMap<>();//название станции - станция
    private static MoscowMetroStationsLines moscowMetroStationsLines = new MoscowMetroStationsLines();


    public static void main(String[] args) throws IOException {

        MoscowMetroParser mockowMetroParser = new MoscowMetroParser();//создаем объект класса парсера - получаем методы

        List<Line>metroLines = MoscowMetroParser.getLines();

        metroLines.forEach(line ->{
            moscowMetroStationsLines.addLine(line);//добавили линию
            List<Station> metroStation = mockowMetroParser.getStations(line);//по этой линии нашли станции
            moscowMetroStationsLines.addLinesStation(line, metroStation);//добавили линию  и набор станций
            metroStation.forEach(s -> stations.put(s.getName(), s));//имя - станция - добавляем в мап

    });

        List<TreeSet<Station>> allConnections = mockowMetroParser
                .getConnections(metroLines, stations);
        allConnections.forEach(s -> moscowMetroStationsLines.addConnect(s));
        mapMetroFile(moscowMetroStationsLines);
}

    private static void mapMetroFile(MoscowMetroStationsLines moscowMetroStationsLines) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            FileWriter fileToJson = new FileWriter(ReadHtmlCreateJson.JSON_FILE);
            gson.toJson(moscowMetroStationsLines, fileToJson);
            fileToJson.flush();
            fileToJson.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    }




