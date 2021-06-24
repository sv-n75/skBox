package task_82;

import task_82.core.Station;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator {
    private StationIndex stationIndex;//карта метро

    private static double interStationDuration = 2.5;// между станциями
    private static double interConnectionDuration = 3.5;// между линиями

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    public List<Station> getShortestRoute(Station from, Station to) {
        List<Station> route = getRouteOnTheLine(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithOneConnection(from, to);
        if (route != null) {
            return route;
        }

        route = getRouteWithTwoConnections(from, to);
        return route;
    }

    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;
        for (int i = 0; i < route.size(); i++) {
            Station station = route.get(i);
            if (i > 0)//к 1 станции перехода нет поэтому считаем от нее
            {
                duration += previousStation.getLine().equals(station.getLine()) ?
                        interStationDuration : interConnectionDuration;
            }
            previousStation = station;
        }
        return duration;
    }


    private List<Station> getRouteOnTheLine(Station from, Station to) {
        if (!from.getLine().equals(to.getLine())) {//если не на 1 линии 0
            return null;
        }
        ArrayList<Station> route = new ArrayList<>();
        List<Station> stations = from.getLine().getStations();
        int direction = 0;
        for (Station station : stations) {
            if (direction == 0) {
                if (station.equals(from)) {
                    direction = 1;
                } else if (station.equals(to)) {
                    direction = -1;//это если 1 оказалась станция назначения
                }
            }

            if (direction != 0) {//как только нашли одну из станция начинаем писать
                route.add(station);
            }

            if ((direction == 1 && station.equals(to)) ||
                    (direction == -1 && station.equals(from))) {//дошли до 2 выходим
                break;
            }
        }
        if (direction == -1) {
            Collections.reverse(route);// если 1 станция назначения переворчиваем список
        }
        return route;
    }

    private List<Station> getRouteWithOneConnection(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();//массив станции линии отправления
        List<Station> toLineStations = to.getLine().getStations();//назначения
        for (Station srcStation : fromLineStations)//перебираем
        {
            for (Station dstStation : toLineStations) {
                if (isConnected(srcStation, dstStation))//если есть соединение
                {
                    ArrayList<Station> way = new ArrayList<>();
                    way.addAll(getRouteOnTheLine(from, srcStation));//до коннекта от назначения и обратно
                    way.addAll(getRouteOnTheLine(dstStation, to));
                    if (route.isEmpty() || route.size() > way.size())// если маршрут не один выбараем короткий(наскоько жизненно?)
                    {
                        route.clear();
                        route.addAll(way);
                    }
                }
            }
        }
        if (route.isEmpty()) {
            return null;       // проверку на пустоту добавили из- за нее ошибка если маршрут не найден, то возвращаем null
            // и переходим к следующему методу соответственно на верху проверить не на линии а на точку соедиения по хорошему
        }
        return route;
    }

    private boolean isConnected(Station station1, Station station2) {
        Set<Station> connected = stationIndex.getConnectedStations(station1);
        return connected.contains(station2);
    }

    private List<Station> getRouteViaConnectedLine(Station from, Station to) {
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);
        Set<Station> toConnected = stationIndex.getConnectedStations(to);
        for (Station srcStation : fromConnected) {
            for (Station dstStation : toConnected) {
                if (srcStation.getLine().equals(dstStation.getLine())) {//ищет станции линии соединения переходов
                    return getRouteOnTheLine(srcStation, dstStation);
                }
            }
        }
        return null;
    }

    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {//тоже не надо бы схожи с методом на 1 соед - а если больше выкиннет пустой
            return null;
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();
        for (Station srcStation : fromLineStations)//станции  линии отправления
        {
            for (Station dstStation : toLineStations)//назначения
            {
                List<Station> connectedLineRoute =
                        getRouteViaConnectedLine(srcStation, dstStation);
                if (connectedLineRoute == null) {
                    continue;//если общей нет крутим дальше
                }
                ArrayList<Station> way = new ArrayList<>();
                way.addAll(getRouteOnTheLine(from, srcStation));
                way.addAll(connectedLineRoute);
                way.addAll(getRouteOnTheLine(dstStation, to));
                if (route.isEmpty() || route.size() > way.size())//если маршрутов несколь короткий
                {

                    route.clear();
                    route.addAll(way);

                }
            }
        }

        return route;
    }
}

