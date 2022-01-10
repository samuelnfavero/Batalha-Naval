package model.classes.utils;


import java.util.Scanner;

public class Input {

    public static int inputInt(){
        Scanner inputInt = new Scanner(System.in);
         return inputInt.nextInt();
    }

    public static char inputChar(){
        Scanner inputChar = new Scanner(System.in);
        return inputChar.next().charAt(0);
    }

}
