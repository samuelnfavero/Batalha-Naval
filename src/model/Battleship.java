package model;

import model.classes.board.Board;
import model.classes.ships.Ship;

import java.util.ArrayList;

public class Battleship {
    public static void main(String[] args){

        Board board = new Board(10, 10);
        board.boardGenerator();
        board.showBoard();
        Ship ship = new Ship("Navio", 1);
        ship.changeInitialLine();
        ship.changeInitialColumn();
        if(ship.getShipLength() > 1) {
            ;
            ArrayList options = board.showOptionsForShipFinalPosition(ship.getInitialLine(), ship.getInitialColumn(), ship.getShipLength());
            board.printOptionsOnConsole(options);
            int[] option = board.chooseOption(options);
            ship.setFinalLine(option[board.ARRAY_POSITION_FOR_LINE]);
            ship.setFinalColumn(option[board.ARRAY_POSITION_FOR_COLUMN]);
        }
        ship.setArrayWithShipAllPositions();

        board.putShipOnTheBoard(ship.getShipAllPositions(), board.getBoardMatrix()); ;
        board.showBoard();


    }

}
