package task_93;
//Научиться читать файл CSV и анализировать его.
//        Что нужно сделать
//        1.	Создать новый проект, который будет читать файл csv банковской выписки movementsList.csv и парсить
//        полученные строки. Путь к файлу выписки храните в константе.
//        2.	Код должен выводить сводную информацию по этой выписке: общий приход, общий расход
//        и разбивку расходов.
//        3.
//        Примеры работы программы
//        Сумма расходов: 398 563.39 руб.
//        Сумма доходов: 289 890.06 руб.
//
//        Суммы расходов по организациям:
//        RUSMOSKVA56  SHLOVE REPUBLIC        1 081.53 руб.
//        RUSMOSCOW42 SHCL ETOILE                     126.34 руб.
//        RUSPUSHKINO105ZOOMAGAZIN 4             217.65 руб.


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadCSV {

    private static final String sourceFile = "D:\\skBox\\movementList.csv";
    private static final double euroCourse = 95;
    private static final double usdCourse = 80;
    private static final Pattern operationPattern = Pattern.compile("[\\/\\\\]\\S+\\s?\\S+\\s?\\S+\\s?\\S+");
    private static final Pattern valutePattern = Pattern.compile("(USD)|(EUR)");

    public static void main(String[] args) throws IOException {
        ArrayList<Transaction> operations = loadFromFile(sourceFile);
        double summaInputMoney = operations.stream().map(Transaction::getInputMoney).reduce((a, b) -> a + b).get();
        System.out.println("Сумма доходов: " + summaInputMoney + " руб.");
        double summaOutputMoney = operations.stream().map(Transaction::getOutputMoney).reduce(Double::sum).get();//нижнее правильно
        System.out.println("Сумма расходов: " + summaOutputMoney + " руб.");
        System.out.println();
        Map<String, Double> outputOrganisation = operations.stream().filter(a -> a.getOutputMoney() > 0)
                .collect(Collectors.toMap(Transaction::getNameTransaction, Transaction::getOutputMoney, Double::sum));
        System.out.println("Расходы по организациям: ");
        for (Map.Entry<String, Double> entry : outputOrganisation.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.println(key + "    " + value + " руб.");
        }
    }

    public static ArrayList<Transaction> loadFromFile(String sourceFile) throws IOException {
        ArrayList<Transaction> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(sourceFile));//создаем массив строк
        lines.remove(0); //убираем заголовок
        for (String strings : lines) {
            String[] fragmentLine = strings.split(",");//бъем по запятым - наши 2 или 3 посл если это валюта
            if (fragmentLine.length == 9) {
                fragmentLine[7] = fragmentLine[7] + "." + fragmentLine[8];
            }
            double outputtMoney = normalMoney(fragmentLine[7], fragmentLine[5]);
            double inputMoney = normalMoney(fragmentLine[6], fragmentLine[5]);
            String nameTransaction = normalOperation(fragmentLine[5]);
            result.add(new Transaction(nameTransaction, inputMoney, outputtMoney));
        }
        return result;
    }

    public static double normalMoney(String money, String operationValute) {
        String normalMoneyString = money.replaceAll("\"", "");//убираем мусор
        Matcher matcherMoney = valutePattern.matcher(operationValute);
        double valueMoney;
        if (matcherMoney.find()) {
            String nameMoney = matcherMoney.group();
            if (nameMoney.equals("EUR")) {
                valueMoney = Math.round(Double.parseDouble(normalMoneyString) * 100);
                return valueMoney / 100 * euroCourse;
            }
            valueMoney = Math.round(Double.parseDouble(normalMoneyString) * 100);
            return valueMoney / 100 * usdCourse;
        }
        valueMoney = Math.round(Double.parseDouble(normalMoneyString) * 100);
        return valueMoney / 100;
    }

    public static String normalOperation(String operation) {
        Matcher matcherOperation = operationPattern.matcher(operation);
        if (matcherOperation.find()) {
            return matcherOperation.group().replaceAll("[\\\\/\\><]", "");
        } else return null;
    }
}

class Transaction {
    private String nameTransaction;
    private double inputMoney;
    private double outputMoney;

    public Transaction(String nameTransaction, double inputMoney, double outputMoney) {
        this.nameTransaction = nameTransaction;
        this.inputMoney = inputMoney;
        this.outputMoney = outputMoney;
    }

    public String getNameTransaction() {

        return nameTransaction;
    }

    public double getInputMoney() {
        return inputMoney;
    }

    public double getOutputMoney() {
        return outputMoney;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "nameTransaction='" + nameTransaction + '\'' +
                ", inputMoney=" + inputMoney +
                ", outputMoney=" + outputMoney +
                '}';
    }
}
