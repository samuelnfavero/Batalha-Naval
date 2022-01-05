package model.classes.board;

import model.classes.utils.Input;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    Scanner input = new Scanner(System.in);
    private int NUMBER_OF_LINES;
    private int NUMBER_OF_COLUMNS;
    private String [][] boardMatrix;
    public final int ARRAY_POSITION_FOR_LINE = 0;
    public final int ARRAY_POSITION_FOR_COLUMN = 1;

    public Board(int numberOfLines, int numberOfColumns){
        this.NUMBER_OF_LINES = numberOfLines;
        this.NUMBER_OF_COLUMNS = numberOfColumns;
        this.boardMatrix = new String[this.NUMBER_OF_LINES][this.NUMBER_OF_COLUMNS];
    }

    public String[][] getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardMatrix(String[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public void boardGenerator(){ //aqui não rola com função statica não sei pq, tem q ver dps
        for(int line = 0; line < this.NUMBER_OF_LINES; line++){
            for(int column = 0; column < this.NUMBER_OF_COLUMNS; column++){
                if (column == 0) {
                    this.boardMatrix[line][column] = String.valueOf(line);
                } else if (line == 0){
                    this.boardMatrix[line][column] = String.valueOf(column);
                } else {
                    this.boardMatrix[line][column] = ".";
                }
            }
        }
    }
    public void showBoard(){

        for(int line = 0; line < boardMatrix.length; line++){
            for(int column = 0; column < boardMatrix[line].length; column++){
                System.out.print("|" + boardMatrix[line][column]);
            }
            System.out.println("|");

        }

    }

    public ArrayList<int[]> showOptionsForShipFinalPosition(int initialLine, int initialColumn, int shipLength){
        int[] option1 = (initialLine - (shipLength - 1)) >= 0 ? new int[] {(initialLine - (shipLength - 1)), initialColumn} : new int[]{0};
        int[] option2 = (initialLine + (shipLength-1)) < this.boardMatrix.length ? new int[] {(initialLine + (shipLength-1)), initialColumn} : new int[] {0};
        int[] option3 = (initialColumn - (shipLength - 1)) >= 0? new int[] {initialLine, (initialColumn - (shipLength - 1))} : new int[] {0};
        int[] option4 = (initialColumn + (shipLength - 1)) < this.boardMatrix.length ? new int[] {initialLine, (initialColumn + (shipLength - 1))} : new int[] {0};
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
            System.out.println("Opção " + (i + 1) +": [" + Integer.toString(options.get(i)[this.ARRAY_POSITION_FOR_LINE]) + "," + Integer.toString(options.get(i)[this.ARRAY_POSITION_FOR_COLUMN]) + "]");
            }
        }


    public static int[] chooseOption(ArrayList<int[]> options){
        System.out.println("Digite a opção escolhida:");
        int option = (Input.inputInt() - 1);
        while(option >= options.size()){
            System.out.println("Opção Inválida. Digite a opção escolhida:");
            option = (Input.inputInt() - 1);
        }
        return options.get(option);
    }

    public static void putShipOnTheBoard(int[][] allPositionsOfShip, String[][] boardMatrix){
        for(int i = 0; i < allPositionsOfShip.length; i++){
            boardMatrix[allPositionsOfShip[i][0]][allPositionsOfShip[i][1]] = "S";
        }
    }


}
