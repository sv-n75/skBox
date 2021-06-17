package task_61;

public class CardAccount extends BankAccount {

    CardAccount(double balance) {
        super(balance);
    }

    @Override
    public void from_Account(double money) {
        super.from_Account(money * 1.01);
    }

    @Override
    public boolean canDischarge(double money) {
        return super.canDischarge(money * 1.01);
    }
}
