package task_61;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepozitAccount extends BankAccount {
    private LocalDate start;

    public DepozitAccount(double balance) {
        super(balance);
        start = LocalDate.now();
    }

    @Override
    public void into_Account(double money) {
        start = LocalDate.now();
        super.into_Account(money);
    }

    @Override
    public boolean canDischarge(double money) {
        LocalDate end = LocalDate.now();
        long days = ChronoUnit.DAYS.between(start, end);
        if (days < 30) {
            try {
                throw new BalanceException();
            } catch (BalanceException e) {
                System.out.println("Прошло меньше 30 дней");
            }
            return false;
        }
        if (this.getBalance() < money) {
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
