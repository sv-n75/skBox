package task_7all;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


// Сделайте сортировку сотрудников одновременно по заработной плате и алфавиту.

//Выведите в консоль с помощью Stream API сотрудника с максимальной зарплатой среди тех, кто пришёл в 2017 году.
//        Критерии оценки
//        «Зачёт» — в консоль выведен сотрудник и его зарплата.
//        «Незачёт» — задание не выполнено.
//


public class Main {
    private static String staffFile = "C:\\Users\\123\\Desktop\\SkBox\\staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {

        ArrayList<Employee> staff = loadStaffFromFile();
        staff.sort((o1, o2) -> {
            if (o1.getSalary().compareTo(o2.getSalary()) == 0) {
                String first = o1.getName().substring(o1.getName().indexOf(" "));
                String second = o2.getName().substring(o2.getName().indexOf(" "));
                return first.compareTo(second);
            } else
                return o1.getSalary().compareTo(o2.getSalary());
        });

        staff.forEach(System.out::println);

        System.out.println();

        // задание 7,2Выведите в консоль с помощью Stream API сотрудника с максимальной зарплатой
        // среди тех, кто пришёл в 2017 году.

        System.out.println("Наибольшая зарплата среди пришедших в 2017 году ");
        staff.stream().filter(s -> s.getWorkStart().getYear() == 117).//вижу зачеркнуто но другого не нашел
                max(Comparator.comparing(Employee::getSalary)).
                ifPresent(e -> System.out.print(e.getName() + " -  " + e.getSalary()));

        System.out.println();
        System.out.println();

//                Задание №2
//        Что нужно сделать
//        Распечатайте с помощью библиотеки airport.jar время вылета и модели самолётов,
//        вылетающих в ближайшие два часа.

        Airport airport = Airport.getInstance();
        airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getDate().getTime() > (System.currentTimeMillis()))
                .filter(flight -> flight.getDate().getTime() < (System.currentTimeMillis() + 7200000))
                //   .filter(flight -> flight.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime().isBefore(LocalTime.now().plusHour(2)))
                //   .filter(flight -> flight.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime().isAfter(LocalTime.now()))
                //ВЫВОДИТ ДАТУ В ЧАСАХ И МИНУТАХ ПО ЗАДАННОМУ DATE НА 2 ДНЯ НЕЛЬЗЯ ИЗМЕНИТЬ
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .forEach(flight -> System.out.println(flight.getDate() + " - " + flight.getAircraft()));
        // .forEach(flight -> System.out.println(flight.toString() + " - " + flight.getAircraft()));//возможный вариант другой
    }


    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}
