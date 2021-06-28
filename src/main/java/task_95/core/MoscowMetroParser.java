package task_95.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MoscowMetroParser {

    private final static String urlString = "https://www.moscowmap.ru/metro.html#lines";
    Document docMoscowMetro;
    static Elements elements;

    public MoscowMetroParser() throws IOException {
        this.docMoscowMetro = Jsoup.connect(urlString)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .maxBodySize(0).get();
        this.elements = docMoscowMetro.select("div#metrodata");
    }

    public static List<Line> getLines() {//линии - номер линии и название
        List<Line> listAllLines = new ArrayList<>();
        Elements lines = elements.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");
        Map<String, String> namesOfLines = lines.stream()
                .collect(Collectors.toMap((k) -> k.attr("data-line"), Element::text));
        namesOfLines.forEach((k, v) -> listAllLines.add(new Line(k, v)));

        return listAllLines;
    }

    public static List<Station> getStations(Line line) {//станции - номер линии- строка и название станции
        Elements stations = elements.select("div.js-metro-stations.t-metrostation-list-table[data-line = " + line.getNumber() + "]")
                .select("span.name");
        List<String> namesStations = stations.stream().map(Element::text).collect(Collectors.toList());
        return namesStations.stream().map(s -> new Station(s, line)).collect(Collectors.toList());
    }

    public List<TreeSet<Station>> getConnections(List<Line> lineList,// передаем лист линий и лист станций
                                                 Map<String, Station> listStation) {
        List<TreeSet<Station>> result = new ArrayList<>();//создаем лист из где номер -станция - переход номер станция по номерам
        try {
            lineList.stream().map(line -> elements.select(
                    "div.js-metro-stations.t-metrostation-list-table[data-line = " + line
                            .getNumber() + "]")// ищем переходы по каждой линии это р между р инфа о станции и переходах
                    .select("p")).map(connections -> {
                return connections.stream().map(stations -> {//поток из этих инф и проходим по массиву станций
                    TreeSet<Station> stationSet = new TreeSet<>();//список станций по порядку
                    stationSet.add(listStation.get(stations.select("span.name").text()));//добавляем станцию - возвращая значение ключа те номера линии
                    for (Line otherLine : lineList) {//и проходимся по другим линиям
                        if (getNameStation(stations, otherLine) != null) {//ищем в переходах станцию  и добавляем если находим
                            stationSet.add(listStation.get(getNameStation(stations, otherLine)));//ну а добавляем что есть
                        }
                    }
                    return stationSet;
                }).filter(s -> s.size() > 1).collect(Collectors.toList());//если не одна те есть переход добавляем в результ
            }).forEach(result::addAll);
            return result;
        } catch (Exception e) {
            e.getStackTrace();
            return result;
        }
    }

    public static String getNameStation(Element element, Line line) {
        try {
            String title = element.select("span.ln-" + line.getNumber()).attr("title");//где титлы там кавычки убрать
            if (!title.isBlank() && !title.isEmpty()) {
                int begin = title.indexOf("«") + 1;
                int end = title.lastIndexOf("»");
                return title.substring(begin, end);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
}
