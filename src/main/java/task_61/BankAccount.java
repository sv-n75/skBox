package task_61;

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

import java.io.IOException;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void printBalance() {
        System.out.println(balance);
    }

    public void into_Account(double money) {
        balance = balance + money;
    }

    public void from_Account(double money) {

        if (canDischarge(money)) {
            balance = balance - money;
        }

    }


    public boolean send(BankAccount receiver, double amount) {
        if (canDischarge(amount)) {
            this.from_Account(amount);
            receiver.into_Account(amount);
            return true;
        } else return false;
    }


    public boolean canDischarge(double money) {
        if (balance < money) {
            try {
                throw new BalanceException();
            } catch (BalanceException e) {
                System.out.println("Недостаточно денег");
            }
            return false;
        }
        return true;
    }
}





