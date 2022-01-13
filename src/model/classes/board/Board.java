package model.classes.board;

import model.classes.enums.BoardSymbols;
import model.classes.utils.ColumnLettersGenerator;
import model.classes.utils.Input;
import model.classes.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    Scanner input = new Scanner(System.in);
    private int NUMBER_OF_LINES;
    private int NUMBER_OF_COLUMNS;
    private String [][] playerBoard;
    private String[][] computerBoard;
    public final int ARRAY_POSITION_FOR_LINE = 0;
    public final int ARRAY_POSITION_FOR_COLUMN = 1;

    public Board(int numberOfLines, int numberOfColumns){
        this.NUMBER_OF_LINES = numberOfLines;
        this.NUMBER_OF_COLUMNS = numberOfColumns;
        this.playerBoard = new String[this.NUMBER_OF_LINES][this.NUMBER_OF_COLUMNS];
        this.computerBoard = new String[this.NUMBER_OF_LINES][this.NUMBER_OF_COLUMNS];
    }

    public String[][] getPlayerBoard() {
        return playerBoard;
    }

    public String[][] getComputerBoard() {
        return computerBoard;
    }


    public void boardGenerator(){
        for(int line = 0; line < this.NUMBER_OF_LINES; line++){
            for(int column = 0; column < this.NUMBER_OF_COLUMNS; column++){
                if (column == 0) {
                    char showLineLetter = ColumnLettersGenerator.transformNumberInLetter(line);
                    this.playerBoard[line][column] = String.valueOf(showLineLetter);
                    this.computerBoard[line][column] = String.valueOf(showLineLetter);
                } else if (line == 0){
                    this.playerBoard[line][column] = String.valueOf(column);
                    this.computerBoard[line][column] = String.valueOf(column);
                } else {
                    this.playerBoard[line][column] = BoardSymbols.SEA.getBoardSymbol();
                    this.computerBoard[line][column] = BoardSymbols.SEA.getBoardSymbol();
                }
            }
        }
    }
    public void showBoard(){
        System.out.println("Seu tabuleiro");
        for(int line = 0; line < playerBoard.length; line++){
            for(int column = 0; column < playerBoard[line].length; column++){
                System.out.print("|" + playerBoard[line][column]);
            }
            System.out.println("|");

        }

        System.out.println("Tabuleiro do computador");
        for(int line = 0; line < computerBoard.length; line++){
            for(int column = 0; column < computerBoard[line].length; column++){
                String showItem;
                if(computerBoard[line][column] == BoardSymbols.SHIP.getBoardSymbol()) {
                    showItem = BoardSymbols.SEA.getBoardSymbol();
                } else {
                    showItem = computerBoard[line][column];
                }
                System.out.print("|" + showItem);
            }
            System.out.println("|");

        }
    }

    public ArrayList<int[]> setOptionsForShipFinalPosition(int initialLine, int initialColumn, int shipLength){

        int[] option1 = (initialLine - (shipLength - 1)) >= 1 ? new int[] {(initialLine - (shipLength - 1)), initialColumn} : new int[]{0};
        int[] option2 = (initialLine + (shipLength-1)) < this.playerBoard.length ? new int[] {(initialLine + (shipLength-1)), initialColumn} : new int[] {0};
        int[] option3 = (initialColumn - (shipLength - 1)) >= 1? new int[] {initialLine, (initialColumn - (shipLength - 1))} : new int[] {0};
        int[] option4 = (initialColumn + (shipLength - 1)) < this.playerBoard.length ? new int[] {initialLine, (initialColumn + (shipLength - 1))} : new int[] {0};
        int[][] optionsArray = {option1, option2, option3, option4};
        ArrayList<int[]> options = new ArrayList<int[]>();

        for(int i = 0; i < optionsArray.length; i++){
            if(optionsArray[i].length > 1){
                options.add(optionsArray[i]);
            }
        }
        return options;
    }

    public void printOptionsOnConsole(ArrayList<int[]> options){
        for(int i = 0; i < options.size(); i++){
            System.out.println("Opção " + (i + 1) +": [" + ColumnLettersGenerator.transformNumberInLetter(options.get(i)[this.ARRAY_POSITION_FOR_LINE]) + "," + Integer.toString(options.get(i)[this.ARRAY_POSITION_FOR_COLUMN]) + "]");
            }
        }


    public static int[] chooseOptionForPlayer(ArrayList<int[]> options){
        System.out.println("Digite a opção escolhida:");
        int option = (Input.inputInt() - 1);
        while(option >= options.size()){
            System.out.println("Opção Inválida. Digite a opção escolhida:");
            option = (Input.inputInt() - 1);
        }
        return options.get(option);
    }

    public static int[] chooseOptionForComputer(ArrayList<int[]> options){
        int option;
        do{
            option = RandomNumberGenerator.randomIntGenerator(4) - 1;
        }while(option >= options.size());
        return options.get(option);
    }

    public static void putShipOnTheBoard(int[][] allPositionsOfShip, String[][] boardMatrix){
        for(int i = 0; i < allPositionsOfShip.length; i++){
            boardMatrix[allPositionsOfShip[i][0]][allPositionsOfShip[i][1]] = BoardSymbols.SHIP.getBoardSymbol();
        }
    }


}
