package model;

public class Generation {

    //initial state of the grid
    private int[][] grid;
    //height (the number of rows)
    private int y;
    //width (the number of columns)
    private int x;

    public Generation(int y, int x){
        this.x = x;
        this.y = y;
        this.grid = new int[y][x];
    }


    public int getX(){
        return this.x;
    }

    public int getY(){
        return y;
    }

    public int getCell(int y, int x){
        return grid[y][x];
    }

    public void setGreenCell(int y, int x){
        this.grid[y][x] = 1;
    }
}