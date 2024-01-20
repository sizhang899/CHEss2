import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartMenus extends JFrame implements ActionListener {
    JLabel startMenuBackground = new JLabel();
    JButton startTTTGameButton = new JButton();
    JButton startChessGameButton = new JButton();
    JButton startGameReviewButton = new JButton();

    public StartMenus() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, 813, 837);
        startGameReviewButton.setBounds(200, 320, 400, 133);
        startGameReviewButton.setText("Game Review");
        startGameReviewButton.addActionListener(this);
        add(startGameReviewButton);
        startChessGameButton.setBounds(200, 100, 400, 133);
        startMenuBackground.setText("  Board Master");
        startChessGameButton.setText("Chess");
        startTTTGameButton.setText("Tic Tac Toe");
        add(startChessGameButton);
        startChessGameButton.addActionListener(this);
        startTTTGameButton.setBounds(200, 532, 400, 133);



        add(startTTTGameButton);
        startTTTGameButton.addActionListener(this);
        startMenuBackground.setBounds(0, 0, 800, 800);


        add(startMenuBackground);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startChessGameButton) {

            new Chessboard();
        }

        if (e.getSource() == startTTTGameButton) {
            new TicTacToe();
        }

        if (e.getSource() == startGameReviewButton) {
            new ChessReview();
        }

    }
}
