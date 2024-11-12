import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GuessTheNumberWithHintsGUI {

    private int min = 1;
    private int max = 100;
    private int number;
    private int attempts = 6;  // Set to 6 attempts
    private boolean guessed = false;

    // GUI components
    private JFrame frame;
    private JTextField guessInput;
    private JLabel messageLabel;
    private JLabel hintLabel;
    private JLabel attemptsLabel;
    private JButton guessButton;

    public GuessTheNumberWithHintsGUI() {
        // Generate random number
        number = (int) (Math.random() * (max - min + 1) + min);

        // Create the GUI frame
        frame = new JFrame("Guess The Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        // Title Label
        JLabel titleLabel = new JLabel("Guess the Number!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.PINK);
        frame.add(titleLabel);

        // Instructions and attempts
        messageLabel = new JLabel("I have chosen a number between " + min + " and " + max + ".", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(messageLabel);

        attemptsLabel = new JLabel("You have " + attempts + " attempts left.", JLabel.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        attemptsLabel.setForeground(Color.ORANGE);
        frame.add(attemptsLabel);

        // Hint Label
        hintLabel = new JLabel(getHint(), JLabel.CENTER);
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        hintLabel.setForeground(Color.BLUE);
        frame.add(hintLabel);

        // Input and Button Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        guessInput = new JTextField(10);
        guessButton = new JButton("Guess");

        guessButton.addActionListener(new GuessButtonListener());

        inputPanel.add(guessInput);
        inputPanel.add(guessButton);
        frame.add(inputPanel);

        // Show the frame
        frame.setVisible(true);
    }

    // Get hint if the number is prime or perfect
    private String getHint() {
        if (isPrime(number)) {
            return "Hint: The number is a prime number.";
        } else if (isPerfect(number)) {
            return "Hint: The number is a perfect number.";
        } else {
            return "Hint: The number is neither a prime number nor a perfect number.";
        }
    }

    // Action listener for guess button
    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessInput.getText());
                attempts--;
                if (guess == number) {
                    messageLabel.setText("Congratulations! You guessed it right.");
                    messageLabel.setForeground(Color.GREEN);
                    guessed = true;
                    guessButton.setEnabled(false);
                } else if (guess < number) {
                    messageLabel.setText("Too low! Try again.");
                    messageLabel.setForeground(Color.RED);
                } else {
                    messageLabel.setText("Too high! Try again.");
                    messageLabel.setForeground(Color.RED);
                }

                if (!guessed && attempts > 0) {
                    attemptsLabel.setText("You have " + attempts + " attempts left.");
                } else if (attempts == 0) {
                    messageLabel.setText("Out of attempts! The number was " + number + ".");
                    messageLabel.setForeground(Color.RED);
                    guessButton.setEnabled(false);
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
                messageLabel.setForeground(Color.RED);
            }
        }
    }

    // Function to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Function to check if a number is a perfect number
    public static boolean isPerfect(int num) {
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num && num != 1;
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new GuessTheNumberWithHintsGUI());
    }
}