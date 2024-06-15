import java.util.ArrayList;

import static java.lang.Math.*;

public class A_star {
    int gridSizeX;
    int gridSizeY;
    int gridSpacing = 10;
    int gridSize;
    int start_index;
    int end_index;
    ArrayList<Place> grid = new ArrayList<Place>();

    public A_star(int sizeX, int sizeY, int start, int end){
        this.gridSizeX = sizeX;
        this.gridSizeY = sizeY;
        this.gridSize = sizeX * sizeY;
        for(int i = 0; i < gridSizeX*gridSizeY; i++){
            grid.add(new Place(i*gridSpacing,(i % gridSizeY)*gridSpacing,0));
        }
        grid.get(start).setType(1);
        grid.get(end).setType(2);
    }

    public void run(){
        Place current = grid.get(start_index);
        current.open = true;
        int current_index = start_index;
        while(true){
            current_index = getSmallestIndex(grid);
            current = grid.get(current_index);

            current.open = false;
            current.closed = true;

            if(current_index == end_index){
                break;
            }

            int[] neightbor_indexes = getNeighbor(current_index);
            for(int neighbor_index : neightbor_indexes){
                if(neighbor_index== Integer.MAX_VALUE) continue; //ignore if invalid neighbor
                Place Neighbor_point = grid.get(neighbor_index);
                if(Neighbor_point.type == 3 && Neighbor_point.closed){
                    continue;
                }
                if()

            }
        }
    }

    private float calculateFCost(Place actual,Place start, Place destination){
        float g_cost = sqrt((float)(pow(start.posX-actual.posX,2)+pow(start.posY-actual.posY,2)));
        float h_cost = sqrt((float)(pow(destination.posX-actual.posX,2)+pow(destination.posY-actual.posY,2));
        return g_cost+h_cost;

    }

    private int getSmallestIndex(ArrayList<Place> list){
        int smallest_index = 0;
        float smallest_value = Integer.MAX_VALUE;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).open) {
                float act_cost = list.get(i).f_cost;
                if (act_cost < smallest_value) {
                    smallest_value = act_cost;
                    smallest_index = i;
                }
            }
        }
        return smallest_index;
    }

    private int[] getNeighbor(int actual_index){
        int[] neighborIndex = {0,0,0,0,0,0,0,0};
        int remainder = actual_index % gridSizeX;

        neighborIndex[0] = actual_index-gridSizeX-1;
        neighborIndex[1] = actual_index-gridSizeX;
        neighborIndex[2] = actual_index-gridSizeX+1;
        neighborIndex[3] = actual_index-1;
        neighborIndex[4] = actual_index+1;
        neighborIndex[5] = actual_index+gridSizeX-1;
        neighborIndex[6] = actual_index+gridSizeX;
        neighborIndex[7] = actual_index+gridSizeX+1;
        //Check if on top
        if(actual_index < gridSizeX){
            neighborIndex[0] = Integer.MAX_VALUE;
            neighborIndex[1] = Integer.MAX_VALUE;
            neighborIndex[2] = Integer.MAX_VALUE;
        }
        //Check on bottom
        if(actual_index > gridSize-gridSizeX){
            neighborIndex[5] = Integer.MAX_VALUE;
            neighborIndex[6] = Integer.MAX_VALUE;
            neighborIndex[7] = Integer.MAX_VALUE;
        }
        //check if on left Side
        if(remainder == 0){
            neighborIndex[0] = Integer.MAX_VALUE;
            neighborIndex[3] = Integer.MAX_VALUE;
            neighborIndex[5] = Integer.MAX_VALUE;
        }
        //check if on right Size
        if(remainder == gridSizeX-1){
            neighborIndex[2] = Integer.MAX_VALUE;
            neighborIndex[4] = Integer.MAX_VALUE;
            neighborIndex[7] = Integer.MAX_VALUE;
        }

        return neighborIndex;
    }
}
