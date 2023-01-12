/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inrectangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Botond
 */
public class ShapeCollection {
    private ArrayList<Shape> shapeCollection;
    
    /**
     * makes an empty ArryList for your Shapes.
     */
    public ShapeCollection(){
        shapeCollection = new ArrayList<>();
    }
    
    /**
     * Copes the contents of an existing Arraylist.
     * @param list 
     */
    public ShapeCollection(ArrayList<Shape> list){
        shapeCollection = new ArrayList<>();
        concat(list);
    }
    
    /**
     * Starts reading from a file containg the shapes in the following format
     * "type of the shape : string" "x coordinate of the shape : double"
     * "y coordinate of the shape : double" "the length of the shapes side : double" 
     * if a line doesn't follow the format the shape in that line will be ignored.
     * @param filename the name of the txt you want to use for the read (eg. "data.txt") 
     * @throws java.io.FileNotFoundException
     * @throws inrectangle.FileIsEmptyException
     */
    public void read(String filename) throws FileNotFoundException, FileIsEmptyException {
        Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
        if (!scan.hasNext()) throw new FileIsEmptyException("missing file");
        while(scan.hasNext()){
            try{
            Scanner line = new Scanner(scan.nextLine());
            String type = line.next();
            if (type.equals("c")|| type.equals("h")||
                    type.equals("s")|| type.equals("t")){
                if (!line.hasNextDouble()) throw new WrongFormatException("wrong format");
                double x = line.nextDouble();
                if (!line.hasNextDouble()) throw new WrongFormatException("wrong format");
                double y = line.nextDouble();
                if (!line.hasNextDouble()) throw new WrongFormatException("wrong format");
                double side = line.nextDouble();
                Shape shape = switch (type) {
                        case "t" -> new Triangle(x,y,side);
                        case "c" -> new Circle(x,y,side);
                        case "s" -> new Square(x,y,side);
                        default -> new Hexagon(x,y,side);
                };
                System.out.println(shape);
                add(shape);
            }else {
                throw new WrongFormatException("wrong format");
            }
            } catch(WrongFormatException e){
                System.out.println("This line had benn written in a wrong format"
                        + " therefore it cannot be read.");
            } catch(NegativeSideException e){
                System.out.println("This line had a negative side value"
                        + " therefore it cannot be read.");
            }
        }
    }
    
    /**
     * Adds all the elements of an exitsting list to our ArrayList. 
     * @param list
     */
    public void concat(ArrayList<Shape> list){
        for (int i = 0; i < list.size(); i++) {
            String c = list.get(i).getType();
            Shape e = switch (c) {
                case "t" -> new Triangle(list.get(i));
                case "c" -> new Circle(list.get(i));
                case "s" -> new Square(list.get(i));
                default -> new Hexagon(list.get(i));
            };
        }
    }
    
    /**
     * adds the Shape to your Colletion
     * @param e 
     */
    public void add(Shape e){
        shapeCollection.add(e);
    }
    
    /**
     * removes a shape from your collection if it exists
     * @param e 
     */
    public void remove(Shape e){
        shapeCollection.remove(e);
    }
    
    /**
     * Calculates which Shape has the biggest inbounding rectangle.
     * @return the Shape that has the bigest inbounding rectangle 
     */
    public Shape getMaxInRectangleArea(){
        if(shapeCollection.isEmpty()){
            return null;
        }
        Shape e = shapeCollection.get(0);
        double max = shapeCollection.get(0).getInRectangleArea();
        for (int i = 1; i < shapeCollection.size(); i++) {
            
            if(shapeCollection.get(i).getInRectangleArea() > max){
                max = shapeCollection.get(i).getInRectangleArea();
                e = shapeCollection.get(i);
            }
        }
        return e;
    }
    
    /**
     * Prints out the properties of the shape which has the largest inbound triangle.
     */
    public void report(){
        if (getMaxInRectangleArea() == null ) System.out.println("The list was empty!");
        else System.out.println("The shape which has the largest inbound rectangle : " +getMaxInRectangleArea());
    }
    
}