package hangman;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.FileReader;

/**
 *
 * @author Gil
 */
public class WordBank {
    private final ArrayList<String> bank;
    private int bankSize;
    
    public WordBank(){
        this.bank = new ArrayList<>();
        this.bankSize = 0;
        refreshBank();
    }
    
    private final void refreshBank(){
        Scanner scan = null;
        try{
            scan = new Scanner(new FileReader("words.txt"));
            while(scan.hasNext()){
                bank.add(scan.next());
            }
        }catch(IllegalStateException | FileNotFoundException e){
            System.err.println("Error reading from file!");
            System.exit(1);
        }catch (NoSuchElementException e){
            //reached end of file. hasNext() should avoid getting here, but checking for safety.
        }finally{
            this.bankSize = this.bank.size();
            if(scan != null)
                scan.close();
        }
    }
    
    public String randomWord(){
        int index;
        String word;
        Random random = new Random();
        
        if(bankSize == 0) //All words have been used already.
            refreshBank();
        
        index = random.nextInt(bankSize);
        word = new String(bank.get(index));
        bank.remove(index); //So words will rarely be used twice consequitively.
        bankSize = bank.size();
        
        return word;
    }
    
}
