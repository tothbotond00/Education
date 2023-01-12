package oracleTower.spell;

import oracleTower.tile.Tile;

import java.util.ArrayList;

import oracleTower.player.Player;
import oracleTower.player.SoldierInterface;

public class Arrows extends Spell {

    public Arrows() {
        super(400, 4, 40);
    }

    public Arrows(int price, int maxCooldown, int damage) {
        super(price, maxCooldown, damage);
    }

    public int getDamage() {
        return value;
    }

    public void setDamage(int damage) {
        this.value = damage;
    }

    @Override
    public boolean canBeUsed(Tile tile, Player spellUser, Player enemy) {
        boolean cooldownOver = true;
        // if(spellUser.getID() == 1){
        //     cooldownOver = (p1Cooldown == 0);
        // }else{
        //     cooldownOver = (p2Cooldown == 0);
        // }
        if(cooldownOver) {
            ArrayList<SoldierInterface> unitsOnTile = tile.getSoldiers();
            boolean isEnemyOnTile = false;
            for (SoldierInterface s : unitsOnTile) {
                if(s.getPosition().equals(tile.getPos()) && s.getOwner() == enemy){
                    isEnemyOnTile = true;
                }
            }
            return isEnemyOnTile;
        }else {
            return false;
        }
    }

    /**
     * removes the enemies soldier's hp on the tile by this.damage
     */
    @Override
    protected void applyEffect(Tile tile, Player spellUser, Player enemy) {
        ArrayList<SoldierInterface> soldiersOnTile = tile.getSoldiers();
        for (SoldierInterface soldier : soldiersOnTile) {
            if(soldier.getOwner() == enemy){
                soldier.setHp(soldier.getHp() - value);
            }
        }
        // if(spellUser.getID() == 1){
        //     p1Cooldown = maxCooldown;
        // }else{
        //     p2Cooldown = maxCooldown;
        // }
    }

}
