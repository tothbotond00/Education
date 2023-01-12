package oracleTower.spell;

import java.util.ArrayList;
import oracleTower.tile.Tile;
import oracleTower.player.Player;
import oracleTower.player.SoldierInterface;

public class Heal extends Spell {
    public Heal() {
        super(600, 4, 50);
    }

    public Heal(int price, int maxCooldown, int healValue) {
        super(price, maxCooldown, healValue);
    }

    public int getHealValue() {
        return value;
    }

    public void getHealValue(int healValue) {
        this.value = healValue;
    }

    @Override
    public boolean canBeUsed(Tile tile, Player spellUser, Player enemy) {
        boolean cooldownOver = true;
        // if (spellUser.getID() == 1) {
        //     cooldownOver = (p1Cooldown == 0);
        // } else {
        //     cooldownOver = (p2Cooldown == 0);
        // }
        if(cooldownOver) {
            ArrayList<SoldierInterface> unitsOnTile = tile.getSoldiers();
            boolean iFriendlyOnTile = false;
            for (SoldierInterface s : unitsOnTile) {
                if(s.getPosition().equals(tile.getPos()) && s.getOwner() == spellUser){
                    iFriendlyOnTile = true;
                }
            }
            return iFriendlyOnTile;
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
            if (soldier.getOwner() == spellUser) {
                soldier.setHp(soldier.getHp() + value);
            }
        }
        // if (spellUser.getID() == 1) {
        //     p1Cooldown = maxCooldown;
        // } else {
        //     p2Cooldown = maxCooldown;
        // }
    }
}
