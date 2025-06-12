import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

public class Sanmoku extends JFrame implements ActionListener{
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'O';
    private boolean gameEnded = false;

    public Sanmoku(){
        setTitle("三目並べ");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        Frame();
        setVisible(true);
    }

    private void Frame(){
        Font font = new Font("Arial", Font.BOLD, 100);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                JButton button = new JButton("");
                button.setFont(font);
                button.addActionListener(this);
                buttons[i][j] = button;
                add(button);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(gameEnded) return;

        JButton clicked = (JButton) e.getSource();
        if(!clicked.getText().equals("")) return;

        clicked.setText(String.valueOf(currentPlayer));
        clicked.setEnabled(false);

        if(checkWin()){
            JOptionPane.showMessageDialog(this, currentPlayer + "の勝ちです。");
            gameEnded = true;
            return;
        }

        if(FrameFull()){
            JOptionPane.showMessageDialog(this, "引き分けです。");
            gameEnded= true;
            return;
        }

        switchPlayer();
    }

    private void switchPlayer(){
        currentPlayer = (currentPlayer == 'O') ? 'X' : 'O';
    }

    private boolean FrameFull(){
        for(JButton[] row : buttons)
            for(JButton b : row)
                if(b.getText().equals("")) return false;
        return true;
    }

    private boolean checkWin(){
        for(int i = 0; i <3; i++){
            if(buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[i][2].getText().equals(String.valueOf(currentPlayer))) return true;
            if(buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][i].getText().equals(String.valueOf(currentPlayer))) return true;
        }

        if(buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) return true;
        if(buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) return true;
        
        return false;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Sanmoku::new);
    }

}
