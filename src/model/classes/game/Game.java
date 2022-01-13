package model.classes.game;

import model.classes.board.Board;
import model.classes.enums.BoardSymbols;
import model.classes.enums.Positions;
import model.classes.player.Player;
import model.classes.ships.Ship;
import model.classes.utils.RandomNumberGenerator;

import java.util.ArrayList;

public class Game {
        public static void PlayGame(){


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

            startTheShots(board, players);

            finishGame(players);
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
                System.out.println("fdjhsdkfjdf");
                board.putShipOnTheBoard(ship.getShipAllPositions(), board.getComputerBoard());
                board.showBoard();
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

        private static void startTheShots(Board board, Player players){
            System.out.println("\nComeçando os ataques ...\n");
            while((players.getPlayersShotsOnTarget() < players.getShotsToBeHit()) && (players.getComputersShotsOnTarget() < players.getShotsToBeHit())) {

                playersShot(board, players);

                if(players.getPlayersShotsOnTarget() < players.getShotsToBeHit()){
                computersShot(board, players);
                }
            }
        }

        private static void playersShot(Board board, Player players){
            boolean isTheShotRight;
            String typeOfPlayer = "Player";
            System.out.println("\nSua vez de jogar\n");
            do {
                    isTheShotRight = players.giveAShot(board.getComputerBoard(), typeOfPlayer);
                    System.out.println("Player" + players.getPlayersShotsOnTarget());
                    board.showBoard();
                    shotMessage(isTheShotRight, typeOfPlayer);
                }while(isTheShotRight && players.getPlayersShotsOnTarget() < players.getShotsToBeHit());
        }

        private static void computersShot(Board board, Player players){
            boolean isTheShotRight;
            String typeOfPlayer = "Computer";
            System.out.println("\nVez do computador...\n");

            do{
                isTheShotRight = players.giveAShot(board.getPlayerBoard(), "Computer");
                System.out.println("Computador" + players.getComputersShotsOnTarget());
                board.showBoard();
                shotMessage(isTheShotRight, typeOfPlayer);
            }while(isTheShotRight && (players.getComputersShotsOnTarget() < players.getShotsToBeHit()));
        }

        private static void shotMessage(boolean isTheShotRight, String typeOfPlayer){
            if (isTheShotRight) {
                System.out.println("Tiro no alvo do " + typeOfPlayer + "!!! =D");
            } else {
                System.out.println("Tiro no mar do " + typeOfPlayer + ". =(");
            }
        }

        private static void finishGame(Player players){
            System.out.println("Jogo encerrado!");
            String winner = players.getPlayersShotsOnTarget() == players.getShotsToBeHit() ? "Você venceu!": "Você perdeu!";
            System.out.println(winner);
        }

        private static boolean verifyIfThePositionIsEmpty(int[][] shipAllPositions, String[][] boardMatrix, String typeOfPlayer){
            for (int[] position : shipAllPositions) {
                if (boardMatrix[position[Positions.LINE.getValue()]][position[Positions.COLUMN.getValue()]] != BoardSymbols.SEA.getBoardSymbol()) {
                    if(typeOfPlayer == "Player"){System.out.println("Uma das posições já está ocupada. Insira as posições deste navio novamente.");}
                    return false;
                }
            }
            return true;
        }


    }
