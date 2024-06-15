public class Place {
    int posX;
    int posY;
    int type; // 0 = nothing; 1 = start; 2 = end; 3 = impenetrable;

    float f_cost = Integer.MAX_VALUE;
    boolean open = false; //To be evaluated
    boolean closed = false; //Already evaluated
    int parentAddress = 0;
    public Place(int placeX, int placeY, int startType) {
        this.posX = placeX;
        this.posY = placeY;
        this.type = startType;
    }

    public void setType(int input){
        this.type = input;
    }
}
