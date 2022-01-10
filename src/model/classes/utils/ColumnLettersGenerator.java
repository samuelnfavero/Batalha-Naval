package model.classes.utils;

public class ColumnLettersGenerator {
    public static char transformNumberInLetter(int number) {

        switch (number) {
            case 0:
                return 'O';
            case 1:
                return 'A';
            case 2:
                return 'B';
            case 3:
                return 'C';
            case 4:
                return 'D';
            case 5:
                return 'E';
            case 6:
                return 'F';
            case 7:
                return 'G';
            case 8:
                return 'H';
            case 9:
                return 'I';
            case 10:
                return 'J';
            default: return 'a';
        }
    }

    public static int transformLetterInNumber(char letter) {

        switch (Character.toLowerCase(letter)) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            case 'i':
                return 9;
            case 'j':
                return 10;
            default: return 0;
        }
    }
}
