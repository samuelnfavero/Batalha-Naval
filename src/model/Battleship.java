package model;

import model.classes.service.Game;
import model.classes.utils.Input;


public class Battleship {
    public static void main(String[] args){
        System.out.println("           _           _   _   _           _     _       \n" +
                "          | |         | | | | | |         | |   (_)      \n" +
                "          | |__   __ _| |_| |_| | ___  ___| |__  _ _ __  \n" +
                "          | '_ \\ / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\ \n" +
                "          | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |\n" +
                "          |_.__/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/ \n" +
                "                                                  | |    \n" +
                "                                                  |_|    ");
        System.out.println("                                   # #  ( )\n" +
                "                                  ___#_#___|__\n" +
                "                              _  |____________|  _\n" +
                "                       _=====| | |            | | |==== _\n" +
                "                 =====| |.---------------------------. | |====\n" +
                "   <--------------------'   .  .  .  .  .  .  .  .   '--------------/\n" +
                "     \\                                                             /\n" +
                "      \\_______________________________________________WWS_________/\n" +
                "  wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" +
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" +
                "   wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww ");
        System.out.println("Menu: ");
        System.out.println("-----------------");
        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Fechar jogo");
        System.out.println("-----------------");
        System.out.println("Digite sua opção:");
        char startGame;
        do{
        startGame = Character.toLowerCase(Input.inputChar());
        switch(startGame) {
            case '1':
                Game.PlayGame();
            case '2':
                System.out.println("Jogo Finalizado.");
                break;
            default:
                System.out.println("Opção inválida. Digite Novamente:");
        }
        }while(startGame != '1' || startGame != '2');
    }
}
