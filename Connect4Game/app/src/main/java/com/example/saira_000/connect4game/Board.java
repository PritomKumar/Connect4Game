package com.example.saira_000.connect4game;

public class Board {

    private int numberOfColumns;
    private int numberOfRows;
    private BoardLogic boardLogic;
    public boolean winCondition;
    public Cell[][] cells;

    public enum Turn {
        PLAYER_1, PLAYER_2
    }

    public Turn turn;

    public Board( int rows , int cols) {
        numberOfRows = rows;
        numberOfColumns = cols;
        cells = new Cell[rows][cols];
        reset();
    }

    public void reset() {
        winCondition = false;
        turn = Turn.PLAYER_1;
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                cells [row][col] = new Cell();
            }
        }
    }

    public int lastAvailableRow(int col) {
        for (int row = numberOfRows - 1; row >= 0; row--) {
            if (cells[row][col].empty) {
                return row;
            }
        }
        return -1;
    }

    public void occupyCell( int row , int col) {

        cells[row][col].setPlayer(turn);
    }

    public void toggleTurn() {
        if (turn == Turn.PLAYER_1) {
            turn = Turn.PLAYER_2;
        } else {
            turn = Turn.PLAYER_1;
        }
    }

    public boolean checkForWin() {
        boardLogic = new BoardLogic(turn , cells , numberOfRows , numberOfColumns);

        if(boardLogic.checkForWin()){
            winCondition = true;
        }
        return  boardLogic.checkForWin();
    }

}
