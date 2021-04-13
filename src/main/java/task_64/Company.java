package task_64;

import java.util.*;

public class Company {

    private List<Employee> employees = new ArrayList<Employee>();

    public void hire(Employee employee) {//прием сотрудника
        this.employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {//прием списка
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {//увольнение
        this.employees.remove(employee);
    }

    public static int getIncome() {//доход
        return 250000000;
    }

    public List<Employee> getTopSalaryStaff(int count) {//список из count высокоопл
        return getCountList(count, new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o2.getMonthSalary() - o1.getMonthSalary();
            }
        });
    }

    public List<Employee> getLowestSalaryStaff(int count) {//список коунт низкооплач
        return getCountList(count, new Comparator<Employee>() {
            public int compare(Employee o1, Employee o2) {
                return o1.getMonthSalary() - o2.getMonthSalary();
            }
        });
    }

    private List<Employee> getCountList(int count, Comparator<Employee> comparator) {//список от каунта и компратора
        List<Employee> copyList = new ArrayList<Employee>(employees);//метод копирования
        Collections.sort(copyList, comparator);//сортируем копию с помощью компратора
        List<Employee> result = new ArrayList<>();//новый лист куда заносим каунт элементов
        for (int i = 0; i < count; i++) {
            result.add(copyList.get(i));
        }
        return result;
    }

//
    public int countEmployees() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
