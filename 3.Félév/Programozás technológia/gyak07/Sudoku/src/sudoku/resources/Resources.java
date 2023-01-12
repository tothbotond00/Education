package sudoku.resources;

import java.io.File;
import java.net.URISyntaxException;

public class Resources {
    
    public static final String[] EASY_GAMES
            = {"easy0.txt", "easy1.txt", "easy2.txt"};
    public static final String[] MEDIUM_GAMES
            = {"medium0.txt", "medium1.txt", "medium2.txt"};
    public static final String[] HARD_GAMES
            = {"hard0.txt", "hard1.txt", "hard2.txt"};

    public static final String[] EASY_SOLUTIONS
            = {"easy0_solution.txt", "easy1_solution.txt", "easy2_solution.txt"};
    public static final String[] MEDIUM_SOLUTIONS
            = {"medium0_solution.txt", "medium1_solution.txt", "medium2_solution.txt"};
    public static final String[] HARD_SOLUTIONS
            = {"hard0_solution.txt", "hard1_solution.txt", "hard2_solution.txt"};
    
    private Resources() {
    }
    
    public static File getResource(String fileName) throws URISyntaxException {
        return new File(Resources.class.getResource(fileName).toURI());
    }
    
}
