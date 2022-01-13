package model.classes.enums;

public enum Positions {
    LINE (0),
    COLUMN (1);

     private final int value;

    Positions(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
