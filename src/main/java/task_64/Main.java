package task_64;

public class Main {
//    . Создайте класс компании Company, содержащей сотрудников и реализующей методы:
//            •	найм одного сотрудника — hire(),
//•	найм списка сотрудников – hireAll(),
//•	увольнение сотрудника – fire(),
//•	получение значения дохода компании – getIncome().
//    Аргументы и возвращаемое значение методов выберите на основании логики работы вашего приложения.
//            2. Создайте два метода, возвращающие список указанной длины (count). Они должны содержать
//            сотрудников, отсортированных по убыванию и возрастанию заработной платы:
//            •	List<Employee> getTopSalaryStaff(int count),
//•	List<Employee> getLowestSalaryStaff(int count).
//            3. Создайте классы сотрудников с информацией о зарплатах и условиями начисления зарплаты:
//            •	Manager — зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных
//            для компании денег. Количество заработанных денег для компании генерируйте случайным образом
//            от 115 000 до 140 000 рублей.
//•	TopManager — зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы, если
// доход компании более 10 млн рублей.
//            •	Operator — зарплата складывается только из фиксированной части.
//    Каждый класс сотрудника должен имплементировать интерфейс Employee. В интерфейсе Employee должен быть
//    объявлен метод, возвращающий зарплату сотрудника:
//            •	getMonthSalary()
//    Аргументы и возвращаемое значение метода выберите в соответствии с логикой начисления зарплат.
//
//
//    Для демонстрации и тестирования работы ваших классов:
//            1.	Создайте и наймите в компанию: 180 операторов Operator, 80 менеджеров по продажам Manager,
//            10 топ-менеджеров TopManager.
//            2.	Распечатайте список из 10–15 самых высоких зарплат в компании.
//3.	Распечатайте список из 30 самых низких зарплат в компании.
//4.	Увольте 50% сотрудников.
//5.	Распечатайте список из 10–15 самых высоких зарплат в компании.
//6.	Распечатайте список из 30 самых низких зарплат в компании.
//
//    Примеры вывода списка зарплат
//    Список из пяти зарплат по убыванию:
//            •	230 000 руб.
//•	178 000 руб.
//•	165 870 руб.
//•	123 000 руб.
//•	117 900 руб.
//
//            Рекомендации
//•	Можно создавать разные экземпляры компании со своим списком сотрудников и доходом.
//•	Чтобы получить данные компании внутри класса сотрудника, настройте хранение ссылки на Company и передавайте
// объект Company с помощью конструктора или сеттера.
//            •	Учтите, в методы получения списков зарплат могут передаваться значения count, превышающие
//            количество сотрудников в компании, или отрицательные.


    public static void main(String[] args) {

        Company company = new Company();

        for (int i = 0; i < 180; i++) {
            Employee employee = new Operator();
            company.hire(employee);
        }

        for (int i = 0; i < 80; i++) {
            Employee employee = new Manager();
            company.hire(employee);
        }

        for (int i = 0; i < 10; i++) {
            Employee employee = new TopManager();
            company.hire(employee);
        }
        System.out.println(company.countEmployees());

        System.out.println("Самые высокие зарплаты: ");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + " рублей");
        }

        System.out.println("Самые низкие зарплаты: ");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + " рублей");
        }

        int count = company.countEmployees();
        for (int i = 0; i < count / 2; i++) {
            int number = (int) (Math.random() * company.countEmployees());
            company.fire(company.getEmployees().get(number));
        }
        System.out.println("Уволено " + count / 2 + " сотрудников");

        System.out.println("Самые высокие зарплаты: ");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + " рублей");
        }

        System.out.println("Самые низкие зарплаты: ");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + " рублей");
        }
    }
}
