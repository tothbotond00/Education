package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JPanel;
import model.Game;
import model.LevelItem;
import model.Position;
import res.ResourceLoader;

public class Board extends JPanel {
    private Game game;
    private final Image box, box_in_place, destination, player, wall, empty;
    private double scale;
    private int scaled_size;
    private final int tile_size = 32;
    
    public Board(Game g) throws IOException{
        game = g;
        scale = 1.0;
        scaled_size = (int)(scale * tile_size);
        box = ResourceLoader.loadImage("res/box.png");
        box_in_place = ResourceLoader.loadImage("res/box_in_place.png");
        destination = ResourceLoader.loadImage("res/destination.png");
        player = ResourceLoader.loadImage("res/player.png");
        wall = ResourceLoader.loadImage("res/wall.png");
        empty = ResourceLoader.loadImage("res/empty.png");
    }
    
    public boolean setScale(double scale){
        this.scale = scale;
        scaled_size = (int)(scale * tile_size);
        return refresh();
    }
    
    public boolean refresh(){
        if (!game.isLevelLoaded()) return false;
        Dimension dim = new Dimension(game.getLevelCols() * scaled_size, game.getLevelRows() * scaled_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        repaint();
        return true;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (!game.isLevelLoaded()) return;
        Graphics2D gr = (Graphics2D)g;
        int w = game.getLevelCols();
        int h = game.getLevelRows();
        Position p = game.getPlayerPos();
        for (int y = 0; y < h; y++){
            for (int x = 0; x < w; x++){
                Image img = null;
                LevelItem li = game.getItem(y, x);
                switch (li){
                    case BOX: img = box; break;
                    case BOX_IN_PLACE: img = box_in_place; break;
                    case DESTINATION: img = destination; break;
                    case WALL: img = wall; break;
                    case EMPTY: img = empty; break;
                }
                if (p.x == x && p.y == y) img = player;
                if (img == null) continue;
                gr.drawImage(img, x * scaled_size, y * scaled_size, scaled_size, scaled_size, null);
            }
        }
    }
    
}
