package gyak01;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private ArrayList<SalariedEntity> salariedEntities;

    public Company() {
    }
    
    public Company(ArrayList<SalariedEntity> salariedEntities) {
        this.salariedEntities = salariedEntities;
    }
    
    public void addSalariedEntity(SalariedEntity e){
        salariedEntities.add(e);
    }
    
    public void removeSalariedEntity(SalariedEntity e){
        salariedEntities.remove(e);
    }
    
    public void increaseAllEmplyeSalary(double percentage){
        for(SalariedEntity e : salariedEntities){
            if(e instanceof Employee){
                ((Employee) e).increaseSalary(percentage);
            }
        }
    }
    
}
