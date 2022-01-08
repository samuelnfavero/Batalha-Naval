package model;

import model.classes.board.Board;
import model.classes.player.PlayerMain;
import model.classes.ships.Ship;
import model.classes.utils.RandomNumberGenerator;


import java.util.ArrayList;

public class Battleship {
    public static void main(String[] args){
        final int ARRAY_POSITION_FOR_LINE = 0;
        final int ARRAY_POSITION_FOR_COLUMN = 1;


        Board board = new Board(11, 11);
        board.boardGenerator();
        board.showBoard();
        Ship[] playerShips =new Ship[]{new Ship("Navio 1", 1), new Ship("Navio 2", 1), new Ship("Navio 3", 2), new Ship("Navio 4", 2),
                new Ship("Navio 5", 3), new Ship("Navio 6", 3), new Ship("Navio 7", 4)};
        Ship[] computerShips =new Ship[]{new Ship("Navio 1", 1), new Ship("Navio 2", 1), new Ship("Navio 3", 2), new Ship("Navio 4", 2),
                new Ship("Navio 5", 3), new Ship("Navio 6", 3), new Ship("Navio 7", 4)};
        for(int i = 0; i < computerShips.length; i++) {
            boolean isEmpty;
            do {
                computerShips[i].setInitialLine(RandomNumberGenerator.randomIntGenerator(10));
                computerShips[i].setInitialColumn(RandomNumberGenerator.randomIntGenerator(10));
                if (computerShips[i].getShipLength() > 1) {
                    ArrayList options = board.setOptionsForShipFinalPosition(computerShips[i].getInitialLine(), computerShips[i].getInitialColumn(), computerShips[i].getShipLength());
                    int[] option = board.chooseOptionForComputer(options);
                    computerShips[i].setFinalLine(option[ARRAY_POSITION_FOR_LINE]);
                    computerShips[i].setFinalColumn(option[ARRAY_POSITION_FOR_COLUMN]);
                }
                computerShips[i].setArrayWithShipAllPositions();
                isEmpty = verifyIfThePositionIsEmpty(computerShips[i].getShipAllPositions(), board.getComputerBoard(), "Computer");
            }while (isEmpty == false);
            board.putShipOnTheBoard(computerShips[i].getShipAllPositions(), board.getComputerBoard());
        }

        for(int i = 0; i < playerShips.length; i++) {
            boolean isEmpty;
            do {
                playerShips[i].changeInitialLine();
                playerShips[i].changeInitialColumn();
                if (playerShips[i].getShipLength() > 1) {
                    ArrayList options = board.setOptionsForShipFinalPosition(playerShips[i].getInitialLine(), playerShips[i].getInitialColumn(), playerShips[i].getShipLength());
                    board.printOptionsOnConsole(options);
                    int[] option = board.chooseOptionForPlayer(options);
                    playerShips[i].setFinalLine(option[ARRAY_POSITION_FOR_LINE]);
                    playerShips[i].setFinalColumn(option[ARRAY_POSITION_FOR_COLUMN]);
                }

                playerShips[i].setArrayWithShipAllPositions();
                isEmpty = verifyIfThePositionIsEmpty(playerShips[i].getShipAllPositions(), board.getPlayerBoard(), "Player");
            }while (isEmpty == false);
            board.putShipOnTheBoard(playerShips[i].getShipAllPositions(), board.getPlayerBoard());
            board.showBoard();
        }

        PlayerMain players = new PlayerMain();

        System.out.println("Começando os ataques ...");

        boolean isTheShotRight;
        do{
            do{
                isTheShotRight = players.giveAShot(board.getComputerBoard(), "Player");
                board.showBoard();
            }while(isTheShotRight);
            do{
                isTheShotRight = players.giveAShot(board.getPlayerBoard(), "Computer");
                board.showBoard();
            }while(isTheShotRight);
        }while(players.getPlayersShotsOnTarget() < players.getShotsToBeHit() || players.getComputersShotsOnTarget() < players.getShotsToBeHit());

        System.out.println("Jogo encerrado!");
        String winner = players.getPlayersShotsOnTarget() == players.getShotsToBeHit() ? "Você venceu!": "Você perdeu!";
        System.out.println(winner);
    }


    public static boolean verifyIfThePositionIsEmpty(int[][] shipAllPositions, String[][] boardMatrix, String typeOfPlayer){
        final int ARRAY_POSITION_FOR_LINE = 0;
        final int ARRAY_POSITION_FOR_COLUMN = 1;
        for (int[] position : shipAllPositions) {
            if (boardMatrix[position[ARRAY_POSITION_FOR_LINE]][position[ARRAY_POSITION_FOR_COLUMN]] != ".") {
                if(typeOfPlayer == "Player"){System.out.println("Uma das posições já está ocupada. Insira as posições deste navio novamente.");}
                return false;
            }
        }
        return true;
    }
}
