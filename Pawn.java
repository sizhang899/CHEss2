import javax.swing.*;

public class Pawn extends Peice {
    String color;
    public Pawn (String c, int n, int x, int y, Peice[][] pA, String COLOR) {
        super("Pawn", c, n, x, y, pA, COLOR);
        color = c;
    }

    @Override
    public void skroinkPeices(Peice[][] pA) {
        peiceArr = pA;
    }

    public void checkShouldRender(int x, int y) {
        if (x < 0 || y < 0)
            return;

        if (!(peiceArr[x][y].getColor().equals(color))) {
            // shouldRender = false;
        }
    }

    public boolean getShouldRender() {
        return shouldRender;
    }

    boolean hasntMoved = true;

    // sees if a pawn hasn't moved, if it has it *can* move 2 squares in the direction of its color and otherwise only 1
    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {//no need to check for pieces blocking because the pawn moves 1 space and that space cant be occupied by other piece oterwise it goes to validCapture method
        try {

            // Move one or two squares forward
            if ((cY - dY == 1&&COLOR.equals("b"))&&(cX-dX==0)) {
                return true;
            } else if ((cY - dY == -1&&COLOR.equals("w"))&&(cX-dX==0)) {
                return true;
            } else if (yPos==1&&COLOR.equals("b")||(yPos==6&&COLOR.equals("w"))) {//checks to see if pawn is in starting position
                if ((cY - dY == 2)&&(cX-dX==0)){
                    return true;
                } else if ((cY - dY == -2)&&(cX-dX==0)) {
                    return true;
                }else {

                    return false;
                }

            } else {
                return false;
            }



            // Add logic for en passant, pawn promotion, and other edge cases


        } catch (Exception e) {
            return false;
        }
    }
    public boolean validCapture(int dX, int dY, int cX, int cY, String board[][]) {
        if ((cY - dY == 1 || cY - dY == -1) && (cX - dX == 1 || cX - dX == -1)) {
            return true;
        }else {
            return false;
        }
    }
    public String toString(){
        return color;
    }
}