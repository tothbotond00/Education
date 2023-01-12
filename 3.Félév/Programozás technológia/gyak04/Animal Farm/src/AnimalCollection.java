
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class AnimalCollection {
    private final ArrayList<Animal> animals;
    private int numOfAnimals;

    public AnimalCollection() {
        numOfAnimals = 0;
        animals = new ArrayList<>();
    }
    
    public void add(Animal a){
        animals.add(a);
    }
    
    public ArrayList<Animal> getSkinnyAnimals(){
        ArrayList<Animal> solution = new ArrayList<>();
        animals.stream().filter(e -> (e.isSkinny())).forEachOrdered(e -> {
            solution.add(e);
        });
        return solution;
    }
    
    public ArrayList<Animal> getOneKiloPlus(){
        ArrayList<Animal> solution = new ArrayList<>();
        animals.stream().filter(e -> (e.moreThanOneKilo())).forEachOrdered(e -> {
            solution.add(e);
        });
        return solution;
    }
    
    public void read(String filename) throws FileNotFoundException, TypeNotExistsException{
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        numOfAnimals = sc.nextInt();
        while(sc.hasNext()){
            Animal animal;
            String type = sc.next();
            if ("C".equals(type) || "E".equals(type) || "G".equals(type) || "H".equals(type)){
                String name = sc.next();
                int weight = sc.nextInt();
                int numOfFeeds = sc.nextInt();
                animal = switch (type) {
                    case "C" -> new Cow(name,weight,numOfFeeds);
                    case "E" -> new Emu(name,weight,numOfFeeds);
                    case "G" -> new Goat(name,weight,numOfFeeds);
                 default -> new Horse(name,weight,numOfFeeds);
                 };
                 for (int i = 0; i < numOfFeeds; i++) {
                    animal.addFeed(sc.nextInt());
                }
                animals.add(animal);
            }
            else throw new TypeNotExistsException();  
        }
    }
    
    public void report(){
        System.out.println("The animals that are skinny: " + this.getSkinnyAnimals());
        System.out.println("The animals that ate more than 1 kilo" + this.getOneKiloPlus());
    }
}
