package com.example.saira_000.connect4game;

import java.io.Serializable;

public class Node2 implements Serializable {

    private int numberOfColumns=7;
    private int numberOfRows=6;
    private BoardLogic boardLogic;
    public boolean winCondition;
    public Cell[][] cells;
    public int score;

    public Node2 parent ;
    public Node2[] column;

    public Board.Turn player;

    public Node2(Cell[][] currentBoard , Board.Turn turn) {

        cells = new Cell[numberOfRows][numberOfColumns];
        column = new Node2[numberOfColumns];
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
