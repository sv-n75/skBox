package task_61;

public class Main {

//    . Создайте класс BankAccount, который представляет собой расчётный счёт в банке. У класса необходимо
//    написать методы:
//    a. Снять со счёта сумму денег (без комиссии).
//    b. Вносить на счёт сумму денег (без комиссии).
//    c. Получить остаток на счёте.
//            2. Создайте два класса наследника, расширяющие работу с остатком на счёте:
//            •	Депозитный расчётный счёт, с которого нельзя снимать деньги в течение месяца после
//            последнего внесения.
//•	Карточный счёт, при снятии денег с которого будет взиматься комиссия 1%.
//Напишите метод в классе BankAccount:
//    boolean send(BankAccount receiver, double amount)
//    для отправки денег с одного счёта на другой. Метод должен вернуть true, если деньги успешно переведены.

    // Изучите методы и переменные проекта задания 6.1 и установите подходящие
    // модификаторы доступа у методов и переменных.

//    Я бы добавил еще один метод boolean canDischarge (double amount) и проверял бы остаток
//    после попытки вывода денег, если он >= 0 тогда true, иначе выбрасывать эксепшн.
//    Эксепшн можно взять любой стандартный, в учебных целях. На практике надо буддет написать свой
//    (это отдельный класс с 5 строками кода).
//    Внутри from_Account в блоке try-catch вызвать canDischarge.
//    В send убрать сохранение текущего баланса в summa1 и проверку this.summa != summa1.
//    Вместо этого в блоке try-catch вызвать два метода from_Account и into_Account и вернуть
//    true. Если исключене не выбрасывается, то все 3 строки выполняются, если выбрасывается,
//    тогда в catch обработать исключение и перед выходом из метода send вернуть false.
//    Считаю такой метод читать и понимать будет проще.


    public static void main(String[] args) throws BalanceException {


        BankAccount bankAccount = new BankAccount(1000);
        BankAccount bankAccount1 = new DepozitAccount(1000);
        BankAccount bankAccount2 = new CardAccount(1000);

        bankAccount.from_Account(200);
        bankAccount.into_Account(300);
        bankAccount.printBalance();

        bankAccount1.into_Account(500);
        bankAccount1.from_Account(100);
        bankAccount1.printBalance();

        bankAccount2.from_Account(500);
        bankAccount2.into_Account(400);
        bankAccount2.from_Account(100);
        bankAccount2.printBalance();

        System.out.println(bankAccount1.send(bankAccount, 500));
        System.out.println(bankAccount2.send(bankAccount, 500));
        bankAccount.printBalance();
        bankAccount1.printBalance();
        System.out.println(bankAccount2.send(bankAccount, 500));
        bankAccount2.printBalance();
        bankAccount.printBalance();
        System.out.println(bankAccount.send(bankAccount1, 1700));
        bankAccount.printBalance();
        bankAccount1.printBalance();
    }
}
