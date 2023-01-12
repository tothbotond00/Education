/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inrectangle;

/**
 *
 * @author Botond
 */
public class Triangle extends Shape {
    
    public Triangle(double x, double y, double side)throws NegativeSideException {
        super(x,y,side);
        double ma = (Math.sqrt(3.0)* side) /2;
        rec = side*ma;
    }
    
    public Triangle(Shape e){
        super(e);
        double ma = (Math.sqrt(3.0)* side) /2;
        rec = side*ma;
    }
    
     /**
     * Calculates the inbounding rectangles area of the triangle.
     * T = a * ( (√3 * a) / 2 ) 
     * @return the area of the inbounding rectangle
     */
    @Override
    public double getInRectangleArea(){
        return rec;
    }
    
    @Override
    public String getType(){
        return "t";
    }
    
}