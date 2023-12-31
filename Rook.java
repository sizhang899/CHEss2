import javax.swing.*;
import javax.swing.JOptionPane;

public class Rook extends Peice {
    public Rook (String c, int n, int x, int y, Peice[][] pA, String COLOR) {
        super("Rook", c, n, x, y, pA, COLOR);
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

    // checks if the selected square is either directly vertical or horizontal of the rook's current position
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {
//        System.out.println("Wow, you moved a rook!");
//
//        if ((dX == cX && dY != cY)) {
//            int length = dY - cY;
//            int length 
//            super.xPos = dX;
//            super.yPos = dY;
//            return true;
//        } else if ((dY == cY && dX != cX)){
//            super.xPos = dX;
//            super.yPos = dY;
//            return true;
//        } else {
//            return false;
//        }


        // Check if the destination is either along the same row or the same column
        if (dX != cX && dY != cY) {
            return false;
        }

        int deltaX = Integer.compare(cX, dX);
        int deltaY = Integer.compare(cY, dY);

        int x = dX + deltaX;
        int y = dY + deltaY;

        while (x != cX || y != cY) {
            String yx = (board[y][x]);
            if ((yx)!="") {//why does this not work
                // There's a piece blocking the rook's path
                JOptionPane.showMessageDialog(null, "Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);

                return false;
            }
            x += deltaX;
            y += deltaY;
        }

        // Destination is valid, no pieces blocking the path
        return true;
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String board[][]) {
        return validMove(dX, dY, cX, cY, board);
    }

}