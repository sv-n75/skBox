package task_63;

public abstract class Client {

    private double summa;

    public Client(double summa) {
        this.summa = summa;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public abstract void balance();

    public abstract void into_Account(double money);

    public abstract void from_Account(double money);

}
