import model.Generation;
import service.GameServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Application {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        //get grid size
        int x = Integer.parseInt(tokenizer.nextToken()); //width
        int y = Integer.parseInt(tokenizer.nextToken());  // height

        if(x > y || y >= 1000) {
            throw new NumberFormatException("Constraint violation.");
        }

        Generation generationZero = new Generation(y, x);

        String currLine;

        //set generation zero
        for(int row = 0; row < y; row++){
            tokenizer = new StringTokenizer(reader.readLine());
            currLine = tokenizer.nextToken();

            for(int column = 0; column < x; column++){

                if(currLine.charAt(column) != '1' && currLine.charAt(column) != '0'){
                    throw new NumberFormatException("Invalid symbol. Only 1s and 0s are permitted.");
                }

                /*When we process the input we don't need
                to set red cells because they are represented by 0
                which is the default value for int array*/
                if(currLine.charAt(column) == '1') {
                    generationZero.setGreenCell(row, column);
                }
            }
        }

        //get the cell we'd like to inspect and the number of turns
        tokenizer = new StringTokenizer(reader.readLine());
        int x1 = Integer.parseInt(tokenizer.nextToken());
        int y1 = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        //set game
        GameServiceImpl game = new GameServiceImpl(x1, y1, N, generationZero);
        game.goThroughGens();
        game.printGreenStateCount();

    }
}
