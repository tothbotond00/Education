/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Class {
   
    private final ArrayList<Student> classes;

    public Class(ArrayList<Student> classes) {
        this.classes = classes;
    }
    
    public Class() {
        this.classes = new ArrayList<>();
    }
    
    public void addToClass (Student s){
        classes.add(s);
    }
    
    public void removeFromClass(Student s){
        classes.remove(s);
    }

    @Override
    public String toString() {
        return classes.toString();
    }
    
    public Student worst(){
        Student s = classes.get(0);
        for (int i = 1; i < classes.size(); i++) {
            if(s.getAvgmark() > classes.get(i).getAvgmark() ){
                s = classes.get(i);
            }
        }
        return s;
    }
    
    public Student best(){
                Student s = classes.get(0);
        for (int i = 1; i < classes.size(); i++) {
            if(s.getAvgmark() < classes.get(i).getAvgmark() ){
                s = classes.get(i);
            }
        }
        return s;
    }
    
    public int classSize(){
        return classes.size();
    }
    
   public Student getStudent(int i){
       return classes.get(i);
   }
}
