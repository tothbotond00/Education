/**
 * This package contains the spells for the game.
 */
package oracleTower.spell;

import oracleTower.tile.Tile;
import oracleTower.player.Player;

public abstract class Spell {
    protected int price;
    /**
     * The currentCooldown will be set to this value,
     * when the spell is used
     */
    protected int maxCooldown;
    protected int value;
    /**
     * Spell can be used if it reaches zero
     */
    protected int p1Cooldown = 0;
    protected int p2Cooldown = 0;

    public Spell(int price, int maxCooldown, int value) {
        this.price = price;
        this.maxCooldown = maxCooldown;
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    /**
     * Sohuld be called every turn, reduces the currentCooldown until
     * it reaches zero, and the spell can be used again
     */
    public void nextTurnAction() {
        if (p1Cooldown > 0) {
            p1Cooldown--;
        }
        if (p2Cooldown > 0) {
            p2Cooldown--;
        }
    }

    public int getP1Cooldown() {
        return p1Cooldown;
    }

    public int getP2Cooldown() {
        return p2Cooldown;
    }

    /**
     * @param tile            tile on which the player wants to use the spell
     * @param spellUser       the player that is using the spell
     * @param playerToApplyOn the player to apply the spell on (same in heal, enemy
     *                        in others)
     * @return true if the cooldown reached 0 and
     *         its a valid tile for the player using the spell
     */ 
    public abstract boolean canBeUsed(Tile tile, Player spellUser, Player playerToApplyOn);

    /**
     * Apllies the spell's effect on the selected soldier or building
     * 
     * @param tile The selected tile to apply the spell on
     */
    public void use(Tile tile, Player spellUser, Player enemy) {
        applyEffect(tile, spellUser, enemy);
    }

    /**
     * Apllies the spells effect on the selected soldier or building if possible
     * 
     * @param tile The selected tile to apply the spell on
     */
    protected abstract void applyEffect(Tile tile, Player spellUser, Player enemy);
}
