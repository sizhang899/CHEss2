public abstract class Peice {
    protected String peice;
    protected String color;
    protected String COLOR;
    protected int xPos;
    protected int yPos;
    protected int peiceNumber;

    public Peice (String p, String c, int n, int x, int y, String COLOR) {
        peice = p;
        color = c;
        peiceNumber = n;
        xPos = x;
        yPos = y;

        this.COLOR = COLOR;
    }
    public int getxPos(){
        return xPos;
    }
    public int getyPos(){
        return yPos;
    }



    public String getcolor(){
        return color;
    }
    public String getCOLOR(){
        return COLOR;
    }

    public String toString() {
        return color;
    }
    public void MovePiece(int x, int y){
        xPos = x;
        yPos = y;
    }


    public abstract boolean validMove(int dX, int dY, int cX, int cY,String board[][]);
    public boolean validCapture(int dX, int dY, int cX, int cY, String board[][]){
        return false;
    }



}
