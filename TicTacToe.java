import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private char currentPlayer = 'X';
    private boolean gameActive = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(font);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("") && gameActive) {
            clickedButton.setText(String.valueOf(currentPlayer));
            checkWin();
            checkDraw();
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private void checkWin() {
        // Winning combinations
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                {0, 4, 8}, {2, 4, 6}              // diagonals
        };

        for (int[] combination : winCombinations) {
            if (buttons[combination[0]].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[combination[1]].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[combination[2]].getText().equals(String.valueOf(currentPlayer))) {
                gameActive = false;
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                showRestartDialog();
                return;
            }
        }
    }

    private void checkDraw() {
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw && gameActive) {
            gameActive = false;
            JOptionPane.showMessageDialog(this, "It's a draw!");
            showRestartDialog();
        }
    }

    private void showRestartDialog() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        currentPlayer = 'X';
        gameActive = true;
        for (JButton button : buttons) {
            button.setText("");
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
