package elteCrussader.view;
/**
 *
 * @author Vajda Marián JG3IH8
 * This class sets up the look and simulates the board on the JFRAME.
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JPanel;
import elteCrussader.modell.*;
import elteCrussader.player.*;

public class Map extends JPanel {
    private Game game;
    private Image base1, mine1, fighter1, support1, destroyer1, speedy1, normal1, tankt1, normalt1, damaget1,
            base2, mine2, fighter2, support2, destroyer2, speedy2, normal2, tankt2, normalt2, damaget2,
            swamp, normal, mountain;
    private final int tile_size = 30;
    //private Position selectedPos = new Position(-1,-1);

    /**
    * Constructor of a board
    * @param g is the game
     * @throws java.io.IOException
    * @params in images are the images for all elements on the map
    */
    public Map(Game g) throws IOException{
        game = g;
        mountain = ResourceLoader.loadImage("elteCrussader/res/mountain.png");
        swamp = ResourceLoader.loadImage("elteCrussader/res/swamp.png");
        normal = ResourceLoader.loadImage("elteCrussader/res/normal.png");
        base1 = ResourceLoader.loadImage("elteCrussader/res/base1.png");
        base2 = ResourceLoader.loadImage("elteCrussader/res/base2.png");
        normal1 = ResourceLoader.loadImage("elteCrussader/res/normal1.png");
        normal2 = ResourceLoader.loadImage("elteCrussader/res/normal2.png");
        normalt1 = ResourceLoader.loadImage("elteCrussader/res/normalt1.png");
        normalt2 = ResourceLoader.loadImage("elteCrussader/res/normalt2.png");
        mine1 = ResourceLoader.loadImage("elteCrussader/res/mine1.png");
        mine2 = ResourceLoader.loadImage("elteCrussader/res/mine2.png");
        
        addMouseListener(new MouseListener(){
            @Override
                public void mouseClicked(MouseEvent e) {                       
                      
                      Position selectedPos = new Position(e.getY()/tile_size,e.getX()/tile_size);
                    
                      
                      if(validPos(selectedPos)){
                          
                        switch (game.getSelected()) {
                              case "normal tower":
                                  TowerInterface building = new NormalTower(selectedPos,game.getPlayer());                                  
                                  if (game.getPlayer().buyTower(building))
                                      game.getGameMap().getTile(selectedPos).putBuilding(building);
                                  refresh();
                                  break;
                              case "tank tower":
                                  TowerInterface building2 = new TankTower(selectedPos,game.getPlayer());
                                  if(game.getPlayer().buyTower(building2))
                                      game.getGameMap().getTile(selectedPos).putBuilding(building2);
                                  refresh();
                                  break;
                              case "damage tower":
                                  TowerInterface building3 = new DamageTower(selectedPos,game.getPlayer());
                                  if(game.getPlayer().buyTower(building3))
                                      game.getGameMap().getTile(selectedPos).putBuilding(building3);
                                  refresh();
                                  break;
                              case "mine":
                                  Mine building4 = new Mine(selectedPos,game.getPlayer());
                                  if(game.getPlayer().buyMine(building4))
                                      game.getGameMap().getTile(selectedPos).putBuilding(building4);
                                  refresh();
                                  break;
                             default:
                                 refresh();
                                 break;
                        }
                     //writePlayer(currentPlayer);
                     refresh();
                    }
                    
                }  
                public void mouseEntered(MouseEvent e) {}  
                public void mouseExited(MouseEvent e) {}  
                public void mousePressed(MouseEvent e) {}  
                public void mouseReleased(MouseEvent e) {} 
        });
        
    }
     
    public boolean validPos(Position selectedPos){
        if (game.getGameMap().getTile(selectedPos).getType().toString() == "N" && !(game.getGameMap().getTile(selectedPos).isBuilding())
            && !(game.getGameMap().getTile(selectedPos).isSoldier())
            && selectedPos.getX() != game.getPlayer1().getBase().getPosition().getX() + 1
            && selectedPos.getY() != game.getPlayer1().getBase().getPosition().getY() + 1
            && selectedPos.getX() != game.getPlayer2().getBase().getPosition().getX() + 1
            && selectedPos.getY() != game.getPlayer2().getBase().getPosition().getY() + 1
            && game.getGameMap().runDijsktraBaseCheck(game.getPlayer1().getBase().getPosition(), game.getPlayer2().getBase().getPosition(), selectedPos)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
    * Refreshes the board when gamestate is changed
     * @return  returns true if the referesh was succesful
    */
    public boolean refresh(){
        if (!game.isMapLoaded()) return false;
        Dimension dim = new Dimension(game.getGameMap().getCOLS() * tile_size, game.getGameMap().getROWS() * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        repaint();
        return true;
    }
    
    /**
    * paints out the board
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!game.isMapLoaded()) return;
        Graphics2D gr = (Graphics2D)g;
        int w = game.getCols();
        int h = game.getRows();
        for(int j = 0; j < w; j++) {
            for (int i = 0; i < h; i++){
                switch (game.getGameMap().getTile(new Position(i,j)).getType().toString()) {
                        case "M" -> g.drawImage(mountain,j*tile_size, i*tile_size,tile_size, tile_size,null);
                        case "S" -> g.drawImage(swamp,j*tile_size, i*tile_size,tile_size,tile_size,null);
                        default -> g.drawImage(normal,j*tile_size, i*tile_size,tile_size,tile_size,null);
                }
            }
        }

        ///THIS PART PAINTS OUT THE COMPONENETS STATICALLY
        for (SoldierInterface soldier : game.getPlayer1().getSoldiers()) {
            g.drawImage(normal1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.drawString(String.valueOf(soldier.getHp()),soldier.getPosition().getY()*tile_size+5, soldier.getPosition().getX()*tile_size);
        }
        for (SoldierInterface soldier : game.getPlayer2().getSoldiers()) {
            g.drawImage(normal2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.drawString(String.valueOf(soldier.getHp()),soldier.getPosition().getY()*tile_size+5, soldier.getPosition().getX()*tile_size);
        }
        for (TowerInterface tower : game.getPlayer1().getTowers()) {
            g.drawImage(normalt1,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.drawString(String.valueOf(tower.getHp()),tower.getPosition().getY()*tile_size+5, tower.getPosition().getX()*tile_size);
        }
        for (TowerInterface tower : game.getPlayer2().getTowers()) {
            g.drawImage(normalt2,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.drawString(String.valueOf(tower.getHp()),tower.getPosition().getY()*tile_size+5, tower.getPosition().getX()*tile_size);
            
        }
        for (Mine mine : game.getPlayer1().getMines()) {
            g.drawImage(mine1,mine.getPosition().getY()*tile_size, mine.getPosition().getX()*tile_size,tile_size, tile_size,null);
            game.getGameMap().getTile(new Position(mine.getPosition().getX(),mine.getPosition().getY()))
                    .putBuilding(mine);
            g.drawString(String.valueOf(mine.getHp()),mine.getPosition().getY()*tile_size+5, mine.getPosition().getX()*tile_size);
            
        }
        for (Mine mine : game.getPlayer2().getMines()) {
            g.drawImage(mine2,mine.getPosition().getY()*tile_size, mine.getPosition().getX()*tile_size,tile_size, tile_size,null);
            game.getGameMap().getTile(new Position(mine.getPosition().getX(),mine.getPosition().getY()))
                    .putBuilding(mine);
            g.drawString(String.valueOf(mine.getHp()),mine.getPosition().getY()*tile_size+5, mine.getPosition().getX()*tile_size);
            
        }
        g.drawImage(base1,game.getPlayer1().getBase().getPosition().getY()*tile_size, game.getPlayer1().getBase().getPosition().getX()*tile_size,tile_size, tile_size,null);
        g.drawString(String.valueOf(game.getPlayer1().getBase().getHp()),game.getPlayer1().getBase().getPosition().getY()*tile_size+5, game.getPlayer1().getBase().getPosition().getX()*tile_size);
        g.drawImage(base2,game.getPlayer2().getBase().getPosition().getY()*tile_size, game.getPlayer2().getBase().getPosition().getX()*tile_size,tile_size, tile_size,null);
        g.drawString(String.valueOf(game.getPlayer2().getBase().getHp()),game.getPlayer2().getBase().getPosition().getY()*tile_size+5, game.getPlayer2().getBase().getPosition().getX()*tile_size);
        
        
    }
        
}