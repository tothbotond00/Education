package oracleTower.spell;

import oracleTower.tile.Tile;
import oracleTower.player.Player;

public class Cannon extends Spell {
    public Cannon() {
        super(500, 4, 50);
    }

    public Cannon(int price, int maxCooldown, int damage) {
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
        // if (spellUser.getID() == 1) {
        // cooldownOver = (p1Cooldown == 0);
        // } else {
        // cooldownOver = (p2Cooldown == 0);
        // }
        if (cooldownOver && tile.getBuilding().getOwnerPlayer() == enemy) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * removes the enemies soldier's hp on the tile by this.damage
     */
    @Override
    protected void applyEffect(Tile tile, Player spellUser, Player enemy) {
        if (tile.getBuilding().getOwnerPlayer() == enemy) {
            tile.getBuilding().setHp(tile.getBuilding().getHp() - value);
        }
        // if (spellUser.getID() == 1) {
        // p1Cooldown = maxCooldown;
        // } else {
        // p2Cooldown = maxCooldown;
        // }
    }
}
