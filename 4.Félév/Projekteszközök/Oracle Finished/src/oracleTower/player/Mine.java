package oracleTower.player;

import oracleTower.modell.Position;

public class Mine extends BuildingInterface{
    private int produce;
    private int price;

    public Mine(Position position,Player ownerPlayer){
        super(200,position,ownerPlayer);
        produce=200;
        price=1500;
    }

    public void setProduce(int produce){
        this.produce=produce;
    }

    public int getProduce(){
        return produce;
    }

    public void setPrice(int price){
        this.price=price;
    }

    public int getPrice(){
        return price;
    }

}

