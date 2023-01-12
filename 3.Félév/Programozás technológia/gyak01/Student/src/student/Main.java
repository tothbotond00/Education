/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Main {
    public static void main(String[] args) {
        Class osztaly = new Class();
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
            System.out.println("Nev: ");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("X")) break;
            System.out.println("Nemzetiseg: ");
            String nationality = sc.nextLine();
            System.out.println("Atlag: ");
            double avg = sc.nextDouble();
            osztaly.addToClass(new Student(name,nationality,avg));
            } catch(InputMismatchException ime){
                System.out.println("Rossz formátum!");
                break;
            } finally {
                sc.nextLine();
            }
        }
        
        osztaly.addToClass(new Student("Akos","magyar",4.5));
        osztaly.addToClass(new Student("Geri","nemet",2.0));
        System.out.println(osztaly);
        System.out.println("Best: " + osztaly.best());
        System.out.println("Worst: " + osztaly.worst());
        System.out.println("Osztondijasok:");
        for (int i = 0; i < osztaly.classSize(); i++) {
            if(osztaly.getStudent(i).getAvgmark() > 4.0){
                System.out.println(osztaly.getStudent(i));
            }
        }
    }
}
