import javax.swing.*;
public class Queen extends Peice {
    public Queen (String c, int n, int x, int y, String COLOR) {
        super("Queen", c, n, x, y, COLOR);
    }
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {
        // Check if the destination is within the board bounds
        if (cX < 0 || cY < 0 || cX >= board.length || cY >= board[0].length) {
            return false;
        }

        // Calculate the movement distance in both x and y directions
        int deltaX = Math.abs(cX - dX);
        int deltaY = Math.abs(cY - dY);

        // Check if the move is diagonal or straight
        if (deltaX != 0 && deltaY != 0 && deltaX != deltaY) {
            return false; // Invalid move: Queen can move only diagonally or straight
        }

        int xDir = Integer.compare(cX, dX);
        int yDir = Integer.compare(cY, dY);

        int x = dX + xDir;
        int y = dY + yDir;

        while (x != cX || y != cY) {
            String yx = (board[y][x]);
            if (!yx.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            x += xDir;
            y += yDir;
        }

        return true; // Valid Queen move
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);
    }
    public boolean validCaptureCheck(int dX, int dY, int cX, int cY, String[][] board){//used for check without popup
        // Check if the destination is within the board bounds
        if (cX < 0 || cY < 0 || cX >= board.length || cY >= board[0].length) {
            return false;
        }

        // Calculate the movement distance in both x and y directions
        int deltaX = Math.abs(cX - dX);
        int deltaY = Math.abs(cY - dY);

        // Check if the move is diagonal or straight
        if (deltaX != 0 && deltaY != 0 && deltaX != deltaY) {
            return false; // Invalid move: Queen can move only diagonally or straight
        }
//determines the direction of the move returns 1, 0, -1

        int xDir = Integer.compare(cX, dX);
        int yDir = Integer.compare(cY, dY);
//initialize the next position in the move
        int x = dX + xDir;
        int y = dY + yDir;
//goes thorugh the path to find if there is a piece blocking
        while (x != cX || y != cY) {
            String yx = (board[y][x]);
            if (!yx.isEmpty()) {
                return false;
            }
            //goes to next square in the path
            x += xDir;
            y += yDir;
        }

        return true; // Valid Queen move
    }
    public boolean validMoveCheckMate(int dX, int dY, int cX, int cY, String[][] board){//used for check without popup
        if (!board[cY][cX].isEmpty()){
            return false;
        }
        // Check if the destination is within the board bounds
        if (cX < 0 || cY < 0 || cX >= board.length || cY >= board[0].length) {
            return false;
        }

        // Calculate the movement distance in both x and y directions
        int deltaX = Math.abs(cX - dX);
        int deltaY = Math.abs(cY - dY);

        // Check if the move is diagonal or straight
        if (deltaX != 0 && deltaY != 0 && deltaX != deltaY) {
            return false; // Invalid move: Queen can move only diagonally or straight
        }

        int xDir = Integer.compare(cX, dX);
        int yDir = Integer.compare(cY, dY);

        int x = dX + xDir;
        int y = dY + yDir;

        while (x != cX || y != cY) {
            String yx = (board[y][x]);
            if (!yx.isEmpty()) {
                return false;
            }
            x += xDir;
            y += yDir;
        }

        return true; // Valid Queen move
    }
}
