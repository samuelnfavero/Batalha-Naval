package model.classes.enums;

public enum BoardSymbols {
    SHIP ("S"),
    SEA ("~"),
    RIGHT_SHOTS ("X"),
    WRONG_SHOTS ("O");

    private final String boardSymbol;

    BoardSymbols(String boardSymbol){
        this.boardSymbol = boardSymbol;
    }

    public String getBoardSymbol() {
        return boardSymbol;
    }
}
