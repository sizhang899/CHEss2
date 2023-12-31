
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.JFrame;

public class Chessboard extends JFrame implements ActionListener {
    Peice peice;
    String playerTurn = "w";
    int oldClickedRow;
    int oldClickedCol;
    Peice[][] peiceArr = new Peice[8][8];
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 100;

    private JFrame frame;
    private JButton[][] squares;
    private String[][] boardState;

    Rook wRook1 = new Rook("♜", 1, 0, 0, peiceArr, "b");
    Knight wKnight1 = new Knight("♞", 1, 1, 0, peiceArr, "b");
    Bishop wBishop1 = new Bishop("♝", 1, 2, 0, peiceArr, "b");
    Queen wQueen = new Queen("♛", 1, 3, 0, peiceArr, "b");
    King wKing = new King("♚", 1, 4, 0, peiceArr, "b");
    Bishop wBishop2 = new Bishop("♝", 2, 5, 0, peiceArr, "b");
    Knight wKnight2 = new Knight("♞", 2, 6, 0, peiceArr, "b");
    Rook wRook2 = new Rook("♜", 2, 7, 0, peiceArr, "b");

    Pawn wPawn1 = new Pawn("♟", 1, 0, 1, peiceArr, "b");
    Pawn wPawn2 = new Pawn("♟", 2, 1, 1, peiceArr, "b");
    Pawn wPawn3 = new Pawn("♟", 3, 2, 1, peiceArr, "b");
    Pawn wPawn4 = new Pawn("♟", 4, 3, 1, peiceArr, "b");
    Pawn wPawn5 = new Pawn("♟", 5, 4, 1, peiceArr, "b");
    Pawn wPawn6 = new Pawn("♟", 6, 5, 1, peiceArr, "b");
    Pawn wPawn7 = new Pawn("♟", 7, 6, 1, peiceArr, "b");
    Pawn wPawn8 = new Pawn("♟", 8, 7, 1, peiceArr, "b");

    Rook bRook1 = new Rook("♖", 1, 0, 7, peiceArr, "w");
    Knight bKnight1 = new Knight("♘", 1, 1, 7, peiceArr, "w");
    Bishop bBishop1 = new Bishop("♗", 1, 2, 7, peiceArr, "w");
    Queen bQueen = new Queen("♕", 1, 3, 7, peiceArr, "w");
    King bKing = new King("♔", 1, 4, 7, peiceArr, "w");
    Bishop bBishop2 = new Bishop("♗", 2, 5, 7, peiceArr, "w");
    Knight bKnight2 = new Knight("♘", 2, 6, 7, peiceArr, "w");
    Rook bRook2 = new Rook("♖", 2, 7, 7, peiceArr, "w");

    Pawn bPawn1 = new Pawn("♙", 1, 0, 6, peiceArr, "w");
    Pawn bPawn2 = new Pawn("♙", 2, 1, 6, peiceArr, "w");
    Pawn bPawn3 = new Pawn("♙", 3, 2, 6, peiceArr, "w");
    Pawn bPawn4 = new Pawn("♙", 4, 3, 6, peiceArr, "w");
    Pawn bPawn5 = new Pawn("♙", 5, 4, 6, peiceArr, "w");
    Pawn bPawn6 = new Pawn("♙", 6, 5, 6, peiceArr, "w");
    Pawn bPawn7 = new Pawn("♙", 7, 6, 6, peiceArr, "w");
    Pawn bPawn8 = new Pawn("♙", 8, 7, 6, peiceArr, "w");



