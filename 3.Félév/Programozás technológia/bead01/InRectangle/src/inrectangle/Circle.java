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
public class Circle extends Shape {
    
    public Circle(double x, double y, double side)throws NegativeSideException {
        super(x,y,side);
         rec = (side + side) * (side + side);
    }
    
    public Circle(Shape e){
        super(e);
        rec = (side + side) * (side + side);
    }
    
    /**
     * Calculates the inbounding rectangles area of the circle.
     * T = (a+a) * (a+a)
     * @return the area of the inbounding rectangle
     */
    @Override
    public double getInRectangleArea(){
        return rec;
    }
    
    @Override
    public String getType(){
        return "c";
    }
    
}