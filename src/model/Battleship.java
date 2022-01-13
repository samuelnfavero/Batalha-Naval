package model;

import model.classes.game.Game;
import model.classes.utils.Input;


public class Battleship {
    public static void main(String[] args){
        System.out.println("Deseja iniciar o jogo? Digite 'S' ou 'N'");
        char startGame = Character.toLowerCase(Input.inputChar());

        switch(startGame) {
            case 's':
            Game.PlayGame();
            default:
            System.out.println("Jogo Finalizado.");
        }
    }
}
