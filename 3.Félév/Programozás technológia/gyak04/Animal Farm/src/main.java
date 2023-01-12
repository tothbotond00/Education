
import java.io.FileNotFoundException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            AnimalCollection a = new AnimalCollection();
            a.read("data.txt");
            a.report();
        }catch(TypeNotExistsException e){
            System.out.println("baj vanASDasd");
        }catch(FileNotFoundException e){
            System.out.println("baj van");
        }
    }
    
}
