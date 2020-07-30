package service;

import model.Generation;

public class GameServiceImpl implements GameService {

    //the inspected cell coordinates
    private int y, x;
    //The number of turns we inspect the cell
    private int numOfTurns;
    //keep track of how many times the cell was green
    private int greenStateCount;
    private Generation currentGen;


   public GameServiceImpl(int x, int y, int turns, Generation generationZero){
       this.y = y;
       this.x = x;
       this.numOfTurns = turns;
       this.currentGen = generationZero;

       if(generationZero.getCell(y,x) == 0){
           this.greenStateCount = 0;
       } else {
           this.greenStateCount = 1;
       }

   }


   @Override
   public void goThroughGens(){

       for(int i = 1; i <= numOfTurns; i++){

           Generation nextGeneration = new Generation(currentGen.getY(), currentGen.getX());
           formNextGen(nextGeneration);
           updateGreenStateCount(nextGeneration);
           currentGen = nextGeneration;

       }
   }

   @Override
   public void printGreenStateCount(){
        System.out.println(greenStateCount);
    }

    @Override
    public void updateGreenStateCount(Generation nextGeneration){
       if(nextGeneration.getCell(y, x) == 1){
           greenStateCount++;
       }
   }

   @Override
   public void formNextGen(Generation nextGeneration) {
       for(int currRow = 0; currRow < currentGen.getY(); currRow++){
           for(int currColumn = 0; currColumn < currentGen.getX(); currColumn++){

               if(becomesGreen(currRow, currColumn)){
                   nextGeneration.setGreenCell(currRow, currColumn);
               }

           }
       }
   }

   @Override
   public boolean becomesGreen(int y, int x){
       int greenNeighbours = countGreenNeighbours(y, x);

       //red in current gen
       if(currentGen.getGrid()[y][x] == 0) {
           switch(greenNeighbours){
               case 3: case 6:
                   return true;
           }
       }

       //green in current gen
       else {
           switch (greenNeighbours){
               case 2: case 3: case 6:
                   return true;
           }
       }

       return false;
   }

   @Override
   public int countGreenNeighbours(int y, int x) {
       int greenNeighbours = 0;

       //top left
       if(y - 1 >= 0 && x - 1 >= 0 && currentGen.getCell(y - 1, x - 1) == 1) {
           greenNeighbours++;
       }
       //left
       if(x - 1 >= 0 && currentGen.getCell(y, x - 1) == 1){
           greenNeighbours++;
       }

       //bottom left
       if(y + 1 < currentGen.getY() && x - 1 >= 0 && currentGen.getCell(y + 1, x - 1) == 1){
           greenNeighbours++;
       }

       //top
       if(y - 1 >= 0 && currentGen.getCell(y - 1, x) == 1){
           greenNeighbours++;
       }

       //bottom
       if(y + 1 < currentGen.getY() && currentGen.getCell(y + 1, x) == 1){
           greenNeighbours++;
       }

       //top right
       if(y - 1 >= 0 && x + 1 < currentGen.getX() && currentGen.getCell(y - 1, x + 1) == 1){
           greenNeighbours++;
       }

       //right
       if(x + 1 < currentGen.getX() && currentGen.getCell(y, x + 1) == 1){
           greenNeighbours++;
       }

       //bottom right
       if(y + 1 < currentGen.getY() && x + 1 < currentGen.getX() && currentGen.getCell(y + 1, x + 1) == 1){
           greenNeighbours++;
       }

       return greenNeighbours;
   }

}