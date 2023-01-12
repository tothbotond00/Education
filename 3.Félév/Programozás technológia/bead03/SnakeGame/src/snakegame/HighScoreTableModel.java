package snakegame;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class HighScoreTableModel extends AbstractTableModel{
    private final ArrayList<HighScore> highScores;
    private final String[] colName = new String[]{ "Score", "Name"};
    
    /**
     * Extends from an AbstractTableModel, makes a table from the highscores
     * given to it in the parameters.
     * the class overrides the following methods:
     * getRowCount(),getColumnCount(),getColumnName(),getValueAt(),getColumnClass()
     * @param highScores 
     */
    public HighScoreTableModel(ArrayList<HighScore> highScores){
        this.highScores = highScores;
    }

    @Override
    public int getRowCount() { return highScores.size(); }

    @Override
    public int getColumnCount() { return 2; }

    @Override
    public Object getValueAt(int r, int c) {
        HighScore h = highScores.get(r);
        if      (c == 0) return h.getScore();
        return h.getName();
    }

    @Override
    public String getColumnName(int i) { return colName[i]; }    
    
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
