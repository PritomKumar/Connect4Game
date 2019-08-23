package com.example.saira_000.connect4game;

public class Node {

    private int numberOfColumns=7;
    private int numberOfRows=6;
    private BoardLogic boardLogic;
    public boolean winCondition;
    public Cell[][] cells;

    public Node parent ;
    public Node [] column;

    public Board.Turn player;

    public Node(Cell[][] currentBoard , Board.Turn turn) {

        cells = new Cell[numberOfRows][numberOfColumns];
        column = new Node[numberOfColumns];
        player = turn;
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

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                cells [row][col] = currentBoard[row][col];
            }
        }
    }


}
