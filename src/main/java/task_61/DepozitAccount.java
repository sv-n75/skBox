package task_61;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DepozitAccount extends BankAccount {
    private LocalDate start;


    public DepozitAccount(double summa) {
        super(summa);
        start = LocalDate.now();
    }

    @Override
    public void balance() {
        super.balance();
    }

    @Override
    public void into_Account(double money) {

        start = LocalDate.now();
        super.into_Account(money);
    }

    @Override
    public void from_Account(double money) {
        LocalDate end = LocalDate.now();
        long days = ChronoUnit.DAYS.between(start, end);
        if (days >= 30) {
            super.from_Account(money);
        } else
            System.out.println("Sorry, нельзя - прошло меньше 30 дней");
    }
}
