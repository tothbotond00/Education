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
public abstract class Shape {
    protected double x,y,side,rec;
    
    /**
     * Constructs the Shape by the following parameters
     * @param x coordinate of the x axis
     * @param y coordinate of the y axis
     * @param side the length of the side. (Cannot be lesser nor equals 0)
     * @throws NegativeSideException
     */
    public Shape(double x, double y, double side) throws NegativeSideException{
        if (side <= 0){
            throw new NegativeSideException("negative side value");
        }
        this.x = x;
        this.y = y;
        this.side = side;
        this.rec = 0;
    }
    
    /**
     * Constructs a Shape from an existing Shape
     * @param e a Shape 
     */
    public Shape(Shape e){
        this.x = e.getX();
        this.y = e.getY();
        this.side = e.getSide();
        this.rec = 0;
    }
    
    /**
     * Calculates the inbound rectangle of the shape.
     * @return the area of the inbound rectangle
     */
    public abstract double getInRectangleArea();
    
    /**
     * Returns the type of the actual shape:
     * 'c' for circle, 's' for square, 'h' for hexagon and 't' triangle
     * @return the type of the shape 
     */
    public abstract String getType();
    
    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSide() {
        return side;
    }
    
    //hashCode and equals (wasn't neccessary)
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.side) ^ (Double.doubleToLongBits(this.side) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Shape other = (Shape) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return Double.doubleToLongBits(this.side) == Double.doubleToLongBits(other.side);
    }
    
    //toString
    @Override
    public String toString() {
        return getType() + " {(" + x + " ; " + y + ") ; side=" + side  + '}';
    }
    
}