    // code not made by me

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
    //    public void mouse(MouseEvent e){
//        JButton clickedSquare = (JButton) e.getSource();
//
//    }
    public void actionPerformed(ActionEvent e) {
        if (bKing.getcolor()==""){
            JOptionPane.showMessageDialog(this, "Black WON!");
        }
        if (wKing.getcolor()==""){
            JOptionPane.showMessageDialog(this, "White WON");
        }
        JButton clickedSquare = (JButton) e.getSource();
        int clickedRow = -1;
        int clickedCol = -1;

        outerLoop:
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (clickedSquare == squares[row][col]) {
                    clickedRow = row;
                    clickedCol = col;
                    break outerLoop;
                }
            }
        }
        System.out.println(boardState[clickedRow][clickedCol]);
        try {
            if (clickedRow != -1 && clickedCol != -1) {
                if (!boardState[clickedRow][clickedCol].isEmpty()) {

                    if (playerTurn.equals(selectPiece(clickedCol, clickedRow).getCOLOR())){
                        oldClickedCol = clickedCol;
                        oldClickedRow = clickedRow;
                        peice = selectPiece(clickedCol, clickedRow);
                        System.out.println("selected peice" + peice+" " + peice.xPos + " " + peice.yPos + peice.getCOLOR());
                        System.out.println("turn = "+playerTurn);

                    } else if (!playerTurn.equals(selectPiece(clickedCol, clickedRow).getCOLOR())) {
                        selectPiece(clickedCol, clickedRow).color = "";
                        if (peice.validCapture(oldClickedCol, oldClickedRow, clickedCol, clickedRow, boardState)) {
                            peice.MovePiece(clickedCol, clickedRow);
                            System.out.println(peice.xPos + " " + peice.yPos);
                            updateBoardState(peice.getxPos(),peice.getyPos(),oldClickedCol, oldClickedRow, peice);
                            if (playerTurn.equals("w")){
                                playerTurn = "b";
                            }else {
                                playerTurn = "w";
                            }
                            System.out.println("turn = "+playerTurn);
                            peice=null;
                        }else {
                            JOptionPane.showMessageDialog(this, "Illegal capture", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                    }
                }else {
                    if (peice.validMove(oldClickedCol, oldClickedRow, clickedCol, clickedRow, boardState)) {

                        peice.MovePiece(clickedCol, clickedRow);
                        updateBoardState(peice.getxPos(),peice.getyPos(),oldClickedCol, oldClickedRow, peice);
                        if (playerTurn.equals("w")){
                            playerTurn = "b";
                        }else {
                            playerTurn = "w";
                        }
                        System.out.println("turn = "+playerTurn);
                        System.out.println(peice.xPos + " " + peice.yPos + peice.getCOLOR());

                        peice=null;
                    }else{
                        JOptionPane.showMessageDialog(this, "Illegal move", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Select piece first, or right color", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void initializePieces() {
        boolean wKingAlive = false;
        boolean bKingAlive = false;


        // intantciating (I hope I spelled that right) all the peices
        Rook wRook1 = new Rook("♖", 1, 0, 0, peiceArr, "w");
        Knight wKnight1 = new Knight("♘", 1, 1, 0, peiceArr, "w");
        Bishop wBishop1 = new Bishop("♗", 1, 2, 0, peiceArr, "w");
        Queen wQueen = new Queen("♕", 1, 3, 0, peiceArr, "w");
        King wKing = new King("♔", 1, 4, 0, peiceArr, "w");
        Bishop wBishop2 = new Bishop("♗", 2, 5, 0, peiceArr, "w");
        Knight wKnight2 = new Knight("♘", 2, 6, 0, peiceArr, "w");
        Rook wRook2 = new Rook("♖", 2, 7, 0, peiceArr, "w");

        Pawn wPawn1 = new Pawn("♙", 1, 0, 1, peiceArr, "w");
        Pawn wPawn2 = new Pawn("♙", 2, 1, 1, peiceArr, "w");
        Pawn wPawn3 = new Pawn("♙", 3, 2, 1, peiceArr, "w");
        Pawn wPawn4 = new Pawn("♙", 4, 3, 1, peiceArr, "w");
        Pawn wPawn5 = new Pawn("♙", 5, 4, 1, peiceArr, "w");
        Pawn wPawn6 = new Pawn("♙", 6, 5, 1, peiceArr, "w");
        Pawn wPawn7 = new Pawn("♙", 7, 6, 1, peiceArr, "w");
        Pawn wPawn8 = new Pawn("♙", 8, 7, 1, peiceArr, "w");

        Rook bRook1 = new Rook("♜", 1, 0, 7, peiceArr, "b");
        Knight bKnight1 = new Knight("♞", 1, 1, 7, peiceArr, "b");
        Bishop bBishop1 = new Bishop("♝", 1, 2, 7, peiceArr, "b");
        Queen bQueen = new Queen("♛", 1, 3, 7, peiceArr, "b");
        King bKing = new King("♚", 1, 4, 7, peiceArr, "b");
        Bishop bBishop2 = new Bishop("♝", 2, 5, 7, peiceArr, "b");
        Knight bKnight2 = new Knight("♞", 2, 6, 7, peiceArr, "b");
        Rook bRook2 = new Rook("♜", 2, 7, 7, peiceArr, "b");

        Pawn bPawn1 = new Pawn("♟", 1, 0, 6, peiceArr, "b");
        Pawn bPawn2 = new Pawn("♟", 2, 1, 6, peiceArr, "b");
        Pawn bPawn3 = new Pawn("♟", 3, 2, 6, peiceArr, "b");
        Pawn bPawn4 = new Pawn("♟", 4, 3, 6, peiceArr, "b");
        Pawn bPawn5 = new Pawn("♟", 5, 4, 6, peiceArr, "b");
        Pawn bPawn6 = new Pawn("♟", 6, 5, 6, peiceArr, "b");
        Pawn bPawn7 = new Pawn("♟", 7, 6, 6, peiceArr, "b");
        Pawn bPawn8 = new Pawn("♟", 8, 7, 6, peiceArr, "b");

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