import javax.swing.*;

public class King extends Peice {
    public boolean kingHasmoved = false;
    public King (String c, int n, int x, int y, String COLOR) {
        super("King", c, n, x, y, COLOR);
    }


    @Override
    public boolean validMove(int dX, int dY, int cX, int cY, String board[][]) {

        int deltaX = Math.abs(dX - cX);
        int deltaY = Math.abs(dY - cY);
        if ((deltaX == 1 || deltaY == 1) && (deltaX < 2 && deltaY < 2)) {
            super.xPos = dX;
            super.yPos = dY;
            kingHasmoved = true;
            return true;
        } else {
            return false;
        }
    }
    public boolean validMoveCheckMate(int dX, int dY, int cX, int cY, String board[][]) {//difference is that this one checks if the space is empty

        int deltaX = Math.abs(dX - cX);
        int deltaY = Math.abs(dY - cY);
        if ((deltaX == 1 || deltaY == 1) && (deltaX < 2 && deltaY < 2) && board[cY][cX].isEmpty()) {
            super.xPos = dX;
            super.yPos = dY;
            kingHasmoved = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validCapture(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);
    }

    @Override
    public boolean validCaptureCheck(int dX, int dY, int cX, int cY, String[][] board) {
        return validMove(dX, dY, cX, cY, board);

    }

    public boolean validCastle(int dX, int dY, int cX, int cY, String[][] board){
        if (!kingHasmoved){
            if ((cX==2)&&(cY==0)&&(dX==4)&&(dY==0)&&(COLOR.equals("b"))&&(board[0][0].equals("♜"))) {
                if (board[0][1].isEmpty()&&board[0][2].isEmpty()&&board[0][3].isEmpty()){
                    kingHasmoved = true;
                    return true;
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid castle, Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            } else if ((cX==6)&&(cY==0)&&(dX==4)&&(dY==0)&&(COLOR.equals("b"))&&(board[0][7].equals("♜"))) {
                if (board[0][5].isEmpty()&&board[0][6].isEmpty()){
                    kingHasmoved = true;

                    return true;
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid castle, Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }else if ((cX==2)&&(cY==7)&&(dX==4)&&(dY==7)&&(COLOR.equals("w"))&&(board[7][0].equals("♖"))) {
                if (board[7][1].isEmpty()&&board[7][2].isEmpty()&&board[7][3].isEmpty()){
                    kingHasmoved = true;
                    return true;
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid castle, Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            } else if ((cX==6)&&(cY==7)&&(dX==4)&&(dY==7)&&(COLOR.equals("w"))&&(board[7][7].equals("♖"))) {
                if (board[7][5].isEmpty()&&board[7][6].isEmpty()){
                    kingHasmoved = true;

                    return true;
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid castle, Piece in the way", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid castle, King moved already", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }else {
            JOptionPane.showMessageDialog(null, "Invalid castle, King has already moved", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
