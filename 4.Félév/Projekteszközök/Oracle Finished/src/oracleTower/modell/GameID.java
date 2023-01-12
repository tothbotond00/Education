package oracleTower.modell;

import java.util.Objects;

/**
 * This class sets up the GameIDs, used to load a map based on size and id
 */
public class GameID {
    public final String size;
    public final int    id;
    /**
    * Constructor of a game id
    * @param size is the size of the map
    * @param id is the id of the map of a given size
    */
    public GameID(String size, int id) {
        this.size = size;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.size);
        hash = 29 * hash + this.id;
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
        final GameID other = (GameID) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        return true;
    }
    
    
}