package gyak01;

public abstract class Employee implements SalariedEntity {
    
    protected String name;
    
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract double getSalary();
    
    public void increaseSalary(double percent){
        salary = salary * percent;
    }
    
}
