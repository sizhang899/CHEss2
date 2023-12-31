
import java.lang.Math;

public class Knight extends Peice {
    public Knight (String c, int n, int x, int y, Peice[][] pA, String COLOR) {
        super("Knight", c, n, x, y, pA, COLOR);
    }

    @Override
    public void skroinkPeices(Peice[][] pA) {
        peiceArr = pA;
    }


    public void checkShouldRender(int x, int y) {
        if (!(peiceArr[yPos][xPos].getColor().equals(color))) {
            // shouldRender = false;
        }
    }

    public boolean getShouldRender() {
        return shouldRender;
    }

    // checks if the selected square is one of the squares that the knight can move to (look up "Knight movement chess" if you want a visual)
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {

        if (Math.abs(cX - dX) == 1 && Math.abs(cY - dY) == 2) {
            super.xPos = dX;
            super.yPos = dY;
            return true;
        } else if (Math.abs(cX - dX) == 2 && Math.abs(cY - dY) == 1) {
            super.xPos = dX;
            super.yPos = dY;
            return true;
        } else if (Math.abs(cX + dX) == 1 && Math.abs(cY + dY) == 2) {
            super.xPos = dX;
            super.yPos = dY;
            return true;
        } else if (Math.abs(cX + dX) == 2 && Math.abs(cY + dY) == 1) {
            super.xPos = dX;
            super.yPos = dY;
            return true;
        } else {
            System.out.println("invalid knight move!");
            return false;
        }
    }
    public boolean validCapture(int dX, int dY, int cX, int cY, String board[][]) {//knight capture and move logic is the same

        return validMove(dX, dY, cX, cY, board);
    }
}