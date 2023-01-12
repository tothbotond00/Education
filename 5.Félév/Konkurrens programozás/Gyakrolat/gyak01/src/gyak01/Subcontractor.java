package gyak01;

public class Subcontractor implements SalariedEntity{

    private final long taxNum;
    private final double salary;
    
    public Subcontractor(long taxNum, double salary) {
        this.taxNum = taxNum;
        this.salary = salary;
    }
    
    public long getTaxNum() {
        return this.taxNum;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }
    
}
