package hangman;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Gil
 */
public class Hangman extends JFrame{
    
    public static final String RESET_TXT = "reset";
    public static final int STATUS_FONT_SIZE = 30, BTN_FONT_SIZE = 20, FRM_VGAP = 18, FRM_H = 700, FRM_V = 800;
    
    private final Game game;
    private final HangmanPanel hangman;
    private final JLabel lblStatus;
    private final LetterButtons buttons;
    
    public Hangman(){
        super();
        
        game = new Game(this);
        hangman = new HangmanPanel();
        lblStatus = new JLabel();
        buttons = new LetterButtons();
    }
    
    private void initWindow(){
        //JFrame:
        this.setLayout(new BorderLayout(0, FRM_VGAP));
        this.setSize(FRM_H, FRM_V);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        //Hangman drawing JPanel:
        this.add(hangman, BorderLayout.CENTER);
        
        //Word status JLabel:
        lblStatus.setFont(new Font("Serif", Font.PLAIN, STATUS_FONT_SIZE));
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        this.add(lblStatus, BorderLayout.NORTH);
        
        //Buttons GridLayout:
        this.add(buttons, BorderLayout.SOUTH);
    }
    
    public void addToHangman(){
        hangman.addWrongGuess();
        this.repaint();
    }
    
    public void updateStatus(){
        if(game.isWon()){
            lblStatus.setText("Congratulations! You guessed the word: " + game.getGuessedWord());
            buttons.disableLetters();
        }else if(hangman.isFullHangman()){
            lblStatus.setText("You lost! The word was: " + game.getWord());
            buttons.disableLetters();
        } else{
            lblStatus.setText("Word guessed so far: " + game.getGuessedWord());
        }
    }
    
    public void resetHangman(){
        hangman.resetGuesses();
        buttons.resetButtons();
        this.repaint();
    }
    
    public static void main(String[] args) {
        Hangman window = new Hangman();
        
        window.initWindow();
    }
    
    
    
    private class LetterButtons extends JPanel{
        
        public static final int COL_NUM = 9, ROW_NUM = 3;
        
        private JButton btnLetters[];
        
        private LetterButtons(){
            super();

            Font font = new Font("Serif", Font.PLAIN, BTN_FONT_SIZE);
            LetterActions btnListener = new LetterActions(game);
            this.btnLetters = new JButton[COL_NUM * ROW_NUM];

            this.setLayout(new GridLayout(ROW_NUM, COL_NUM));

            for(int i = 0; i < btnLetters.length; i++){
                btnLetters[i] = new JButton(getLetter(i));
                btnLetters[i].addActionListener(btnListener);
                btnLetters[i].setFont(font);
                this.add(btnLetters[i]);
            }
        }
        
        private void disableLetters(){
            for(int i = 0; i < btnLetters.length - 1; i++)
                btnLetters[i].setEnabled(false);
        }
        
        private void resetButtons(){
            for(int i = 0; i < btnLetters.length; i++)
                btnLetters[i].setEnabled(true);
        }
        
        private final String getLetter(int num){
            switch(num){
                case 0: return "a";
                case 1: return "b";
                case 2: return "c";
                case 3: return "d";
                case 4: return "e";
                case 5: return "f";
                case 6: return "g";
                case 7: return "h";
                case 8: return "i";
                case 9: return "j";
                case 10: return "k";
                case 11: return "l";
                case 12: return "m";
                case 13: return "n";
                case 14: return "o";
                case 15: return "p";
                case 16: return "q";
                case 17: return "r";
                case 18: return "s";
                case 19: return "t";
                case 20: return "u";
                case 21: return "v";
                case 22: return "w";
                case 23: return "x";
                case 24: return "y";
                case 25: return "z";
                case 26: return RESET_TXT;
                default: return "";
            }
        }
        
    }
    
    private class HangmanPanel extends JPanel{

        public static final int NUM_OF_LIMBS = 9;

        private int wrongGuesses;

        public HangmanPanel(){
            super();

            this.wrongGuesses = 0;
        }

        @Override
        public void paintComponent(Graphics g){
            int width = this.getWidth(), height = this.getHeight();
            int wBlock = width/10, hBlock = height/10;

            switch(wrongGuesses){
                case 9: g.drawArc(width/2 - wBlock/2, 5 * hBlock / 4, wBlock, hBlock/2, 0, 180); //mouth
                case 8: g.fillOval(width/2 + wBlock/4, hBlock/2, wBlock/4, hBlock/4); //left eye
                case 7: g.fillOval(width/2 - wBlock/2, hBlock/2, wBlock/4, hBlock/4); //right eye
                case 6: g.drawLine(width/2, height/2, width/2 + wBlock, height/2 + (2 * hBlock));//left leg
                case 5: g.drawLine(width/2, height/2, width/2 - wBlock, height/2 + (2 * hBlock));//right leg
                case 4: g.drawLine(width/2, 2 * hBlock, width/2 + wBlock, 4 * hBlock); //left arm
                case 3: g.drawLine(width/2, 2 * hBlock, width/2 - wBlock, 4 * hBlock); //right arm
                case 2: g.drawLine(width/2, 2 * hBlock, width/2, height/2); //body
                case 1: g.drawOval(width/2 - wBlock, 0, 2 * wBlock, 2 * hBlock); //head
                default: break;
            }
        }

        private void resetGuesses(){
            this.wrongGuesses = 0;
        }

        private void addWrongGuess(){
            if(wrongGuesses < NUM_OF_LIMBS)
                wrongGuesses++;

            this.repaint();
        }
        
        private boolean isFullHangman(){
            return (wrongGuesses == NUM_OF_LIMBS);
        }
    }
    
    
}
