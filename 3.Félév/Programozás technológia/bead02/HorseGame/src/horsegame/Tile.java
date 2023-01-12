package horsegame;

enum Paint {
    BLACK,
    WHITE,
    GRAY,
}

public class Tile {

    private Paint color;
    private boolean onHorse;

    /**
     * Initalises the Tile whitout a horse and sets it's color to GRAY.
     */
    public Tile() {
        color = Paint.GRAY;
        onHorse = false;
    }

    /**
     * Initalises the Tile as a copy of an existing one.
     *
     * @param tile the copy tile
     */
    public Tile(Tile tile) {
        color = tile.getColor();
        onHorse = tile.getOnHorse();
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public boolean getOnHorse() {
        return onHorse;
    }

    /**
     * Puts a horse on the tile. (Sets onHorse true)
     */
    public void putHorse() {
        onHorse = true;
    }

    /**
     * Removes a horse on the tile. (Sets onHorse false)
     */
    public void removeHorse() {
        onHorse = false;
    }

}
