import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame {
    private int numberToGuess;
    private int attempts;
    private final int MAX_ATTEMPTS = 5;

    private JFrame frame;
    private JTextField textField;
    private JButton guessButton;
    private JButton restartButton;
    private JLabel resultLabel;

    public NumberGuessingGame() {
        initializeGame();
        createUI();
    }

    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void createUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Guess a number between 1 and 100 (5 attempts max):");
        label.setBounds(50, 20, 350, 25);
        frame.add(label);

        textField = new JTextField();
        textField.setBounds(50, 60, 100, 25);
        frame.add(textField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(160, 60, 80, 25);
        frame.add(guessButton);

        restartButton = new JButton("Restart");
        restartButton.setBounds(250, 60, 100, 25);
        restartButton.setEnabled(false);
        frame.add(restartButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 100, 350, 25);
        frame.add(resultLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame();
                textField.setText("");
                resultLabel.setText("");
                guessButton.setEnabled(true);
                restartButton.setEnabled(false);
            }
        });

        frame.setVisible(true);
    }

    private void processGuess() {
        try {
            int userGuess = Integer.parseInt(textField.getText());
            attempts++;

            if (userGuess < numberToGuess) {
                resultLabel.setText("Too low! Attempts left: " + (MAX_ATTEMPTS - attempts));
                resultLabel.setForeground(Color.RED);
            } else if (userGuess > numberToGuess) {
                resultLabel.setText("Too high! Attempts left: " + (MAX_ATTEMPTS - attempts));
                resultLabel.setForeground(Color.RED);
            } else {
                resultLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                resultLabel.setForeground(Color.GREEN);
                guessButton.setEnabled(false);
                restartButton.setEnabled(true);
                return;
            }

            if (attempts >= MAX_ATTEMPTS) {
                resultLabel.setText("Game over! The number was " + numberToGuess + ".");
                resultLabel.setForeground(Color.RED);
                guessButton.setEnabled(false);
                restartButton.setEnabled(true);
            }

        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
            resultLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
