package task_95.core;

import java.util.*;
import java.util.stream.Collectors;

public class MoscowMetroStationsLines {//это пишем в наш json

    TreeMap<String, Set<String>> stations = new TreeMap<>();//наше метро линии-станции ключ - значения
    List<TreeSet<Station>>connections = new ArrayList<>();//переходы
    TreeSet<Line>lines = new TreeSet<>();//линии

    public MoscowMetroStationsLines(){}//

    public void addLinesStation(Line line, List<Station>stations){
        Set<String>stringSetStation = stations.stream().map(Station::getName).collect(Collectors.toSet());
        this.stations.put(line.getNumber(), stringSetStation);
    }

    public void  addConnect (TreeSet<Station>stations){
        if (!connections.contains(stations))
            connections.add(stations);
    }

    public void addLine(Line line){
        this.lines.add(line);
    }

    public List<TreeSet<Station>>getConnections(){
        return connections;
    }
}
