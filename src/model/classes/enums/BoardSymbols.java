package model.classes.enums;

public enum BoardSymbols {
    SHIP ('S'),
    SEA ('~'),
    RIGHT_SHOTS ('X'),
    WRONG_SHOTS ('O');

    private final char boardSymbol;

    BoardSymbols(char boardSymbol){
        this.boardSymbol = boardSymbol;
    }
}
