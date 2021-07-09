package task_101;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//Напишите код, который выведет среднее количество покупок в месяц для каждого курса за 2018 год.
// Учитывайте диапазон месяцев, в течение которого были продажи. Подробнее в примере.
//     Старайтесь использовать только SQL, при этом  группировку по месяцам можно
//     сделать и с помощью Java.


public class PrintAverageValue {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/skillbox";
    private static final String USER_MYSQL = "root";
    private static final String PASSWORD_MYSQL = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL_MYSQL, USER_MYSQL, PASSWORD_MYSQL)) {
            Statement statement = connection.createStatement();// statement для sql
            ResultSet resultSet = statement.executeQuery("SELECT pl.course_name, (COUNT(month(pl.subscription_date))/"//метод позволяет задавать sql запос
                    + "MAX(month(pl.subscription_date))) AS middle_buy FROM PurchaseList pl "//month(pl.subscription_date) - получаем номер месяца
                    + "GROUP BY pl.course_name ORDER BY pl.course_name");
            int courseId = 0;
            System.out.printf("     %-40s%-16s%n", "Наименование курса", "Среднее количество покупок в месяц");
            while (resultSet.next()) {// читает построчно
                courseId++;
                String courseName = resultSet.getString(1);// по индексу колонки можно и по названию строки
                double averageBuy = resultSet.getDouble(2);
                System.out
                        .printf("%3d) %-40s%.2f шт. в месяц%n", courseId, courseName, averageBuy);//3 цифры - 35 знаков - 2 знака после запятой-перевод строки
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
