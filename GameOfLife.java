import java.util.Arrays;

public class GameOfLife implements Board {

    // Integers: 0 or 1 for alive or dead
    private int[][] board;
    private int[][] genAlpha;

    public GameOfLife(int x, int y)
    {
        board = new int[x][y];
        genAlpha = new int[x][y];

    }

    // Set values on the board
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
        print();
    }

    // Run the simulation for a number of turns
    public void run(int turns) {
        int currentStep = 1;
        while(currentStep <= turns){
            step();
            currentStep++;
        }

    }

    // Step the simulation forward one turn.
    public void step()
    {

        for (int i =0; i< board.length; i++){
            for (int j=0; j< board[0].length; j++){
                int neighbors = countNeighbors(i, j);

                if(neighbors<2){
                    genAlpha[i][j] = 0;
                }
                else if((neighbors==2 || neighbors==3) && board[i][j]==1){
                    genAlpha[i][j]= 1;
                }
                else if (neighbors ==3 && board[i][j]==0){
                    genAlpha[i][j]=1;
                }
                else if(neighbors>3){
                    genAlpha[i][j]=0;
                }
            }
        }
        board = genAlpha;
        print();
        
    }


    public int countNeighbors(int x, int y) {
        int count=0;
        if(get(x-1,y-1)==1){
            count++;
        }
        if(get(x-1,y)==1){
            count++;
        }
        if(get(x-1,y+1)==1){
            count++;
        }
        if(get(x,y-1)==1){
            count++;
        }
        if(get(x,y+1)==1){
            count++;
        }
        if(get(x+1,y-1)==1){
            count++;
        }
        if(get(x+1,y)==1){
            count++;
        }
        if(get(x+1,y+1)==1){
            count++;
        }
        return count;
    }


    // Get a value from the board with "wrap around"
    // Locations outside the board will loop back into the board.
    // Ex: -1 will read board.length-1
    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x+xLimit)%xLimit][(y+yLimit)%yLimit];
    }

    // Test helper to get the whole board state
    public int[][] get()
    {
        return board;
    }

    // Test helper to print the current state
    public void print(){
        // Print the header
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
