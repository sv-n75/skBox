package task_63;

public class Person extends Client {

    public Person(double summa) {
        super(summa);
    }

    @Override
    public void balance() {
        System.out.println("Снятие и внесение на счет физ лица происходит без комиссии");
        System.out.println("остаток на счете " + getSumma());
    }

    @Override
    public void into_Account(double money) {
        setSumma(getSumma() + money);
    }

    @Override
    public void from_Account(double money) {
        if (getSumma() - money > 0)
            setSumma(getSumma() - money);
        else System.out.println("недостаточно средств");
    }
}
