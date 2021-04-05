package task_61;

import java.io.IOException;

public class BankAccount {

    private double summa;

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public double getSumma() {
        return summa;
    }


    public BankAccount(double summa) {
        this.summa = summa;
    }

    public void balance() {
        System.out.println(summa);
    }

    public void into_Account(double money) {
        summa = summa + money;
    }

    public void from_Account(double money) {
        if (summa - money > 0)
            summa = summa - money;
        else
            System.out.println("недостаточно средств");
    }

    public boolean send(BankAccount receiver, double amount) {
        double summa1 = this.summa;
        this.from_Account(amount);


        if (this.summa != summa1) {
            receiver.into_Account(amount);
            return true;
        } else
            return false;

    }


}





