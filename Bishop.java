import javax.swing.*;
import java.lang.Math;

public class Bishop extends Peice {
    public Bishop (String c, int n, int x, int y, String COLOR) {
        super("Bishop", c, n, x, y, COLOR);
    }

    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {

        int deltaX = Math.abs(cX - dX);
        int deltaY = Math.abs(cY - dY);

        if (deltaX != deltaY) {
            return false;
        }

        // Determine the direction of movement
        int xDir = Integer.compare(cX, dX);
        int yDir = Integer.compare(cY, dY);
//initialize the first position in the move
        int x = dX + xDir;
        int y = dY + yDir;

        // Check for pieces blocking the bishop's path
        while (x != cX && y != cY) {
            String yx = (board[y][x]);
            if (!yx.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);

                return false; // There's a piece blocking the bishop's path
            }
            //goes to next square in the path
            x += xDir;
            y += yDir;
        }

        return true; // Move is valid - no pieces blocking the path
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);
    }
    public boolean validCaptureCheck(int dX, int dY, int cX, int cY, String[][] board) {
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
            if (!yx.isEmpty()) {
                return false; // There's a piece blocking the bishop's path
            }
            x += xDir;
            y += yDir;
        }

        return true; // Move is valid - no pieces blocking the path
    }
    public boolean validMoveCheckMate(int dX, int dY, int cX, int cY, String[][] board) {
        if (!board[cY][cX].isEmpty()){
            return false;
        }
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
            if (!yx.isEmpty()) {

                return false; // There's a piece blocking the bishop's path
            }
            x += xDir;
            y += yDir;
        }

        return true; // Move is valid - no pieces blocking the path
    }

}
