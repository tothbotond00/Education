/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author LENOVO
 */
public class Student {
    private String name;
    private String nationality;
    private double avgmark;

    public Student(String name, String nationality, double avgmark) {
        this.name = name;
        this.nationality = nationality;
        this.avgmark = avgmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public double getAvgmark() {
        return avgmark;
    }

    public void setAvgmark(double avgmark) {
        this.avgmark = avgmark;
    }

    @Override
    public String toString() {
        return  name + " (" + nationality + ") has a " + avgmark + " average mark";
    }
    
}
