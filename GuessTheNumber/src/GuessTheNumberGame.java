import javax.swing.JOptionPane;
import java.util.Random;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        int totalRounds = 10; // maximum possible rounds
        int totalScore = 0;

        for (int round = 1; round <= totalRounds; round++) {
            int roundScore = playRound(round);
            totalScore += roundScore;
            JOptionPane.showMessageDialog(null, "Round " + round + " Score: " + roundScore);

            // Ask the user if they want to continue
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to play the next round?",
                    "Continue?",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice != JOptionPane.YES_OPTION) {
                break; // stop the rounds if user clicks No
            }
        }

        JOptionPane.showMessageDialog(null, "Game Over! Total Score: " + totalScore);
    }

    public static int playRound(int roundNumber) {
        Random rand = new Random();
        int min = 1;
        int max = 100;
        int randomNumber = rand.nextInt(max - min + 1) + min;
        int attempts = 0;
        int maxAttempts = 10;
        int guess = 0;

        JOptionPane.showMessageDialog(
                null,
                "Round " + roundNumber + ": Guess the number between " + min + " and " + max
        );

        while (attempts < maxAttempts) {
            String input = JOptionPane.showInputDialog("Enter your guess:");

            if (input == null || input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You cancelled the game.");
                break;
            }

            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number!");
                continue;
            }

            attempts++;

            if (guess == randomNumber) {
                JOptionPane.showMessageDialog(null, "Correct! You guessed the number in " + attempts + " attempts.");
                return calculateScore(attempts);
            } else if (guess < randomNumber) {
                JOptionPane.showMessageDialog(null, "Too low! Attempts left: " + (maxAttempts - attempts));
            } else {
                JOptionPane.showMessageDialog(null, "Too high! Attempts left: " + (maxAttempts - attempts));
            }
        }

        JOptionPane.showMessageDialog(null, "Sorry! The number was: " + randomNumber);
        return 0;
    }

    public static int calculateScore(int attempts) {
        return Math.max(0, 10 - attempts + 1);
    }
}