
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author nafea8846
 */
public class TicTacToe implements Runnable, ActionListener {

    // Class Variables  
    JPanel mainPanel;
    JButton[] grid;
    Font bigger = new Font("arial", Font.BOLD, 52);
    String player = "X";

    // Method to assemble our GUI
    public void run() {
        // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
        JFrame frame = new JFrame("TicTacToe");
        // Makes the X button close the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes the windows 800 pixel wide by 600 pixels tall
        frame.setSize(600, 600);
        // shows the window
        frame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        
        grid = new JButton[9];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new JButton();
            grid[i].setFont(bigger);
            grid[i].addActionListener(this);
            grid[i].setActionCommand("" + i);
            mainPanel.add(grid[i]);
        }
        frame.add(mainPanel);
    }

    public void putPiece(int index, String letter) {
        grid[index].setText(letter);
        grid[index].setEnabled(false);
    }

    public void switchPlayer() {
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
    }
    // method called when a button is pressed
    public boolean winChecker (String player){
        for (int i = 0; i <= 6; i = i+3) {
            if(grid[i].getText().equals(player) && grid[i+1].getText().equals(player) && grid[i+2].getText().equals(player)){
                return true;
            }
        }
        for (int i = 0; i <=3; i++) {
            if(grid[i].getText().equals(player) && grid[i+3].getText().equals(player) && grid[i+6].getText().equals(player)){
                return true;
            }
        }
        if(grid[0].getText().equals(player)&& grid[4].getText().equals(player)&&grid[8].getText().equals(player)){
            return true;
        }
        else if (grid[3].getText().equals(player)&& grid[4].getText().equals(player)&&grid[6].getText().equals(player)){
            return true;
        }
        return false;
    }
    public void resetGame(){
        for (JButton grid1 : grid) {
            grid1.setText("");
            grid1.setEnabled(true);
        }
    }
    public boolean isTieGame(){
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            if(!grid[i].isEnabled()){
                count++;
            }
        }
        if(count == 9){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // get the command from the action
        String command = e.getActionCommand();
        int location = Integer.parseInt(command);
        putPiece(location, player);
        if (winChecker(player)){
            JOptionPane.showMessageDialog(mainPanel, player + " has won");
            resetGame();
        }else if(isTieGame()){
        JOptionPane.showMessageDialog(mainPanel, "Tie game!");
        resetGame();
        }else{
            switchPlayer();
        }
        
    }

    // Main method to start our program
    public static void main(String[] args) {
        // Creates an instance of our program
        TicTacToe gui = new TicTacToe();
        // Lets the computer know to start it in the event thread
        SwingUtilities.invokeLater(gui);
    }
}
