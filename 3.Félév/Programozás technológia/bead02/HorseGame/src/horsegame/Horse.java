package horsegame;

import java.util.ArrayList;
import java.util.Objects;

public class Horse {

    private Coordinate cor;
    private Paint color;
    private int enviroment;

    /**
     * Initilases the Horse with the following parameters.
     *
     * @param x the x coordinate of the starting place of the horse
     * @param y the y coordinate of the starting place of the horse
     * @param color the color of the horse
     * @param enviroment the size of the table with the horse on
     */
    public Horse(int x, int y, Paint color, int enviroment) {
        cor = new Coordinate(x, y);
        this.color = color;
        this.enviroment = enviroment;
    }

    /**
     * Initalises the Horse as a copy of an existing one.
     *
     * @param horse
     */
    public Horse(Horse horse) {
        this.cor = new Coordinate(horse.getCor());
        this.color = horse.getColor();
        this.enviroment = horse.enviroment;
    }

    public void change(Horse horse) {
        this.cor = new Coordinate(horse.getCor());
        this.color = horse.getColor();
        this.enviroment = horse.enviroment;
    }

    public Coordinate getCor() {
        return cor;
    }

    /**
     * Changes the coordinate of the horse
     *
     * @param x the x coordinate of the target
     * @param y the y coordinate of the target
     */
    public void go(int x, int y) {
        cor.setXY(x, y);
    }

    public Paint getColor() {
        return color;
    }

    /**
     * Calculates the possible moves for the horse depending on the horses
     * enviroment.
     *
     * @return ArrayList containing the possible Coordinates.
     */
    public ArrayList<Coordinate> moves() {
        ArrayList<Coordinate> ar = new ArrayList<>();
        if (cor.getX() + 1 < enviroment && cor.getY() + 2 < enviroment) {
            ar.add(new Coordinate(cor.getX() + 1, cor.getY() + 2));
        }
        if (cor.getX() + 2 < enviroment && cor.getY() + 1 < enviroment) {
            ar.add(new Coordinate(cor.getX() + 2, cor.getY() + 1));
        }
        if (cor.getX() + 2 < enviroment && cor.getY() - 1 >= 0) {
            ar.add(new Coordinate(cor.getX() + 2, cor.getY() - 1));
        }
        if (cor.getX() + 1 < enviroment && cor.getY() - 2 >= 0) {
            ar.add(new Coordinate(cor.getX() + 1, cor.getY() - 2));
        }
        if (cor.getX() - 1 >= 0 && cor.getY() + 2 < enviroment) {
            ar.add(new Coordinate(cor.getX() - 1, cor.getY() + 2));
        }
        if (cor.getX() - 2 >= 0 && cor.getY() + 1 < enviroment) {
            ar.add(new Coordinate(cor.getX() - 2, cor.getY() + 1));
        }
        if (cor.getX() - 2 >= 0 && cor.getY() - 1 >= 0) {
            ar.add(new Coordinate(cor.getX() - 2, cor.getY() - 1));
        }
        if (cor.getX() - 1 >= 0 && cor.getY() - 2 >= 0) {
            ar.add(new Coordinate(cor.getX() - 1, cor.getY() - 2));
        }
        return ar;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.cor);
        hash = 47 * hash + Objects.hashCode(this.color);
        hash = 47 * hash + this.enviroment;
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
        final Horse other = (Horse) obj;
        if (this.enviroment != other.enviroment) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Horse{" + "cor=" + cor + ", color=" + color + ", enviroment=" + enviroment + '}';
    }

}
