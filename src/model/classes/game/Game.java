package model.classes.game;

import model.classes.board.Board;
import model.classes.enums.Positions;
import model.classes.player.Player;
import model.classes.ships.Ship;
import model.classes.utils.RandomNumberGenerator;

import java.util.ArrayList;

public class Game {
        public static void PlayGame(){
            final int ARRAY_POSITION_FOR_LINE = 0;
            final int ARRAY_POSITION_FOR_COLUMN = 1;

            Ship[] playersShips = new Ship[]{new Ship("Navio 1", 1), new Ship("Navio 2", 1), new Ship("Navio 3", 2), new Ship("Navio 4", 2),
                    new Ship("Navio 5", 3), new Ship("Navio 6", 3), new Ship("Navio 7", 4)};

            Ship[] computersShips = new Ship[]{new Ship("Navio 1", 1), new Ship("Navio 2", 1), new Ship("Navio 3", 2), new Ship("Navio 4", 2),
                    new Ship("Navio 5", 3), new Ship("Navio 6", 3), new Ship("Navio 7", 4)};

            Board board = new Board(11, 11);

            board.boardGenerator();
            board.showBoard();

            placeComputersShips(board, computersShips);

            placePlayersShips(board, playersShips);

            Player players = new Player();

            System.out.println("Começando os ataques ...");

            boolean isTheShotRight;
            while((players.getPlayersShotsOnTarget() < players.getShotsToBeHit()) && (players.getComputersShotsOnTarget() < players.getShotsToBeHit())) {
                do{
                    isTheShotRight = players.giveAShot(board.getComputerBoard(), "Player");
                    System.out.println("Player" + players.getPlayersShotsOnTarget());
                    board.showBoard();
                    if(isTheShotRight) {
                        System.out.println("Você acertou o último tiro!!! =D");
                    } else {
                        System.out.println("Você errou o último tiro. =(");
                    }

                }while(isTheShotRight && players.getPlayersShotsOnTarget() < players.getShotsToBeHit());
                do{
                    isTheShotRight = players.giveAShot(board.getPlayerBoard(), "Computer");
                    System.out.println("Computador" + players.getComputersShotsOnTarget());
                    board.showBoard();
                }while(isTheShotRight && (players.getComputersShotsOnTarget() < players.getShotsToBeHit()));
            }

            System.out.println("Jogo encerrado!");
            String winner = players.getPlayersShotsOnTarget() == players.getShotsToBeHit() ? "Você venceu!": "Você perdeu!";
            System.out.println(winner);
        }

        private static void placeComputersShips(Board board, Ship[] computersShips){
            for(Ship ship : computersShips) {
                boolean isEmpty;
                do {
                    ship.setInitialLine(RandomNumberGenerator.randomIntGenerator(10));
                    ship.setInitialColumn(RandomNumberGenerator.randomIntGenerator(10));
                    if (ship.getShipLength() > 1) {
                        ArrayList options = board.setOptionsForShipFinalPosition(ship.getInitialLine(), ship.getInitialColumn(), ship.getShipLength());
                        int[] option = board.chooseOptionForComputer(options);
                        ship.setFinalLine(option[Positions.LINE.getValue()]);
                        ship.setFinalColumn(option[Positions.COLUMN.getValue()]);
                    }
                    ship.setArrayWithShipAllPositions();
                    isEmpty = verifyIfThePositionIsEmpty(ship.getShipAllPositions(), board.getComputerBoard(), "Computer");
                }while (isEmpty == false);
                board.putShipOnTheBoard(ship.getShipAllPositions(), board.getComputerBoard());
            }

        }
        private static void placePlayersShips(Board board, Ship[] playersShips){
            for(Ship ship : playersShips) {
                boolean isEmpty;
                do {
                    ship.changeInitialLine();
                    ship.changeInitialColumn();
                    if (ship.getShipLength() > 1) {
                        ArrayList options = board.setOptionsForShipFinalPosition(ship.getInitialLine(), ship.getInitialColumn(), ship.getShipLength());
                        board.printOptionsOnConsole(options);
                        int[] option = board.chooseOptionForPlayer(options);
                        ship.setFinalLine(option[Positions.LINE.getValue()]);
                        ship.setFinalColumn(option[Positions.COLUMN.getValue()]);
                    }

                    ship.setArrayWithShipAllPositions();
                    isEmpty = verifyIfThePositionIsEmpty(ship.getShipAllPositions(), board.getPlayerBoard(), "Player");
                }while (isEmpty == false);
                board.putShipOnTheBoard(ship.getShipAllPositions(), board.getPlayerBoard());
                board.showBoard();
            }
        }

        private static boolean verifyIfThePositionIsEmpty(int[][] shipAllPositions, String[][] boardMatrix, String typeOfPlayer){
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
