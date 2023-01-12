package oracleTower.player;
import oracleTower.modell.Position;

public abstract class SoldierInterface {
    private int price; // soldier's price
    private Player owner; // player who posses the soldier
    private int hp; // soldier's hp
    private int maxHp;
    private int dmg; // soldier's dmg
    private int movementSpeed; //  movementSpeed is an amount that a soldier can move in a round 
    private BuildingInterface target; // soldier attack that
    private Position position; // soldier currently position
    private Position ownerBasePosition; // soldier's starting position

    public SoldierInterface(int price,Player owner, int hp,int dmg,int movementSpeed,BuildingInterface target, Position ownerBasePosition){
        this.price=price;
        this.owner=owner;
        this.hp=hp;
        maxHp = hp;
        this.target=target;
        this.dmg=dmg;
        this.movementSpeed=movementSpeed;
        this.ownerBasePosition=ownerBasePosition; 
        //This needs to be refactored!!!
        this.position =new Position(ownerBasePosition.getX() + 1
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

    public void addHp(int healValue){
        
        if(hp + healValue > maxHp){
            hp = maxHp;
        }else{
            hp += healValue;
        }
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
    
    public int getMaxHp(){
        return maxHp;
    }

    
    public void setPrice(int price){
        this.price=price;
    }

    public void setOwner(Player owner){
        this.owner=owner;
    }

    public void setHp(int hp){
        this.hp=hp;
    }

    public void setDmg(int dmg){
        this.dmg=dmg;
    }

    public void setMovementSpeed(int movementSpeed){
        this.movementSpeed=movementSpeed;
    }

    public void damageHp(int damage){
        this.hp = hp - damage; 
    }


    public void setTarget(BuildingInterface target){
        this.target=target;
    }

    public void setPosition(Position position){
        this.position=position;
    }

    public boolean isDead(){
        return hp<=0;
    }

    @Override
    public String toString() {
        return "Soldier's hp " + getHp();
    }


}
