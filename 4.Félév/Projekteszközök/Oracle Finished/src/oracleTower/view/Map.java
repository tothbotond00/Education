/**
 * This is the painting class of the GUI
 */
package oracleTower.view;
/**
 *
 * @author Vajda Marián JG3IH8
 * This class sets up the look and simulates the map on the JFrame.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JPanel;
import oracleTower.modell.Game;
import oracleTower.modell.Position;
import oracleTower.modell.ResourceLoader;
import oracleTower.player.DamageTower;
import oracleTower.player.Destroyer;
import oracleTower.player.Fighter;
import oracleTower.player.Mage;
import oracleTower.player.Mine;
import oracleTower.player.NormalSoldier;
import oracleTower.player.NormalTower;
import oracleTower.player.SoldierInterface;
import oracleTower.player.Support;
import oracleTower.player.TankTower;
import oracleTower.player.TowerInterface;

public class Map extends JPanel {
    private Game game;
    private Image base1, mine1, fighter1, support1, destroyer1, mage1, normal1, tankt1, normalt1, damaget1,
            base2, mine2, fighter2, support2, destroyer2, mage2, normal2, tankt2, normalt2, damaget2,
            swamp, normal, mountain,
            arrows, cannon, freeze, heal;
    private final int tile_size = 30;

    // @params in images are the images for all elements on the map
    
    /**
    * Constructor of a board
    * @param g is the game
    * @throws java.io.IOException
    */
    public Map(Game g) throws IOException{
        game = g;
        mountain = ResourceLoader.loadImage("oracleTower/res/mountain.png");
        swamp = ResourceLoader.loadImage("oracleTower/res/swamp.png");
        normal = ResourceLoader.loadImage("oracleTower/res/normal.png");
        base1 = ResourceLoader.loadImage("oracleTower/res/base1.png");
        base2 = ResourceLoader.loadImage("oracleTower/res/base2.png");
        normal1 = ResourceLoader.loadImage("oracleTower/res/normal1.png");
        normal2 = ResourceLoader.loadImage("oracleTower/res/normal2.png");
        destroyer1 = ResourceLoader.loadImage("oracleTower/res/destroyer1.png");
        destroyer2 = ResourceLoader.loadImage("oracleTower/res/destroyer2.png");
        fighter1 = ResourceLoader.loadImage("oracleTower/res/fighter1.png");
        fighter2 = ResourceLoader.loadImage("oracleTower/res/fighter2.png");
        mage1 = ResourceLoader.loadImage("oracleTower/res/mage1.png");
        mage2 = ResourceLoader.loadImage("oracleTower/res/mage2.png");
        support1 = ResourceLoader.loadImage("oracleTower/res/support1.png");
        support2 = ResourceLoader.loadImage("oracleTower/res/support2.png");
        damaget1 = ResourceLoader.loadImage("oracleTower/res/damaget1.png");
        damaget2 = ResourceLoader.loadImage("oracleTower/res/damaget2.png");
        normalt1 = ResourceLoader.loadImage("oracleTower/res/normalt1.png");
        normalt2 = ResourceLoader.loadImage("oracleTower/res/normalt2.png");
        tankt1 = ResourceLoader.loadImage("oracleTower/res/tankt1.png");
        tankt2 = ResourceLoader.loadImage("oracleTower/res/tankt2.png");
        mine1 = ResourceLoader.loadImage("oracleTower/res/mine1.png");
        mine2 = ResourceLoader.loadImage("oracleTower/res/mine2.png");
        
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
                     refresh();
                    }else {
                    switch (game.getSelected()) {
                        case "arrows spell":
                            game.getPlayer().buySpell(g.getArrows(), game.getGameMap().getTile(selectedPos), g.getEnemyPlayer());
                            break;
                        case "cannon spell":
                            game.getPlayer().buySpell(g.getCannon(), game.getGameMap().getTile(selectedPos), g.getEnemyPlayer());
                            break;
                        case "freeze spell":

                            break;
                        case "heal spell":
                            game.getPlayer().buySpell(g.getHeal(), game.getGameMap().getTile(selectedPos), g.getPlayer());
                            break;
                        default:
                            refresh();
                            break;
                    }
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
        return game.validPos(selectedPos);
    }
    
    
    /**
    * Refreshes the board when the state of the game is changed
     * @return  returns true if the refresh was successful
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
    * paints out the map and the elements on it
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!game.isMapLoaded()) return;
        Graphics2D gr = (Graphics2D)g;
        int w = game.getCols();
        int h = game.getRows();
        //This part paints out the map itself 
        for(int j = 0; j < w; j++) {
            for (int i = 0; i < h; i++){
                switch (game.getGameMap().getTile(new Position(i,j)).getType().toString()) {
                        case "M" : g.drawImage(mountain,j*tile_size, i*tile_size,tile_size, tile_size,null); break;
                        case "S" : g.drawImage(swamp,j*tile_size, i*tile_size,tile_size,tile_size,null); break;
                        default : g.drawImage(normal,j*tile_size, i*tile_size,tile_size,tile_size,null); break;
                }
            }
        }
        
        g.setColor(Color.RED);
        ///This part paint out the entities of the game (towers, soldiers...)
        for (SoldierInterface soldier : game.getPlayer1().getSoldiers()) {
            
            if(soldier instanceof NormalSoldier){
                g.drawImage(normal1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
          
            }
            if(soldier instanceof Fighter){
                g.drawImage(fighter1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
            }
            if(soldier instanceof Mage){
                g.drawImage(mage1,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
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
            if(soldier instanceof Mage){
                g.drawImage(mage2,soldier.getPosition().getY()*tile_size, soldier.getPosition().getX()*tile_size,tile_size, tile_size,null);
                
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
            g.fillRect(tower.getPosition().getY()*tile_size+5, tower.getPosition().getX()*tile_size,(tower.getHp()/(tower.getMaxHp()/10))*2+1,3);
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
            g.fillRect(tower.getPosition().getY()*tile_size+5, tower.getPosition().getX()*tile_size,(tower.getHp()/(tower.getMaxHp()/10))*2+1,3);
        }
        
        for (Mine mine : game.getPlayer1().getMines()) {
            g.drawImage(mine1,mine.getPosition().getY()*tile_size, mine.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.fillRect(mine.getPosition().getY()*tile_size+5, mine.getPosition().getX()*tile_size,(mine.getHp()/(mine.getMaxHp()/10))*2+1,3);
        }
        for (Mine mine : game.getPlayer2().getMines()) {
            g.drawImage(mine2,mine.getPosition().getY()*tile_size, mine.getPosition().getX()*tile_size,tile_size, tile_size,null);
            g.fillRect(mine.getPosition().getY()*tile_size+5, mine.getPosition().getX()*tile_size,(mine.getHp()/(mine.getMaxHp()/10))*2+1,3);
        }
        g.setColor(Color.WHITE);
        
        g.drawImage(base1,game.getPlayer1().getBase().getPosition().getY()*tile_size, game.getPlayer1().getBase().getPosition().getX()*tile_size,tile_size, tile_size,null);
        g.fillRect(game.getPlayer1().getBase().getPosition().getY()*tile_size +5, game.getPlayer1().getBase().getPosition().getX()*tile_size,
                (game.getPlayer1().getBase().getHp()/(game.getPlayer1().getBase().getMaxHp()/10))*2 +1,3);
        
        g.drawImage(base2,game.getPlayer2().getBase().getPosition().getY()*tile_size, game.getPlayer2().getBase().getPosition().getX()*tile_size,tile_size, tile_size,null);
        g.fillRect(game.getPlayer2().getBase().getPosition().getY()*tile_size +5, game.getPlayer2().getBase().getPosition().getX()*tile_size,
                (game.getPlayer2().getBase().getHp()/(game.getPlayer2().getBase().getMaxHp()/10))*2 +1,3);
        
    }
        
}
