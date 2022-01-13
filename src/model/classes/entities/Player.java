package model.classes.entities;

import model.classes.enums.BoardSymbols;
import model.classes.utils.ColumnLettersGenerator;
import model.classes.utils.Input;
import model.classes.utils.RandomNumberGenerator;

import static java.util.Arrays.stream;

public class Player {
    private int playersShotsOnTarget;
    private int computersShotsOnTarget;
    private int shotsToBeHit;

    public Player(){
        this.playersShotsOnTarget = 0;
        this.computersShotsOnTarget = 0;
        this.shotsToBeHit = 16;

    }

    public int getPlayersShotsOnTarget() {
        return playersShotsOnTarget;
    }

    public int getComputersShotsOnTarget() {
        return computersShotsOnTarget;
    }

    public  int getShotsToBeHit() {
        return shotsToBeHit;
    }

    //    public static int setShotsToBeHit(String[] board){
//
//    }

    public boolean giveAShot(String[][] board, String typeOfPlayer){
        final int BOARD_FIRST_POSITION = 1;
        final int BOARD_LAST_POSITION = 10;
        int line = 0;
        int column = 0;
        boolean isTheShotRight = true;
        if(typeOfPlayer == "Player") {
            do {
                System.out.println("Digite a linha da posição para dar o tiro (LETRA): ");
                line = ColumnLettersGenerator.transformLetterInNumber(Input.inputChar());
            }while(line < BOARD_FIRST_POSITION || line > BOARD_LAST_POSITION);
            do{
                System.out.println("Digite a coluna da posição para dar o tiro (NÚMERO): ");
                column = Input.inputInt();
            }while(column < BOARD_FIRST_POSITION  || column > BOARD_LAST_POSITION);
            isTheShotRight = setShotOnTheBoard(board, line, column);
            this.playersShotsOnTarget = isTheShotRight ? this.playersShotsOnTarget + 1 : this.playersShotsOnTarget;
        }
        if(typeOfPlayer == "Computer") {
            line = RandomNumberGenerator.randomIntGenerator(BOARD_LAST_POSITION);
            column = RandomNumberGenerator.randomIntGenerator(BOARD_LAST_POSITION);
            isTheShotRight = setShotOnTheBoard(board, line, column);
            this.computersShotsOnTarget = isTheShotRight ? this.computersShotsOnTarget + 1 : this.computersShotsOnTarget;
        }
        return isTheShotRight;
    }

    private static boolean setShotOnTheBoard(String[][] board, int line, int column){
        String boardElement = board[line][column];
        boolean isTheShotRight = true;
        if(boardElement == BoardSymbols.SHIP.getBoardSymbol()){
            board[line][column] = BoardSymbols.RIGHT_SHOTS.getBoardSymbol();
            isTheShotRight = true;
        }
        if(boardElement == BoardSymbols.SEA.getBoardSymbol()){
            board[line][column] = BoardSymbols.WRONG_SHOTS.getBoardSymbol();
            isTheShotRight = false;
        }
        if(boardElement == BoardSymbols.RIGHT_SHOTS.getBoardSymbol() || boardElement == BoardSymbols.WRONG_SHOTS.getBoardSymbol()){
            isTheShotRight = false;
        }
        return  isTheShotRight;
    }
}
