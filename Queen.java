import javax.swing.*;

public class Queen extends Peice {
    public Queen (String c, int n, int x, int y, Peice[][] pA, String COLOR) {
        super("Queen", c, n, x, y, pA, COLOR);
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

    // combo of rook and bishop code, as that's essentially all the queen is
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
            if ((yx)!="") {
                JOptionPane.showMessageDialog(null, "Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            x += xDir;
            y += yDir;
        }

        return true; // Valid Queen move
//        System.out.println("Wow, you moved a queen!");
//        int deltaX = Math.abs(dX - cX);
//        int deltaY = Math.abs(dY - cY);
//        if (dX == cX) {
//            super.xPos = dX;
//            super.yPos = dY;
//            return true;
//        } else if (dY == cY) {
//            super.xPos = dX;
//            super.yPos = dY;
//            return true;
//        } else if (deltaX == deltaY) {
//            super.xPos = dX;
//            super.yPos = dY;
//            return true;
//        } else {
//            System.out.println("invalid queen move!");
//            return false;
//        }
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);
    }
}