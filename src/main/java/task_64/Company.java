package task_64;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {

    private static int incomeAll;

    private static List<Employee> employees = new ArrayList<Employee>();

    public void hire(Employee employee) {//прием сотрудника
        this.employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {//прием списка
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {//увольнение
        this.employees.remove(employee);
    }

    public int getIncomeAll() {//доход
        if (incomeAll != 0)// посчитать 1 раз если убрать ограничени будет считать с каждым вызовом по кругу
            return incomeAll;
        else {
            for (Employee a : employees) {
                if (a instanceof Manager) {
                    incomeAll += ((Manager) a).getEarningsCompany();
                }
            }
            return incomeAll;
        }
    }


    public List<Employee> getTopSalaryStaff(int count) {//список из count высокоопл
        if (!checkCount(count)) {
            System.out.println("Введенные данные не верны");
            return Collections.emptyList();
        } else {
            return getCountList(count, new Comparator<Employee>() {
                public int compare(Employee o1, Employee o2) {
                    return o2.getMonthSalary(Company.this) - o1.getMonthSalary(Company.this);
                }
            });
        }
    }

    public List<Employee> getLowestSalaryStaff(int count) {//список коунт низкооплач
        if (!checkCount(count)) {
            System.out.println("Введенные данные не верны");
            return Collections.emptyList();
        } else {
            return getCountList(count, new Comparator<Employee>() {
                public int compare(Employee o1, Employee o2) {
                    return o1.getMonthSalary(Company.this) - o2.getMonthSalary(Company.this);
                }
            });
        }
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

    public boolean checkCount(int count) {//проверяем каунт
        if (count < 0 || count > employees.size()) {
            return false;
        } else {
            return true;
        }
    }
}
