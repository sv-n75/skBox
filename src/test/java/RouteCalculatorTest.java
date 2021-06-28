import junit.framework.TestCase;
import task_82_83.RouteCalculator;
import task_82_83.StationIndex;
import task_82_83.core.Line;
import task_82_83.core.Station;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    RouteCalculator routeCalculator;
    StationIndex stationIndex;

    @Override
    protected void setUp() throws Exception {//создаем наше метро

    /*


                                                       3
                                                       |
                                                  (WhiteThird)
    (WhiteFirst) ----1---- (BrownFirst) ----1---- (BlackFirst) ----1---- (GreenFirs)
                                                       |
                                                       |
                                                   (BrownThird)
                                                       |
                                                       |3
                                                       |
                                                    (BlackThird)
                            (WhiteSecond) ----3---- (BrownSecond) ----3---- (BlackSecond) ----3---- (GreenSecond)
                                                         |
                                                         |3
                                                         |
                                                     (GreenThird)


     */

        stationIndex = new StationIndex();
        stationIndex.addLine(new Line(1, "First"));//линии
        stationIndex.addLine(new Line(2, "Second"));
        stationIndex.addLine(new Line(3, "Third"));

        stationIndex.addStation(new Station("WhiteFirst", stationIndex.getLine(1)));//станции
        stationIndex.addStation(new Station("BrownFirst", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("BlackFirst", stationIndex.getLine(1)));
        stationIndex.addStation(new Station("GreenFirst", stationIndex.getLine(1)));
        stationIndex.getLine(1).addStation(stationIndex.getStation("WhiteFirst"));//заполняем линии
        stationIndex.getLine(1).addStation(stationIndex.getStation("BrownFirst"));
        stationIndex.getLine(1).addStation(stationIndex.getStation("BlackFirst"));
        stationIndex.getLine(1).addStation(stationIndex.getStation("GreenFirst"));

        stationIndex.addStation(new Station("WhiteSecond", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("BrownSecond", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("BlackSecond", stationIndex.getLine(2)));
        stationIndex.addStation(new Station("GreenSecond", stationIndex.getLine(2)));
        stationIndex.getLine(2).addStation(stationIndex.getStation("WhiteSecond"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("BrownSecond"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("BlackSecond"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("GreenSecond"));

        stationIndex.addStation(new Station("WhiteThird", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("BrownThird", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("BlackThird", stationIndex.getLine(3)));
        stationIndex.addStation(new Station("GreenThird", stationIndex.getLine(3)));
        stationIndex.getLine(3).addStation(stationIndex.getStation("WhiteThird"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("BrownThird"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("BlackThird"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("GreenThird"));

        List<Station> connectionStation = new ArrayList<>();//1 переход
        connectionStation.add(stationIndex.getStation("WhiteThird"));
        connectionStation.add(stationIndex.getStation("BlackFirst"));
        stationIndex.addConnection(connectionStation);// добавляем переход
        connectionStation.clear();

        connectionStation.add(stationIndex.getStation("BlackThird"));
        connectionStation.add(stationIndex.getStation("BrownSecond"));
        stationIndex.addConnection(connectionStation);
        connectionStation.clear();

        routeCalculator = new RouteCalculator(stationIndex);
    }


    public void testGetRouteOneLine() {
        Station from = stationIndex.getStation("BrownFirst");//проверка маршрута который возвр метод getShortestRoutе
        Station to = stationIndex.getStation("GreenFirst");
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("BrownFirst"));
        expected.add(stationIndex.getStation("BlackFirst"));
        expected.add(stationIndex.getStation("GreenFirst"));

        List<Station> actual = routeCalculator.getShortestRoute(from, to);

        assertEquals(expected, actual);//сравниваем
    }

    public void testGetRouteWithOneConnection() {
        Station from = stationIndex.getStation("BrownFirst");//проверка маршрута который возвр метод getShortestRoutе
        Station to = stationIndex.getStation("BrownThird");
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("BrownFirst"));
        expected.add(stationIndex.getStation("BlackFirst"));
        expected.add(stationIndex.getStation("WhiteThird"));
        expected.add(stationIndex.getStation("BrownThird"));

        List<Station> actual = routeCalculator.getShortestRoute(from, to);

        assertEquals(expected, actual);//сравниваем
    }

    public void testGetRouteWithTwoConnection() {
        Station from = stationIndex.getStation("BrownFirst");//проверка маршрута который возвр метод getShortestRoutе
        Station to = stationIndex.getStation("BlackSecond");
        List<Station> expected = new ArrayList<>();

        expected.add(stationIndex.getStation("BrownFirst"));// может есть смысл сделать метод добавл станции
        expected.add(stationIndex.getStation("BlackFirst"));// где то ошибка
        expected.add(stationIndex.getStation("WhiteThird"));
        expected.add(stationIndex.getStation("BrownThird"));
        expected.add(stationIndex.getStation("BlackThird"));
        expected.add(stationIndex.getStation("BrownSecond"));
        expected.add(stationIndex.getStation("BlackSecond"));


        List<Station> actual = routeCalculator.getShortestRoute(from, to);

        assertEquals(expected, actual);//сравниваем
    }


    public void testCalculatorDurationOneLine() {
        Station from = stationIndex.getStation("WhiteFirst");
        Station to = stationIndex.getStation("GreenFirst");

        double expected = 7.5;//считаем вручную
        double actual = RouteCalculator.calculateDuration(routeCalculator.getShortestRoute(from, to));
        assertEquals(expected, actual);
    }

    public void testCalculatorDurationWithOneConnection() {
        Station from = stationIndex.getStation("BrownFirst");
        Station to = stationIndex.getStation("BrownThird");

        double expected = 8.5;
        double actual = RouteCalculator.calculateDuration(routeCalculator.getShortestRoute(from, to));
        assertEquals(expected, actual);

    }

    public void testCalculatorDurationWithTwoConnection() {
        Station from = stationIndex.getStation("WhiteFirst");
        Station to = stationIndex.getStation("GreenSecond");

        double expected = 22.0;
        double actual = RouteCalculator.calculateDuration(routeCalculator.getShortestRoute(from, to));
        assertEquals(expected, actual);

    }

    @Override
    protected void tearDown() throws Exception {

    }
}