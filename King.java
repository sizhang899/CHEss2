
public class King extends Peice {
    public boolean kingHasmoved = false;
    public King (String c, int n, int x, int y, Peice[][] pA, String COLOR) {
        super("King", c, n, x, y, pA, COLOR);
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

    // checks to find if the selected square is within one block of the king.
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {
        if (!kingHasmoved){

        }
        int deltaX = Math.abs(dX - cX);
        int deltaY = Math.abs(dY - cY);
        if ((deltaX == 1 || deltaY == 1) && (deltaX < 2 && deltaY < 2)) {
            super.xPos = dX;
            super.yPos = dY;
            kingHasmoved = true;
            return true;
        } else {
            super.xPos = dX;
            super.yPos = dY;
            kingHasmoved = true;
            return false;
        }
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);
    }
}