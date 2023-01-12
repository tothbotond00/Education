package flashcards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FlashcardsModel {
    private final ArrayList<Card> cards;
    private int cardNumber;
    private boolean showQuestion;
    private int score;
    
    public FlashcardsModel(){
        cards = new ArrayList();
    }
    
    public void reset(){
        cardNumber = 0;
        score = 0;
        showQuestion = true;
    }
    
    public boolean isEmpty(){
        return cardNum() == 0;
    }
    
    public int cardNum(){
        return cards.size();
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getScore() {
        return score;
    }
    
    public void invertQuestion(){
        showQuestion = !showQuestion;
    }
    
    public void addCard(String question, String answer) {
        cards.add(new Card(question, answer));
    }
    
    public Card getCard(int i){
        if (i >= cardNum() || i < 0) return null;
        else return cards.get(i);
    }
    
    public Card getCurrentCard(){
        return getCard(cardNumber);
    }
    
    public void read(File file) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] qa = line.split("~");
            if (qa.length == 2) {
                addCard(qa[0], qa[1]);
            }
        }
    }
    
    public void nextCard(boolean incScore) {
        if (!isEmpty() && cardNumber < cards.size()) {
            if (incScore) {
                score++;
            }
            cardNumber++;
            showQuestion = true;
        }
    }
    
    public String currentCardText() {
        if (!isEmpty() && getCardNumber() < cardNum()) {
            if (showQuestion) {
                return getCurrentCard().getQuestion();
            } else {
                return getCurrentCard().getAnswer();
            }
        } else if (!isEmpty()) {
            return "The End";
        }
        return "Please open a cards file...";
    }
    
}