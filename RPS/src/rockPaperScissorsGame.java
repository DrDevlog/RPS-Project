import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rockPaperScissorsGame extends JFrame{
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel;
    private boolean isPvPMode;
    private Choice playerOneChoice;
    public int winner;
    public static int playerWins = 0;
    public static int computerWins = 0;

    public rockPaperScissorsGame(String mode) {
        setTitle("Rock Paper Scissors Game - " + mode);
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        isPvPMode = mode.equals("PvP");

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        resultLabel = new JLabel();

        setLayout(new FlowLayout());

        add(rockButton);
        add(paperButton);
        add(scissorsButton);
        add(resultLabel);

        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOneChoice = Choice.ROCK;
                if (isPvPMode) {
                    promptPlayerTwoChoice();
                } else {
                    playGame(Choice.ROCK);
                }
            }
        });

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOneChoice = Choice.PAPER;
                if (isPvPMode) {
                    promptPlayerTwoChoice();
                } else {
                    playGame(Choice.PAPER);
                }
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOneChoice = Choice.SCISSORS;
                if (isPvPMode) {
                    promptPlayerTwoChoice();
                } else {
                    playGame(Choice.SCISSORS);
                }
            }
        });
    }

    private void promptPlayerTwoChoice() {
        PlayerChoiceDialog playerChoiceDialog = new PlayerChoiceDialog(this);
        Choice playerTwoChoice = playerChoiceDialog.getPlayerChoice();

        if (playerTwoChoice != null) {
            playGame(playerTwoChoice);
        }
    }

    private void playGame(Choice playerTwoChoice) {
        Choice computerChoice;
        String result;
        winner = 0;

        if (isPvPMode) {
            computerChoice = null;
            result = determinePvPWinner(playerOneChoice, playerTwoChoice);
        } else {
            computerChoice = getRandomChoice();
            result = determineWinner(playerOneChoice, computerChoice);
        }

        resultLabel.setText(result);

        JOptionPane.showMessageDialog(this, result);
            MenuWindow menuWindow = new MenuWindow(winner);
            menuWindow.setVisible(true);
            dispose();
    }

    private Choice getRandomChoice() {
        int random = (int) (Math.random() * 3);
        if (random == 0) {
            return Choice.ROCK;
        } else if (random == 1) {
            return Choice.PAPER;
        } else {
            return Choice.SCISSORS;
        }
    }

    private String determineWinner(Choice playerChoice, Choice computerChoice) {
        if (playerChoice == computerChoice) {
            winner = 0;
            return "It's a tie!";
        } else if (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS ||
                playerChoice == Choice.PAPER && computerChoice == Choice.ROCK ||
                playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) {
            winner = 1;
            return "You win!";
        } else {
            winner = 2;
            return "Computer wins!";
        }
    }

    private String determinePvPWinner(Choice playerOneChoice, Choice playerTwoChoice) {
        if (playerOneChoice == playerTwoChoice) {
            winner = 0;
            return "It's a tie!";
        } else if (playerOneChoice == Choice.ROCK && playerTwoChoice == Choice.SCISSORS ||
                playerOneChoice == Choice.PAPER && playerTwoChoice == Choice.ROCK ||
                playerOneChoice == Choice.SCISSORS && playerTwoChoice == Choice.PAPER) {
            winner = 1;
            return "Player 1 wins!";
        } else {
            winner = 2;
            return "Player 2 wins!";
        }
    }

    private enum Choice {
        ROCK, PAPER, SCISSORS
    }

    private class PlayerChoiceDialog extends JDialog {
        private JButton rockButton, paperButton, scissorsButton;
        private Choice playerChoice;

        public PlayerChoiceDialog(JFrame parent) {
            super(parent, "Player 2 Choice", true);
            setSize(200, 100);
            setLayout(new FlowLayout());
            setLocationRelativeTo(parent);

            rockButton = new JButton("Rock");
            paperButton = new JButton("Paper");
            scissorsButton = new JButton("Scissors");

            add(rockButton);
            add(paperButton);
            add(scissorsButton);

            rockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerChoice = Choice.ROCK;
                    dispose();
                }
            });

            paperButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerChoice = Choice.PAPER;
                    dispose();
                }
            });

            scissorsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerChoice = Choice.SCISSORS;
                    dispose();
                }
            });

            setVisible(true);
        }

        public Choice getPlayerChoice() {
            return playerChoice;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuWindow menuWindow = new MenuWindow(0);
                menuWindow.setVisible(true);
            }
        });
    }
}
