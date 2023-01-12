package horsegame;

public class Coordinate {

    private int x;
    private int y;

    /**
     * Initialises a Coordinate IMPORTANT! The coordinate's y axis is
     * inverted!!!
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initalises the Coordiante as a copy of an existing one.
     *
     * @param cor the copy coordinate
     */
    public Coordinate(Coordinate cor) {
        this.x = cor.getX();
        this.y = cor.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
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
        final Coordinate other = (Coordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }

}
