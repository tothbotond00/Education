package elteCrussader.player;

import elteCrussader.modell.Position;


public abstract class SoldierInterface {
    private int price; // soldier's price
    private Player owner; // player who posses the soldier
    private int hp; // soldier's hp
    private int maxHp; // soldier's max hp
    private int dmg; // soldier's dmg
    private int movementSpeed; //  movementSpeed is an amount that a soldier can move in a round 
    private BuildingInterface target; // soldier attack that
    private Position position; // soldier currently position
    private Position ownerBasePosition; // soldier's starting position

    public SoldierInterface(int price,Player owner, int hp,int dmg,int movementSpeed,BuildingInterface target, Position ownerBasePosition){
        this.price=price;
        this.owner=owner;
        this.hp=hp;
        this.maxHp=hp;
        this.target=target;
        this.dmg=dmg;
        this.movementSpeed=movementSpeed;
        this.ownerBasePosition=ownerBasePosition;
        this.position=new Position(ownerBasePosition.getX() + 1
                ,ownerBasePosition.getY() + 1);
    }

    public void move(Position pos){
        this.position = pos;
    }

    public int getPrice(){
        return price;
    }

    public Player getOwner(){
        return owner;
    }

    public int getHp(){
        return hp;
    }
    
    public int getMaxHp(){
        return maxHp;
    }

    public int getDmg(){
        return dmg;
    }

    public int getMovementSpeed(){
        return movementSpeed;
    }

    public BuildingInterface getTarget(){
        return target;
    }

    public Position getPosition(){
        return position;
    }
    
    public boolean isDead(){
        return hp <= 0;
    }

    public void setTarget(BuildingInterface target){
        this.target=target;
    }

    public void setPosition(Position position){
        this.position=position;
    }
    
    public void damageHp(int dmg){
        if (hp - dmg <= maxHp){
            this.hp = hp - dmg; 
        }
    }

    @Override
    public String toString() {
        return "Soldier's hp " + getHp();
    }

}