import javax.swing.*;
public class Rook extends Peice {
    boolean RookHasMoved = false;
    public Rook (String c, int n, int x, int y, String COLOR) {
        super("Rook", c, n, x, y, COLOR);
    }

    // checks if the selected square is either directly vertical or horizontal of the rook's current position
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {
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
            if (!yx.isEmpty()) {
                // There's a piece blocking the rook's path
                JOptionPane.showMessageDialog(null, "Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);

                return false;
            }
            x += deltaX;
            y += deltaY;
        }
        RookHasMoved = true;
        // Destination is valid, no pieces blocking the path
        return true;
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String board[][]) {
        return validMove(dX, dY, cX, cY, board);
    }

    public boolean validCaptureCheck(int dX, int dY, int cX, int cY, String[][] board) {

        // Check if the destination is either along the same row or the same column
        if (dX != cX && dY != cY) {
            return false;
        }
//determines the direction of the move
        int deltaX = Integer.compare(cX, dX);//returns 1, 0 , -1
        int deltaY = Integer.compare(cY, dY);
//starts the next position based on the direction
        int x = dX + deltaX;
        int y = dY + deltaY;
//Iteration of the path until the position it wants to go to
        while (x != cX || y != cY) {
            String yx = (board[y][x]);
            if (!yx.isEmpty()) {
                // There's a piece blocking the rook's path

                return false;
            }
            //goes to next position in the path
            x += deltaX;
            y += deltaY;
        }
        RookHasMoved = true;
        // Destination is valid, no pieces blocking the path
        return true;
    }
    public boolean validMoveCheckMate(int dX, int dY, int cX, int cY, String[][] board) {

        if (!board[cY][cX].isEmpty()){
            return false;
        }
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
            if (!yx.isEmpty()) {
                // There's a piece blocking the rook's path

                return false;
            }
            x += deltaX;
            y += deltaY;
        }
        RookHasMoved = true;
        // Destination is valid, no pieces blocking the path
        return true;
    }


}
