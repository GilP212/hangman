package hangman;


/**
 *
 * @author Gil
 */
public class ChosenWord {
    private WordBank bank;
    private String word;
    private char[] guessedWord;
    
    public ChosenWord(){
        bank = new WordBank();
        word = bank.randomWord();
        guessedWord = new char[word.length()];
        
        for(int i = 0; i < word.length(); i++)
            guessedWord[i] = '_';
    }
    
    
    public boolean isCorrectGuess(char guess){
        boolean result = false;
        
        for(int i=0; i < word.length(); i++)
            if(guess == word.charAt(i)){
                result = true;
                guessedWord[i] = guess; //replaces '_' with the guessed char
            }
        
        return result;
    }
    
    
    public void newWord(){
        word = bank.randomWord();
        guessedWord = new char[word.length()];
        for(int i = 0; i < word.length(); i++)
            guessedWord[i] = '_';
    }
    
    
    public String getWord(){
        return new String(this.word);
    }
    
    public String getGuessedWord(){
        return new String(this.guessedWord); //notice guessedWord is returned as String
    }
    
}
