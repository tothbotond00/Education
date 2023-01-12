package gyak01;

import java.util.List;

public class Manager extends Employee {
    private List<Employee> emps;

    public Manager(String name, double salary) {
        super(name, salary);
    }
    
    public void addEmployee(Employee e){
        emps.add(e);
    }
    
    public void deleteEmployee(Employee e){
        emps.remove(e);
    }

    @Override
    public double getSalary() {
        double sum = 0;
        for(Employee e : emps){
            sum += e.getSalary() * 0.05;
        }
        return sum + this.salary;
    }
}
