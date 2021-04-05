package task_61;

public class CardAccount extends BankAccount {

    CardAccount(double summa) {
        super(summa);
    }

    @Override
    public void setSumma(double summa) {
        super.setSumma(summa);
    }

    @Override
    public double getSumma() {
        return super.getSumma();
    }

    @Override
    public void balance() {
        super.balance();
    }

    @Override
    public void into_Account(double money) {
        super.into_Account(money);
    }

    @Override
    public void from_Account(double money) {
        if (getSumma() - 1.01*money >0)
        super.setSumma(getSumma() - 1.01 * money);
        else
            System.out.println("недостаточно средств");

    }
}
