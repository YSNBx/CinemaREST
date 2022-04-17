package com.cinema.layout;

public class CinemaSeat {
    private int row;
    private int column;
    private int price;

    public CinemaSeat() {}

    public CinemaSeat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getPrice() {
        return this.price;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
