
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.JFrame;

public class Chessboard extends JFrame implements ActionListener {
    Peice peice;
    Peice tempPawn;
    Peice deletedPeice;
    String playerTurn = "w";
    int oldClickedRow;
    int oldClickedCol;
    Peice[][] peiceArr = new Peice[8][8];
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 100;

    private JFrame frame;
    private JButton[][] squares;
    private String[][] boardState;
    Queen q1;

    Rook wRook1 = new Rook("♜", 1, 0, 0, peiceArr, "b");
    Knight wKnight1 = new Knight("♞", 1, 1, 0, peiceArr, "b");
    Bishop wBishop1 = new Bishop("♝", 1, 2, 0, peiceArr, "b");
    Queen wQueen = new Queen("♛", 1, 3, 0, peiceArr, "b");
    King wKing = new King("♚", 1, 4, 0, peiceArr, "b");
    Bishop wBishop2 = new Bishop("♝", 2, 5, 0, peiceArr, "b");
    Knight wKnight2 = new Knight("♞", 2, 6, 0, peiceArr, "b");
    Rook wRook2 = new Rook("♜", 2, 7, 0, peiceArr, "b");

    Peice wPawn1 = new Pawn("♟", 1, 0, 1, peiceArr, "b");
    Peice wPawn2 = new Pawn("♟", 2, 1, 1, peiceArr, "b");
    Peice wPawn3 = new Pawn("♟", 3, 2, 1, peiceArr, "b");
    Peice wPawn4 = new Pawn("♟", 4, 3, 1, peiceArr, "b");
    Peice wPawn5 = new Pawn("♟", 5, 4, 1, peiceArr, "b");
    Peice wPawn6 = new Pawn("♟", 6, 5, 1, peiceArr, "b");
    Peice wPawn7 = new Pawn("♟", 7, 6, 1, peiceArr, "b");
    Peice wPawn8 = new Pawn("♟", 8, 7, 1, peiceArr, "b");

    Rook bRook1 = new Rook("♖", 1, 0, 7, peiceArr, "w");
    Knight bKnight1 = new Knight("♘", 1, 1, 7, peiceArr, "w");
    Bishop bBishop1 = new Bishop("♗", 1, 2, 7, peiceArr, "w");
    Queen bQueen = new Queen("♕", 1, 3, 7, peiceArr, "w");
    King bKing = new King("♔", 1, 4, 7, peiceArr, "w");
    Bishop bBishop2 = new Bishop("♗", 2, 5, 7, peiceArr, "w");
    Knight bKnight2 = new Knight("♘", 2, 6, 7, peiceArr, "w");
    Rook bRook2 = new Rook("♖", 2, 7, 7, peiceArr, "w");

    Peice bPawn1 = new Pawn("♙", 1, 0, 6, peiceArr, "w");
    Peice bPawn2 = new Pawn("♙", 2, 1, 6, peiceArr, "w");
    Peice bPawn3 = new Pawn("♙", 3, 2, 6, peiceArr, "w");
    Peice bPawn4 = new Pawn("♙", 4, 3, 6, peiceArr, "w");
    Peice bPawn5 = new Pawn("♙", 5, 4, 6, peiceArr, "w");
    Peice bPawn6 = new Pawn("♙", 6, 5, 6, peiceArr, "w");
    Peice bPawn7 = new Pawn("♙", 7, 6, 6, peiceArr, "w");
    Peice bPawn8 = new Pawn("♙", 8, 7, 6, peiceArr, "w");


