package task_63;

public class IP extends Client {
    public IP(double summa) {
        super(summa);
    }

    @Override
    public void balance() {
        System.out.println("пополнение с комиссией 1%, если сумма меньше 1000 рублей. И с комиссией 0,5%,\n" +
                "           если сумма больше либо равна 1000 рублей");
        System.out.println("остаток на счете " + getSumma());
    }

    @Override
    public void into_Account(double money) {
        if (money < 1000)
            setSumma(getSumma() + 0.99 * money);
        else
            setSumma(getSumma() + .995 * money);

    }

    @Override
    public void from_Account(double money) {
        if (getSumma() - money > 0)
            setSumma(getSumma() - money);
        else System.out.println("недостаточно средств");

    }
}
