
import javax.swing.*;
import java.lang.Math;

public class Bishop extends Peice {
    public Bishop (String c, int n, int x, int y, Peice[][] pA, String COLOR) {
        super("Bishop", c, n, x, y, pA, COLOR);
    }

    @Override
    public void skroinkPeices(Peice[][] pA) {
        peiceArr = pA;
    }

    public void checkShouldRender(int x, int y) {
        if (!(peiceArr[yPos][xPos].getColor().equals(color))) {
            shouldRender = false;
        }
    }

    public boolean getShouldRender() {
        return shouldRender;
    }

    // does some equation stuff to find if the selected square is on the same diagonal as the bishop is (I wrote this over a month ago ok)
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {
//        System.out.println("Wow, you moved a bishop!");
//        int deltaX = Math.abs(dX - cX);
//        int deltaY = Math.abs(dY - cY);
//        if (deltaX == deltaY) {
//            super.xPos = dX;
//            super.yPos = dY;
//            return true;
//        } else {
//            System.out.println("invalid bishop move!");
//            return false;
//        }


        // Check if the move is along a diagonal path
        int deltaX = Math.abs(cX - dX);
        int deltaY = Math.abs(cY - dY);

        if (deltaX != deltaY) {
            return false;
        }

        // Determine the direction of movement
        int xDir = Integer.compare(cX, dX);
        int yDir = Integer.compare(cY, dY);

        int x = dX + xDir;
        int y = dY + yDir;

        // Check for pieces blocking the bishop's path
        while (x != cX && y != cY) {
            String yx = (board[y][x]);
            if ((yx)!="") {
                JOptionPane.showMessageDialog(null, "Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);

                return false; // There's a piece blocking the bishop's path
            }
            x += xDir;
            y += yDir;
        }

        return true; // Move is valid - no pieces blocking the path
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);
    }
}