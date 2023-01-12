
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public abstract class Animal {
    protected String type;
    protected String name;
    protected int weight;
    protected int feedNum;
    protected ArrayList<Integer> feeds;

    public Animal(String type, String name, int weight, int feedNum) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.feedNum = feedNum;
        feeds = new ArrayList<>();
    }
    
    public void addFeed (Integer f){
        feeds.add(f);
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getFeedNum() {
        return feedNum;
    }
    
    public boolean moreThanOneKilo(){
        int sum = 0;
        for (int i = 0; i < feedNum; i++) {
            sum += feeds.get(i);
        }
        return sum > 100;
    }
    
    public abstract boolean isSkinny();
}
