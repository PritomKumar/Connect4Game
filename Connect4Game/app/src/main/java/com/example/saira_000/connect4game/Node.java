package com.example.saira_000.connect4game;

public class Node {

    private int numberOfColumns=7;
    private int numberOfRows=6;
    private BoardLogic boardLogic;
    public boolean winCondition;
    public Cell[][] cells;

    Node parent ;
    Node [] column;

    private Board.Turn turn;

    public Node(Cell[][] currentBoard) {

        cells = new Cell[numberOfRows][numberOfColumns];
        column = new Node[numberOfColumns];
        initialState(currentBoard);
    }

    public void initialState(Cell[][] currentBoard){

        for(int col = 0 ; col < numberOfColumns ; col++){
            column[col] = null;
        }
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
}
