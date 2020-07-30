package service;

import model.Generation;

public interface GameService {

    void goThroughGens();

    void printGreenStateCount();

    void updateGreenStateCount(Generation nextGeneration);

    void formNextGen(Generation nextGeneration);

    boolean becomesGreen(int y, int x);

    int countGreenNeighbours(int y, int x);

}
