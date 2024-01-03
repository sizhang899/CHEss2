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
    ImageIcon iconImg = new ImageIcon("Chess/assets/AppIcon.png");

    public StartMenus() {
        this.setBounds(0, 0, 813, 837);

        this.setIconImage(this.iconImg.getImage());
        this.startChessGameButton.setBounds(200, 532, 400, 133);
        this.startMenuBackground.setText("  Board Master");
        startChessGameButton.setText("Chess");
        startTTTGameButton.setText("Tic Tac Toe");
        this.add(this.startChessGameButton);
        this.startChessGameButton.addActionListener(this);
        this.startTTTGameButton.setBounds(200, 266, 400, 133);



        this.add(this.startTTTGameButton);
        this.startTTTGameButton.addActionListener(this);
        this.startMenuBackground.setBounds(0, 0, 800, 800);


        this.add(this.startMenuBackground);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.startChessGameButton) {

            new Chessboard();
        }

        if (e.getSource() == this.startTTTGameButton) {
            new TicTacToe();
        }

    }
}
