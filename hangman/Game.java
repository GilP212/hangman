package hangman;

/**
 *
 * @author Gil
 */
public class Game {
    
    private final ChosenWord word; 
    private final Hangman frmHangman;
    
    public Game(Hangman frmHangman){
        this.frmHangman = frmHangman;
        this.word = new ChosenWord();
    }
    
    
    public boolean guessLetter(char guess){
        boolean result;
        
        if(!Character.isAlphabetic(guess))//checks if char is a letter
            return false;
        
        result = word.isCorrectGuess(Character.toLowerCase(guess));
        
        if(!result)
            frmHangman.addToHangman();
        
        frmHangman.updateStatus();
        
        return result;
    }
    
    
    public boolean isWon(){
        return (word.getGuessedWord().indexOf('_')) < 0; //still remaining "blank" letters
    }
    
    public void newGame(){
        frmHangman.resetHangman();
        word.newWord();
        frmHangman.updateStatus();
    }
    
    public String getGuessedWord(){
        return new String(word.getGuessedWord());
    }
    
    public String getWord(){
        return word.getWord();
    }
    
    
}
