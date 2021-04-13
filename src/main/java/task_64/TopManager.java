package task_64;

public class TopManager implements Employee {

    private static final int salaryTopMan = 100000;


    @Override
    public int getMonthSalary() {
        return salaryTopMan + (Company.getIncome() > 10000000 ? (int) (salaryTopMan * 1.5) : 0);
    }
}
