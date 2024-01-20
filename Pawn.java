public class Pawn extends Peice {
    public Pawn (String c, int n, int x, int y, String COLOR) {
        super("Pawn", c, n, x, y, COLOR);
    }



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
                } else return (cY - dY == -2) && (cX - dX == 0);

            } else {
                return false;
            }


        } catch (Exception e) {
            return false;
        }
    }

    public boolean validCapture(int dX, int dY, int cX, int cY, String board[][]) {
        if ((cY - dY == 1 &&(cX - dX == 1 || cX - dX == -1) )&&COLOR.equals("b")){
            return true;
        }else if ((cY - dY == -1 &&(cX - dX == 1 || cX - dX == -1) )&&COLOR.equals("w")){
            return true;
        }
        return  false;
    }
    public boolean validCaptureCheck(int dX, int dY, int cX, int cY, String board[][]) {
        if ((cY - dY == 1 &&(cX - dX == 1 || cX - dX == -1) )&&COLOR.equals("b")){
            return true;
        }else if ((cY - dY == -1 &&(cX - dX == 1 || cX - dX == -1) )&&COLOR.equals("w")){
            return true;
        }
        return  false;
    }
}
