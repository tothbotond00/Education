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
import java.awt.Color;

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
        destroyer1 = ResourceLoader.loadImage("elteCrussader/res/destroyer1.png");
        destroyer2 = ResourceLoader.loadImage("elteCrussader/res/destroyer2.png");
        fighter1 = ResourceLoader.loadImage("elteCrussader/res/fighter1.png");
        fighter2 = ResourceLoader.loadImage("elteCrussader/res/fighter2.png");
        speedy1 = ResourceLoader.loadImage("elteCrussader/res/speedy1.png");
        speedy2 = ResourceLoader.loadImage("elteCrussader/res/speedy2.png");
        support1 = ResourceLoader.loadImage("elteCrussader/res/support1.png");
        support2 = ResourceLoader.loadImage("elteCrussader/res/support2.png");
        damaget1 = ResourceLoader.loadImage("elteCrussader/res/damaget1.png");
        damaget2 = ResourceLoader.loadImage("elteCrussader/res/damaget2.png");
        normalt1 = ResourceLoader.loadImage("elteCrussader/res/normalt1.png");
        normalt2 = ResourceLoader.loadImage("elteCrussader/res/normalt2.png");
        tankt1 = ResourceLoader.loadImage("elteCrussader/res/tankt1.png");
        tankt2 = ResourceLoader.loadImage("elteCrussader/res/tankt2.png");
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
    
    //checks valid position
    public boolean validPos(Position selectedPos){
        return game.validPos(selectedPos);
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
        g.setColor(Color.RED);
        ///THIS PART PAINTS OUT THE COMPONENETS STATICALLY TO THE BOARD
        for (SoldierInterface soldier : game.getPlayer1().getSoldiers()) {
            
            if(soldier instanceof NormalSoldier){
                g.drawImage(normal1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
          
            }
            if(soldier instanceof Fighter){
                g.drawImage(fighter1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(soldier instanceof SpeedyBoi){
                g.drawImage(speedy1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(soldier instanceof Support){
                g.drawImage(support1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(soldier instanceof Destroyer){
                g.drawImage(destroyer1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            g.fillRect(soldier.getPosition().getY()*tile_size+5, soldier.getPosition().getX()*tile_size,(soldier.getHp()/(soldier.getMaxHp()/10))*2+1,3);
            
        }
        for (SoldierInterface soldier : game.getPlayer2().getSoldiers()) {
            if(soldier instanceof NormalSoldier){
                g.drawImage(normal2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
            
            }
            if(soldier instanceof Fighter){
                g.drawImage(fighter2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
               
            }
            if(soldier instanceof SpeedyBoi){
                g.drawImage(speedy2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(soldier instanceof Support){
                g.drawImage(support2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(soldier instanceof Destroyer){
                g.drawImage(destroyer2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            g.fillRect(soldier.getPosition().getY()*tile_size+5, soldier.getPosition().getX()*tile_size,(soldier.getHp()/(soldier.getMaxHp()/10))*2+1,3);
        }
        for (TowerInterface tower : game.getPlayer1().getTowers()) {
            if(tower instanceof NormalTower){
                g.drawImage(normalt1,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(tower instanceof DamageTower){
                g.drawImage(damaget1,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(tower instanceof TankTower){
                g.drawImage(tankt1,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            g.fillRect(tower.getPosition().getY()*tile_size+5, tower.getPosition().getX()*tile_size,(tower.getHp()/(tower.getMaxHP()/10))*2+1,3);
        }
        for (TowerInterface tower : game.getPlayer2().getTowers()) {
            if(tower instanceof NormalTower){
                g.drawImage(normalt2,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(tower instanceof DamageTower){
                g.drawImage(damaget2,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(tower instanceof TankTower){
                g.drawImage(tankt2,tower.getPosition().getY()*tile_size, tower.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            g.fillRect(tower.getPosition().getY()*tile_size+5, tower.getPosition().getX()*tile_size,(tower.getHp()/(tower.getMaxHP()/10))*2+1,3);
        }
        for (Mine mine : game.getPlayer1().getMines()) {
            g.drawImage(mine1,mine.getPosition().getY()*tile_size, mine.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.fillRect(mine.getPosition().getY()*tile_size+5, mine.getPosition().getX()*tile_size,(mine.getHp()/(mine.getMaxHP()/10))*2+1,3);
        }
        for (Mine mine : game.getPlayer2().getMines()) {
            g.drawImage(mine2,mine.getPosition().getY()*tile_size, mine.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.fillRect(mine.getPosition().getY()*tile_size+5, mine.getPosition().getX()*tile_size,(mine.getHp()/(mine.getMaxHP()/10))*2+1,3);
        }
        g.setColor(Color.WHITE);
        g.drawImage(base1,game.getPlayer1().getBase().getPosition().getY()*tile_size, game.getPlayer1().getBase().getPosition().getX()*tile_size,tile_size, tile_size,null);
        g.fillRect(game.getPlayer1().getBase().getPosition().getY()*tile_size +5, game.getPlayer1().getBase().getPosition().getX()*tile_size,
                (game.getPlayer1().getBase().getHp()/(game.getPlayer1().getBase().getMaxHP()/10))*2 +1,3);
        g.drawImage(base2,game.getPlayer2().getBase().getPosition().getY()*tile_size, game.getPlayer2().getBase().getPosition().getX()*tile_size,tile_size, tile_size,null);
        g.fillRect(game.getPlayer2().getBase().getPosition().getY()*tile_size +5, game.getPlayer2().getBase().getPosition().getX()*tile_size,
                (game.getPlayer2().getBase().getHp()/(game.getPlayer2().getBase().getMaxHP()/10))*2 +1,3);
        
    }
        
}