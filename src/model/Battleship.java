package model;

import model.classes.board.Board;
import model.classes.ships.Ship;

import java.util.ArrayList;

public class Battleship {
    public static void main(String[] args){

        Board board = new Board(11, 11);
        board.boardGenerator();
        board.showBoard();
        Ship[] ship =new Ship[]{new Ship("Navio 1", 1), new Ship("Navio 2", 1), new Ship("Navio 3", 2), new Ship("Navio 4", 2),
                new Ship("Navio 5", 3), new Ship("Navio 6", 3), new Ship("Navio 7", 4)};
        for(int i = 0; i < ship.length; i++) {
            ship[i].changeInitialLine();
            ship[i].changeInitialColumn();
            if (ship[i].getShipLength() > 1) {
                ;
                ArrayList options = board.showOptionsForShipFinalPosition(ship[i].getInitialLine(), ship[i].getInitialColumn(), ship[i].getShipLength());
                board.printOptionsOnConsole(options);
                int[] option = board.chooseOption(options);
                ship[i].setFinalLine(option[board.ARRAY_POSITION_FOR_LINE]);
                ship[i].setFinalColumn(option[board.ARRAY_POSITION_FOR_COLUMN]);
            }
            ship[i].setArrayWithShipAllPositions();

            board.putShipOnTheBoard(ship[i].getShipAllPositions(), board.getBoardMatrix());
            ;
            board.showBoard();
        }

    }

}
