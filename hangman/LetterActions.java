/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Gil
 */
public class LetterActions implements ActionListener{
    
    private final Game game;
    
    public LetterActions(Game game){
        this.game = game;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton srcBtn = (JButton)e.getSource();
        String btnText = srcBtn.getText();
        
        if(btnText.equals(Hangman.RESET_TXT)){//reset button was selected
            game.newGame();
            return;
        }
        
        if(!game.isWon()){
            srcBtn.setEnabled(false);
            game.guessLetter(btnText.charAt(0));
        }else{
            
        }
        
    }
    
}
