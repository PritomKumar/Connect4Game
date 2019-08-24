package com.example.saira_000.connect4game;

import java.util.ArrayList;

public class Expand {

    int numberOfRows = 6;
    int numberOfColumns = 7;

    public void fullBoardCells (Cell [][] cells){
        for(int row = 0 ; row < numberOfRows ; row++){
            for(int col = 0 ; col < numberOfColumns ; col++){
                cells[row][col] = new Cell();
            }
        }

    }


    public ArrayList<Node> MakeChildren(Cell[][] currentBoard, Board.Turn player) {
        ArrayList<Node> frontier= new ArrayList<Node>();
        for(int j=0; j < numberOfColumns; j++)  {
            if(currentBoard[0][j].empty==true) {
                Node node = new Node(AddChild(currentBoard,j,player) , player);
                node.setScore(j);
                frontier.add(node);
            }
        }
        return frontier;
    }


    public Cell[][] AddChild(Cell[][] currentBoard, int j, Board.Turn player) {
        Cell[][] childBoard = new Cell[numberOfRows][numberOfColumns];
        fullBoardCells(childBoard);

        for(int i=0; i<numberOfRows; i++)
            for(int k=0; k<numberOfColumns; k++)
                childBoard[i][k]= currentBoard[i][k];

        for(int i = numberOfRows-1; i >= 0; i--) {
            if(childBoard[i][j].empty==true) {
                if(player == Board.Turn.PLAYER_1)
                    childBoard[i][j].setPlayer(player);
                else
                    childBoard[i][j].setPlayer(Board.Turn.PLAYER_2);
                break;
            }
        }
        return childBoard;
    }
}
