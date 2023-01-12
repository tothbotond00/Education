package snakegame;

import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HighScoreGUI {
    private final JFrame frame;
    
    /**
     * Shows the 10 best player in a table.
     */
    public HighScoreGUI(){
        frame = new JFrame("HighScores");
        
        URL url = HighScoreGUI.class.getResource("res\\snakefaceu.png");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        try{
        HighScores highScores = new HighScores(10);
        JTable table = new JTable(new HighScoreTableModel(highScores.getHighScores()));
        table.setFillsViewportHeight(true);
        
        TableRowSorter<TableModel> sorter =
                new TableRowSorter<>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
        
        frame.add(new JScrollPane(table));
        
        }catch(SQLException ex){
                    Logger.getLogger(PopUpGUI.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        frame.pack();
        frame.setSize(400,225);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }
    }
}