    public Chessboard(){

        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SQUARE_SIZE * BOARD_SIZE, SQUARE_SIZE * BOARD_SIZE);

        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        squares = new JButton[BOARD_SIZE][BOARD_SIZE];
        boardState = new String[BOARD_SIZE][BOARD_SIZE];
        boardPanel.setFont(new Font("Arial", 1, 50));
        Color c1 = new Color(0xFFF6EFCE, true);//rgb colors
        Color c2 = new Color(0xFF568A38, true);//I wanted to make the board look nice
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton square = new JButton();
                square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                square.setBackground((row + col) % 2 == 0 ? c1 : c2);
                square.addActionListener(this);
                squares[row][col] = square;
                boardPanel.add(square);
                boardState[row][col] = "";
            }
        }

        initializePieces();
        updateBoard();
        frame.add(boardPanel);
        frame.setVisible(true);
    }
    private void updateBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col].setFont(new Font("", 1, 59));
                squares[row][col].setText(boardState[row][col]);
            }
        }
    }
    public void updateBoardState(int x, int y, int oldx, int oldy, Peice newP){

        boardState[y][x]=newP.getcolor();
        squares[y][x].setText(newP.getcolor());
        boardState[oldy][oldx]="";
        squares[oldy][oldx].setText("");

    }

    public void actionPerformed(ActionEvent e) {

        JButton clickedSquare = (JButton) e.getSource();
        int clickedRow = -1;
        int clickedCol = -1;

        //two for loops to find the x and y or row and column
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (clickedSquare == squares[row][col]) {
                    clickedRow = row;
                    clickedCol = col;
                    break ;
                }
            }
        }

        try {
            if (clickedRow != -1 && clickedCol != -1) {//if it is a valid input
                if (!boardState[clickedRow][clickedCol].isEmpty()) {//if clicked square is not empty

                    if (playerTurn==(selectPiece(clickedCol, clickedRow).getCOLOR())){//if it is also your turn
                        oldClickedCol = clickedCol;//stores original position
                        oldClickedRow = clickedRow;//stores original position
                        peice = selectPiece(clickedCol, clickedRow);//this is the peice selected for movement
                        System.out.println("selected peice is " + peice+" position is (" + peice.xPos + ", " + peice.yPos + ") piece color is " + peice.getCOLOR());//text to display what is happening in console


                    } else if (playerTurn!=(deletedPeice=selectPiece(clickedCol, clickedRow)).getCOLOR()) {//if there is a piece that is not of your color then that means you want to capture it
                        if (peice.validCapture(oldClickedCol, oldClickedRow, clickedCol, clickedRow, boardState)) {
                            System.out.println("Captured "+deletedPeice+" with "+ peice + " at (" + peice.xPos + ", " + peice.yPos+")");
                            deletedPeice.yPos=(int)Math.random()*1000000;//the piece being captured will be not usable
                            deletedPeice.xPos=(int)Math.random()*1000000;//the piece being captured will be not usable
                            deletedPeice.color="";//the piece being captured will be not usable
                            deletedPeice=null;//the piece being captured will be not usable
                            peice.MovePiece(clickedCol, clickedRow);//this way there will be no confusion for the computer

                            updateBoardState(peice.getxPos(),peice.getyPos(),oldClickedCol, oldClickedRow, peice);//updates chessboard

                            if (playerTurn.equals("w")){
                                playerTurn = "b";
                            }else {
                                playerTurn = "w";
                            }
                            if (peice instanceof Pawn&&peice.COLOR=="w"&&peice.getyPos()==0){//checks for promotion

                                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                                int choice = JOptionPane.showOptionDialog(null, "Choose a piece to promote to:", "Pawn Promotion",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                                // Processing the user's choice
                                switch (choice) {
                                    case 0:
                                    if (peice.peiceNumber == 1 && peice.COLOR == "w") {
                                        bPawn1 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber == 2 && peice.COLOR == "w") {
                                        bPawn2 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    } else if (peice.peiceNumber == 3 && peice.COLOR == "w") {
                                        bPawn3 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    } else if (peice.peiceNumber == 4 && peice.COLOR == "w") {
                                        bPawn4 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    } else if (peice.peiceNumber == 5 && peice.COLOR == "w") {
                                        bPawn5 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    } else if (peice.peiceNumber == 6 && peice.COLOR == "w") {
                                        bPawn6 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    } else if (peice.peiceNumber == 7 && peice.COLOR == "w") {
                                        bPawn7 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    } else if (peice.peiceNumber == 8 && peice.COLOR == "w") {
                                        bPawn8 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }

                                    break;
                                case 1:
                                    if (peice.peiceNumber == 1 && peice.COLOR == "w") {
                                        bPawn1 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber == 2 && peice.COLOR == "w") {
                                        bPawn2 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    } else if (peice.peiceNumber == 3 && peice.COLOR == "w") {
                                        bPawn3 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    } else if (peice.peiceNumber == 4 && peice.COLOR == "w") {
                                        bPawn4 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    } else if (peice.peiceNumber == 5 && peice.COLOR == "w") {
                                        bPawn5 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    } else if (peice.peiceNumber == 6 && peice.COLOR == "w") {
                                        bPawn6 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    } else if (peice.peiceNumber == 7 && peice.COLOR == "w") {
                                        bPawn7 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    } else if (peice.peiceNumber == 8 && peice.COLOR == "w") {
                                        bPawn8 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                case 2:
                                    if (peice.peiceNumber == 1 && peice.COLOR == "w") {
                                        bPawn1 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber == 2 && peice.COLOR == "w") {
                                        bPawn2 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    } else if (peice.peiceNumber == 3 && peice.COLOR == "w") {
                                        bPawn3 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    } else if (peice.peiceNumber == 4 && peice.COLOR == "w") {
                                        bPawn4 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    } else if (peice.peiceNumber == 5 && peice.COLOR == "w") {
                                        bPawn5 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    } else if (peice.peiceNumber == 6 && peice.COLOR == "w") {
                                        bPawn6 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    } else if (peice.peiceNumber == 7 && peice.COLOR == "w") {
                                        bPawn7 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    } else if (peice.peiceNumber == 8 && peice.COLOR == "w") {
                                        bPawn8 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                case 3:
                                    if (peice.peiceNumber == 1 && peice.COLOR == "w") {
                                        bPawn1 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber == 2 && peice.COLOR == "w") {
                                        bPawn2 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    } else if (peice.peiceNumber == 3 && peice.COLOR == "w") {
                                        bPawn3 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    } else if (peice.peiceNumber == 4 && peice.COLOR == "w") {
                                        bPawn4 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    } else if (peice.peiceNumber == 5 && peice.COLOR == "w") {
                                        bPawn5 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    } else if (peice.peiceNumber == 6 && peice.COLOR == "w") {
                                        bPawn6 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    } else if (peice.peiceNumber == 7 && peice.COLOR == "w") {
                                        bPawn7 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    } else if (peice.peiceNumber == 8 && peice.COLOR == "w") {
                                        bPawn8 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                default:
                                    if (peice.peiceNumber == 1 && peice.COLOR == "w") {
                                        bPawn1 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber == 2 && peice.COLOR == "w") {
                                        bPawn2 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    } else if (peice.peiceNumber == 3 && peice.COLOR == "w") {
                                        bPawn3 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    } else if (peice.peiceNumber == 4 && peice.COLOR == "w") {
                                        bPawn4 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    } else if (peice.peiceNumber == 5 && peice.COLOR == "w") {
                                        bPawn5 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    } else if (peice.peiceNumber == 6 && peice.COLOR == "w") {
                                        bPawn6 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    } else if (peice.peiceNumber == 7 && peice.COLOR == "w") {
                                        bPawn7 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    } else if (peice.peiceNumber == 8 && peice.COLOR == "w") {
                                        bPawn8 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;



                                }
                            } else if (peice instanceof Pawn&&peice.COLOR=="b"&&peice.getyPos()==7) {
                                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                                int choice = JOptionPane.showOptionDialog(null, "Choose a piece to promote to:", "Pawn Promotion",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                                // Processing the user's choice
                                switch (choice) {
                                    case 0:

                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }

                                    break;
                                case 1:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                case 2:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                case 3:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                default:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                }
                            }
                            System.out.println("turn = "+playerTurn);
                        }else {
                            JOptionPane.showMessageDialog(this, "Illegal capture", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }else {
                    if (peice.getcolor()=="♚"&&!wKing.kingHasmoved&&(clickedCol==2&&!wRook1.RookHasMoved||clickedCol==6&&!wRook2.RookHasMoved)){
                        if (wKing.validCastle(oldClickedCol, oldClickedRow, clickedCol, clickedRow, boardState)){
                            peice.MovePiece(clickedCol, clickedRow);
                            updateBoardState(peice.getxPos(), peice.getyPos(), oldClickedCol, oldClickedRow, peice);
                            if (peice.getxPos()==2&&peice.getyPos()==0){
                                wRook1.MovePiece(3, 0);
                                updateBoardState(wRook1.getxPos(), wRook1.getyPos(), 0, 0, wRook1);

                            } else if (peice.getxPos()==6&&peice.getyPos()==0) {
                                wRook2.MovePiece(5, 0);
                                updateBoardState(wRook2.getxPos(), wRook2.getyPos(), 7, 0, wRook2);
                            }
                            if (playerTurn.equals("w")) {
                                playerTurn = "b";
                            } else {
                                playerTurn = "w";
                            }
                            peice = null;
                        }
                    } else if (peice.getcolor()=="♔"&&!bKing.kingHasmoved&&(clickedCol==2&&!bRook1.RookHasMoved||clickedCol==6&&!bRook2.RookHasMoved)) {
                        if (bKing.validCastle(oldClickedCol, oldClickedRow, clickedCol, clickedRow, boardState)){
                            peice.MovePiece(clickedCol, clickedRow);
                            updateBoardState(peice.getxPos(), peice.getyPos(), oldClickedCol, oldClickedRow, peice);
                            if (peice.getxPos()==2&&peice.getyPos()==7){
                                bRook1.MovePiece(3, 7);
                                updateBoardState(bRook1.getxPos(), bRook1.getyPos(), 0, 7, bRook1);

                            } else if (peice.getxPos()==6&&peice.getyPos()==7) {
                                bRook2.MovePiece(5, 7);
                                updateBoardState(bRook2.getxPos(), bRook2.getyPos(), 7, 7, bRook2);
                            }
                            if (playerTurn.equals("w")) {
                                playerTurn = "b";
                            } else {
                                playerTurn = "w";
                            }
                            peice = null;
                        }
                    }else {
                        if (peice.validMove(oldClickedCol, oldClickedRow, clickedCol, clickedRow, boardState)) {

                            peice.MovePiece(clickedCol, clickedRow);
                            updateBoardState(peice.getxPos(), peice.getyPos(), oldClickedCol, oldClickedRow, peice);
                            if (playerTurn.equals("w")) {
                                playerTurn = "b";
                            } else {
                                playerTurn = "w";
                            }
                            System.out.println(peice + " moved to ("+peice.xPos+", "+peice.yPos+")");
                            System.out.println("turn = " + playerTurn);

                            if (peice instanceof Pawn&&peice.COLOR=="w"&&peice.getyPos()==0){
                                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                                int choice = JOptionPane.showOptionDialog(null, "Choose a piece to promote to:", "Pawn Promotion",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                                // Processing the user's choice
                                switch (choice) {
                                    case 0:
                                    if (peice.peiceNumber==1&&peice.COLOR=="w"){
                                        bPawn1 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="w"){
                                        bPawn2 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="w"){
                                        bPawn3 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="w"){
                                        bPawn4 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="w"){
                                        bPawn5 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="w"){
                                        bPawn6 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="w"){
                                        bPawn7 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="w"){
                                        bPawn8 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }

                                    break;
                                case 1:
                                    if (peice.peiceNumber==1&&peice.COLOR=="w"){
                                        bPawn1 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="w"){
                                        bPawn2 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="w"){
                                        bPawn3 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="w"){
                                        bPawn4 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="w"){
                                        bPawn5 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="w"){
                                        bPawn6 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="w"){
                                        bPawn7 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="w"){
                                        bPawn8 = new Rook("♖", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                case 2:
                                    if (peice.peiceNumber==1&&peice.COLOR=="w"){
                                        bPawn1 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="w"){
                                        bPawn2 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="w"){
                                        bPawn3 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="w"){
                                        bPawn4 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="w"){
                                        bPawn5 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="w"){
                                        bPawn6 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="w"){
                                        bPawn7 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="w"){
                                        bPawn8 = new Bishop("♗", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                case 3:
                                    if (peice.peiceNumber==1&&peice.COLOR=="w"){
                                        bPawn1 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="w"){
                                        bPawn2 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="w"){
                                        bPawn3 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="w"){
                                        bPawn4 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="w"){
                                        bPawn5 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="w"){
                                        bPawn6 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="w"){
                                        bPawn7 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="w"){
                                        bPawn8 = new Knight("♘", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                default:
                                    // Default action if the user closes the dialog
                                    if (peice.peiceNumber==1&&peice.COLOR=="w"){
                                        bPawn1 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn1.xPos, bPawn1.yPos, oldClickedCol, oldClickedRow, bPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="w"){
                                        bPawn2 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn2.xPos, bPawn2.yPos, oldClickedCol, oldClickedRow, bPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="w"){
                                        bPawn3 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn3.xPos, bPawn3.yPos, oldClickedCol, oldClickedRow, bPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="w"){
                                        bPawn4 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn4.xPos, bPawn4.yPos, oldClickedCol, oldClickedRow, bPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="w"){
                                        bPawn5 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn5.xPos, bPawn5.yPos, oldClickedCol, oldClickedRow, bPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="w"){
                                        bPawn6 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn6.xPos, bPawn6.yPos, oldClickedCol, oldClickedRow, bPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="w"){
                                        bPawn7 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn7.xPos, bPawn7.yPos, oldClickedCol, oldClickedRow, bPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="w"){
                                        bPawn8 = new Queen("♕", 1, clickedCol, clickedRow, peiceArr, "w");
                                        updateBoardState(bPawn8.xPos, bPawn8.yPos, oldClickedCol, oldClickedRow, bPawn8);
                                    }
                                    break;
                                }
                            } else if (peice instanceof Pawn&&peice.COLOR=="b"&&peice.getyPos()==7) {
                                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                                int choice = JOptionPane.showOptionDialog(null, "Choose a piece to promote to:", "Pawn Promotion",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                                // Processing the user's choice
                                switch (choice) {
                                    case 0:

                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }

                                    break;
                                case 1:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Rook("♜", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                case 2:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Bishop("♝", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                case 3:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Knight("♞", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                default:
                                    if (peice.peiceNumber==1&&peice.COLOR=="b"){
                                        wPawn1 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn1.xPos, wPawn1.yPos, oldClickedCol, oldClickedRow, wPawn1);
                                    } else if (peice.peiceNumber==2&&peice.COLOR=="b"){
                                        wPawn2 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn2.xPos, wPawn2.yPos, oldClickedCol, oldClickedRow, wPawn2);
                                    }else if (peice.peiceNumber==3&&peice.COLOR=="b"){
                                        wPawn3 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn3.xPos, wPawn3.yPos, oldClickedCol, oldClickedRow, wPawn3);
                                    }else if (peice.peiceNumber==4&&peice.COLOR=="b"){
                                        wPawn4 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn4.xPos, wPawn4.yPos, oldClickedCol, oldClickedRow, wPawn4);
                                    }else if (peice.peiceNumber==5&&peice.COLOR=="b"){
                                        wPawn5 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn5.xPos, wPawn5.yPos, oldClickedCol, oldClickedRow, wPawn5);
                                    }else if (peice.peiceNumber==6&&peice.COLOR=="b"){
                                        wPawn6 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn6.xPos, wPawn6.yPos, oldClickedCol, oldClickedRow, wPawn6);
                                    }else if (peice.peiceNumber==7&&peice.COLOR=="b"){
                                        wPawn7 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn7.xPos, wPawn7.yPos, oldClickedCol, oldClickedRow, wPawn7);
                                    }else if (peice.peiceNumber==8&&peice.COLOR=="b"){
                                        wPawn8 = new Queen("♛", 1, clickedCol, clickedRow, peiceArr, "b");
                                        updateBoardState(wPawn8.xPos, wPawn8.yPos, oldClickedCol, oldClickedRow, wPawn8);
                                    }
                                    break;
                                }
                            }
                            peice = null;
                        } else {
                            JOptionPane.showMessageDialog(this, "Illegal move", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Not your turn, select piece first", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (bKing.getcolor()==""){
            JOptionPane.showMessageDialog(this, "Black WON!");
        }
        if (wKing.getcolor()==""){
            JOptionPane.showMessageDialog(this, "White WON");
        }
    }
    private void initializePieces() {

        // intantciating (I hope I spelled that right) all the peices
        Rook wRook1 = new Rook("♖", 1, 0, 0, peiceArr, "w");
        Knight wKnight1 = new Knight("♘", 1, 1, 0, peiceArr, "w");
        Bishop wBishop1 = new Bishop("♗", 1, 2, 0, peiceArr, "w");
        Queen wQueen = new Queen("♕", 1, 3, 0, peiceArr, "w");
        King wKing = new King("♔", 1, 4, 0, peiceArr, "w");
        Bishop wBishop2 = new Bishop("♗", 2, 5, 0, peiceArr, "w");
        Knight wKnight2 = new Knight("♘", 2, 6, 0, peiceArr, "w");
        Rook wRook2 = new Rook("♖", 2, 7, 0, peiceArr, "w");

        Peice wPawn1 = new Pawn("♙", 1, 0, 1, peiceArr, "w");
        Peice wPawn2 = new Pawn("♙", 2, 1, 1, peiceArr, "w");
        Peice wPawn3 = new Pawn("♙", 3, 2, 1, peiceArr, "w");
        Peice wPawn4 = new Pawn("♙", 4, 3, 1, peiceArr, "w");
        Peice wPawn5 = new Pawn("♙", 5, 4, 1, peiceArr, "w");
        Peice wPawn6 = new Pawn("♙", 6, 5, 1, peiceArr, "w");
        Peice wPawn7 = new Pawn("♙", 7, 6, 1, peiceArr, "w");
        Peice wPawn8 = new Pawn("♙", 8, 7, 1, peiceArr, "w");

        Rook bRook1 = new Rook("♜", 1, 0, 7, peiceArr, "b");
        Knight bKnight1 = new Knight("♞", 1, 1, 7, peiceArr, "b");
        Bishop bBishop1 = new Bishop("♝", 1, 2, 7, peiceArr, "b");
        Queen bQueen = new Queen("♛", 1, 3, 7, peiceArr, "b");
        King bKing = new King("♚", 1, 4, 7, peiceArr, "b");
        Bishop bBishop2 = new Bishop("♝", 2, 5, 7, peiceArr, "b");
        Knight bKnight2 = new Knight("♞", 2, 6, 7, peiceArr, "b");
        Rook bRook2 = new Rook("♜", 2, 7, 7, peiceArr, "b");

        Peice bPawn1 = new Pawn("♟", 1, 0, 6, peiceArr, "b");
        Peice bPawn2 = new Pawn("♟", 2, 1, 6, peiceArr, "b");
        Peice bPawn3 = new Pawn("♟", 3, 2, 6, peiceArr, "b");
        Peice bPawn4 = new Pawn("♟", 4, 3, 6, peiceArr, "b");
        Peice bPawn5 = new Pawn("♟", 5, 4, 6, peiceArr, "b");
        Peice bPawn6 = new Pawn("♟", 6, 5, 6, peiceArr, "b");
        Peice bPawn7 = new Pawn("♟", 7, 6, 6, peiceArr, "b");
        Peice bPawn8 = new Pawn("♟", 8, 7, 6, peiceArr, "b");

        boardState[0] = new String[]{bRook1.getcolor(), bKnight1.getcolor(), bBishop1.getcolor(), bQueen.getcolor(), bKing.getcolor(), bBishop2.getcolor(), bKnight2.getcolor(), bRook2.getcolor()};
        boardState[1] = new String[]{bPawn1.getcolor(), bPawn2.getcolor(), bPawn3.getcolor(), bPawn4.getcolor(), bPawn5.getcolor(), bPawn6.getcolor(), bPawn7.getcolor(), bPawn8.getcolor()};
        boardState[6] = new String[]{wPawn1.getcolor(), wPawn2.getcolor(), wPawn3.getcolor(), wPawn4.getcolor(), wPawn5.getcolor(), wPawn6.getcolor(), wPawn7.getcolor(), wPawn8.getcolor()};
        boardState[7] = new String[]{wRook1.getcolor(), wKnight1.getcolor(), wBishop1.getcolor(), wQueen.getcolor(), wKing.getcolor(), wBishop2.getcolor(), wKnight2.getcolor(), wRook2.getcolor()};
    }

    public Peice selectPiece(int x, int y){
        if (wRook1.getxPos()==x&&wRook1.getyPos()==y){
            return wRook1;
        } else if (wKnight1.getxPos()==x&&wKnight1.getyPos()==y){
            return wKnight1;
        }else if (wBishop1.getxPos()==x&&wBishop1.getyPos()==y){
            return wBishop1;
        }else if (wQueen.getxPos()==x&&wQueen.getyPos()==y){
            return wQueen;
        }else if (wKing.getxPos()==x&&wKing.getyPos()==y){
            return wKing;
        }else if (wBishop2.getxPos()==x&&wBishop2.getyPos()==y){
            return wBishop2;
        }else if (wKnight1.getxPos()==x&&wKnight1.getyPos()==y){
            return wKnight1;
        }else if (wKnight2.getxPos()==x&&wKnight2.getyPos()==y){
            return wKnight2;
        }else if (wRook2.getxPos()==x&&wRook2.getyPos()==y){
            return wRook2;
        }else if (wPawn1.getxPos()==x&&wPawn1.getyPos()==y){
            return wPawn1;
        }else if (wPawn2.getxPos()==x&&wPawn2.getyPos()==y){
            return wPawn2;
        }else if (wPawn3.getxPos()==x&&wPawn3.getyPos()==y){
            return wPawn3;
        }else if (wPawn4.getxPos()==x&&wPawn4.getyPos()==y){
            return wPawn4;
        }else if (wPawn5.getxPos()==x&&wPawn5.getyPos()==y){
            return wPawn5;
        }else if (wPawn6.getxPos()==x&&wPawn6.getyPos()==y){
            return wPawn6;
        }else if (wPawn7.getxPos()==x&&wPawn7.getyPos()==y){
            return wPawn7;
        }else if (wPawn8.getxPos()==x&&wPawn8.getyPos()==y){
            return wPawn8;
        }else if (bRook1.getxPos()==x&&bRook1.getyPos()==y){
            return bRook1;
        }else if (bKnight1.getxPos()==x&&bKnight1.getyPos()==y){
            return bKnight1;
        }else if (bBishop1.getxPos()==x&&bBishop1.getyPos()==y){
            return bBishop1;
        }else if (bQueen.getxPos()==x&&bQueen.getyPos()==y){
            return bQueen;
        }else if (bKing.getxPos()==x&&bKing.getyPos()==y){
            return bKing;
        }else if (bBishop2.getxPos()==x&&bBishop2.getyPos()==y){
            return bBishop2;
        }else if (bKnight2.getxPos()==x&&bKnight2.getyPos()==y){
            return bKnight2;
        }else if (bRook2.getxPos()==x&&bRook2.getyPos()==y){
            return bRook2;
        }else if (bPawn1.getxPos()==x&&bPawn1.getyPos()==y){
            return bPawn1;
        }else if (bPawn2.getxPos()==x&&bPawn2.getyPos()==y){
            return bPawn2;
        }else if (bPawn3.getxPos()==x&&bPawn3.getyPos()==y){
            return bPawn3;
        }else if (bPawn4.getxPos()==x&&bPawn4.getyPos()==y){
            return bPawn4;
        }else if (bPawn5.getxPos()==x&&bPawn5.getyPos()==y){
            return bPawn5;
        }else if (bPawn6.getxPos()==x&&bPawn6.getyPos()==y){
            return bPawn6;
        }else if (bPawn7.getxPos()==x&&bPawn7.getyPos()==y){
            return bPawn7;
        }else if (bPawn8.getxPos()==x&&bPawn8.getyPos()==y){
            return bPawn8;
        }
        return null;
    }
}
