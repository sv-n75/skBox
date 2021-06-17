package task_64;

public class Manager implements Employee {


    private static final int salaryMan = 60000;
    private int earningsCompany;

    public Manager() {
        this.earningsCompany = (int) (Math.random() * 25000) + 115000;
    }

    public int getEarningsCompany() {
        return earningsCompany;
    }

    public int getMonthSalary(Company company) {
        return salaryMan + (int) (earningsCompany * 0.05);
    }
}
