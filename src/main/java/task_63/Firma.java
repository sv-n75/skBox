package task_63;

public class Firma extends Client {

    public Firma(double summa) {
        super(summa);
    }

    @Override
    public void balance() {
        System.out.println("снятие с комиссией 1%.");
        System.out.println("остаток на счете " + getSumma());

    }

    @Override
    public void into_Account(double money) {
        setSumma(getSumma() + money);

    }

    @Override
    public void from_Account(double money) {
        if (getSumma() - 1.01 * money > 0)
            setSumma(getSumma() - 1.01 * money);
        else System.out.println("недостаточно средств");
    }
}
