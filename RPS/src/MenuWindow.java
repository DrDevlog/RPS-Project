import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    private JButton startButton, instructionsButton;
    private JRadioButton pvpRadioButton, pveRadioButton;
    private JLabel pWins;
    private JLabel cWins;

    public MenuWindow(int w) {
        setTitle("Rock Paper Scissors Menu");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        if(w == 1){
            rockPaperScissorsGame.playerWins++;
        }else if(w == 2){
            rockPaperScissorsGame.computerWins++;
        }

        pWins = new JLabel("Player Wins: " + rockPaperScissorsGame.playerWins);
        cWins = new JLabel("Computer Wins: " + rockPaperScissorsGame.computerWins);
        startButton = new JButton("Start Game");
        instructionsButton = new JButton("Instructions");
        pvpRadioButton = new JRadioButton("PvP");
        pveRadioButton = new JRadioButton("PvE");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(pvpRadioButton);
        modeGroup.add(pveRadioButton);

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Select Game Mode:"));
        add(new JPanel());
        add(pvpRadioButton);
        add(pveRadioButton);
        add(startButton);
        add(instructionsButton);
        add(cWins);
        add(pWins);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pvpRadioButton.isSelected() || pveRadioButton.isSelected()) {
                    setVisible(false);
                    String mode = pvpRadioButton.isSelected() ? "PvP" : "PvE";
                    new rockPaperScissorsGame(mode).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MenuWindow.this, "Please select a game mode!");
                }
            }
        });

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });
    }

    public void showInstructions() {
        JOptionPane.showMessageDialog(this, "Instructions:\n" +
                "Rock beats scissors\n" +
                "Scissors beats paper\n" +
                "Paper beats rock");
    }
}