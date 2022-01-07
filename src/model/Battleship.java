package model;

import model.classes.board.Board;
import model.classes.ships.Ship;


import java.util.ArrayList;

public class Battleship {
    public static void main(String[] args){
        final int ARRAY_POSITION_FOR_LINE = 0;
        final int ARRAY_POSITION_FOR_COLUMN = 1;

        Board board = new Board(11, 11);
        board.boardGenerator();
        board.showBoard();
        Ship[] ship =new Ship[]{new Ship("Navio 1", 1), new Ship("Navio 2", 1), new Ship("Navio 3", 2), new Ship("Navio 4", 2),
                new Ship("Navio 5", 3), new Ship("Navio 6", 3), new Ship("Navio 7", 4)};
        for(int i = 0; i < ship.length; i++) {
            boolean isEmpty = false;
            while (isEmpty == false) {
                ship[i].changeInitialLine();
                ship[i].changeInitialColumn();
                if (ship[i].getShipLength() > 1) {
                    ;
                    ArrayList options = board.setOptionsForShipFinalPosition(ship[i].getInitialLine(), ship[i].getInitialColumn(), ship[i].getShipLength());
                    board.printOptionsOnConsole(options);
                    int[] option = board.chooseOption(options);
                    ship[i].setFinalLine(option[ARRAY_POSITION_FOR_LINE]);
                    ship[i].setFinalColumn(option[ARRAY_POSITION_FOR_COLUMN]);
                }

                ship[i].setArrayWithShipAllPositions();
                isEmpty = verifyIfThePositionIsEmpty(ship[i].getShipAllPositions(), board.getPlayerBoard());
            }
            board.putShipOnTheBoard(ship[i].getShipAllPositions(), board.getPlayerBoard());
            board.showBoard();
        }
    }

    public static boolean verifyIfThePositionIsEmpty(int[][] shipAllPositions, String[][] boardMatrix){
        final int ARRAY_POSITION_FOR_LINE = 0;
        final int ARRAY_POSITION_FOR_COLUMN = 1;
        for (int[] position : shipAllPositions) {
            if (boardMatrix[position[ARRAY_POSITION_FOR_LINE]][position[ARRAY_POSITION_FOR_COLUMN]] != ".") {
                System.out.println("Uma das posições já está ocupada. Insira as posições deste navio novamente.");
                return false;
            }
        }
        return true;
    }
}
