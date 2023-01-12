/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inrectangle;

import java.io.FileNotFoundException;

/**
 *
 * @author Botond
 */
public class Main {

    public static void main(String[] args) {
        ///The execution of the tests stated in the documentation
        for (int i = 0; i <= 12; i++) {
            System.out.println(i+". Teszteset");
            try{
               ShapeCollection sc = new ShapeCollection();
                sc.read("test"+i+".txt");
                sc.report(); 
            }catch (FileNotFoundException e){
                System.out.println("The File does not exists");
            }catch(FileIsEmptyException e){
                System.out.println("The file was empty");
            }
            System.out.println("---------------------------------------------");
        }
    }
    
}
