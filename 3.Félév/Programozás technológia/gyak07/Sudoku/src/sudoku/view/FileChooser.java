package sudoku.view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.Timer;

public class FileChooser extends JFileChooser {

    public FileChooser(String title) {
        setDialogTitle(title);
    }

    public File open() {        
        if (showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        } else {
            return null;
        }        
    }
    
}
