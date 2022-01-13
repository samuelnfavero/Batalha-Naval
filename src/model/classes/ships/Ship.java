package model.classes.ships;

import model.classes.enums.BoardSymbols;
import model.classes.enums.Positions;
import model.classes.utils.ColumnLettersGenerator;
import model.classes.utils.Input;

public class Ship {
    private String shipName;
    private int shipLength;
    private int initialLine;
    private int initialColumn;
    private int finalLine;
    private int finalColumn;
    private int[][] shipAllPositions;

    public Ship(String _shipName, int _shipLength){
        this.shipName = _shipName;
        this.shipLength = _shipLength;
        this.shipAllPositions = new int[this.shipLength][2];
    }

    //----------Setters and Getters----------//
    public int getShipLength() {
        return shipLength;
    }

    public int getInitialLine() {
        return initialLine;
    }

    public int getInitialColumn() {
        return initialColumn;
    }

    public int getFinalLine() {
        return finalLine;
    }

    public int getFinalColumn() {
        return finalColumn;
    }

    public int[][] getShipAllPositions(){ return shipAllPositions; }

    public void setInitialLine(int initialLine) {
        this.initialLine = initialLine;
    }

    public void setInitialColumn(int initialColumn) {
        this.initialColumn = initialColumn;
    }

    public void setFinalLine(int finalLine) {
        this.finalLine = finalLine;
    }

    public void setFinalColumn(int finalColumn) {
        this.finalColumn = finalColumn;
    }

    //-------Methods-------//

    public void changeInitialLine(){
        System.out.println("Insira a linha da posição inicial do " + this.shipName + " de comprimento " + this.shipLength + " (LETRA) " + ": ");
        int initialLine = ColumnLettersGenerator.transformLetterInNumber(Input.inputChar());
        while(initialLine < 1 || initialLine > 10) {
            System.out.println("Essa posição não existe no tabuleiro");
            System.out.println("Insira a linha da posição inicial deste navio: ");
            initialLine = ColumnLettersGenerator.transformLetterInNumber(Input.inputChar());
        }
        this.setInitialLine(initialLine);
    }

    public void changeInitialColumn(){
        System.out.println("Insira a coluna da posição inicial do " + this.shipName + " de comprimento " + this.shipLength + " (NÚMERO)" + ": ");
        int initialColumn = Input.inputInt();
        while(initialColumn < 1 || initialColumn > 10) {
            System.out.println("Essa posição não existe no tabuleiro");
            System.out.println("Insira a coluna da posição inicial deste navio: ");
            initialColumn = Input.inputInt();
        }
        this.setInitialColumn(initialColumn);
    }

    public void setArrayWithShipAllPositions() {
        if(this.shipLength == 1){
            this.shipAllPositions[0][Positions.LINE.getValue()] = this.initialLine;
            this.shipAllPositions[0][Positions.COLUMN.getValue()] = this.initialColumn;
        }else {
            if (this.initialLine == this.finalLine) {
                int columnPosition = this.initialColumn < this.finalColumn ? this.initialColumn : this.finalColumn;
                for (int i = 0; i < this.shipLength; i++) {
                    this.shipAllPositions[i][Positions.LINE.getValue()] = this.initialLine;
                    this.shipAllPositions[i][Positions.COLUMN.getValue()] = columnPosition;
                    columnPosition++;
                }
            } else {
                int linePosition = this.initialLine < this.finalLine ? this.initialLine : this.finalLine;
                for (int i = 0; i < this.shipLength; i++) {
                    this.shipAllPositions[i][Positions.LINE.getValue()] = linePosition;
                    this.shipAllPositions[i][Positions.COLUMN.getValue()] = this.initialColumn;
                    linePosition++;
                }
            }
        }
    }

}
