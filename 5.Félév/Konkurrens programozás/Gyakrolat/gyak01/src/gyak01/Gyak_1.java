package gyak01;

public class Gyak_1 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Employee e = new Subordinate("Boti", 100000);
        e.increaseSalary(150.5);
        System.out.println(e.getSalary());
    }
    
}
