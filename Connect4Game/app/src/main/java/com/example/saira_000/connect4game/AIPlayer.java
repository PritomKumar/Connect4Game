package com.example.saira_000.connect4game;

import static android.bluetooth.BluetoothHidDeviceAppQosSettings.MAX;

public class AIPlayer {

    private BoardLogic boardLogic;
    private Board.Turn newplayer;
    private Node currentNode;
    Node head , tail;
    private int numberOfColumns=7;
    private int numberOfRows=6;
    private Cell[][] tempCell;

    public AIPlayer() {

        createLinkedList();
    }

    private void createLinkedList() {
        head= null;
        tail = null;
    }

    public int babyLevel(){
        int randomInt = (int)(6.0 * Math.random());
        return  randomInt;
    }

    public int easyLevel(){



        return 0 ;
    }

    public int mediumLevel(Cell [][] currentCell){

        currentNode = new Node(currentCell, Board.Turn.PLAYER_2);
        head = currentNode;

        return miniMax(currentNode,1,true) ;
    }

    public int hardLevel(){

        return 0 ;
    }

    public int veryHardLevel(){

        return 0 ;
    }

    public int lastAvailableRow(int col, Cell [][] cells) {
        for (int row = numberOfRows - 1; row >= 0; row--) {
            if (cells[row][col].empty) {
                return row;
            }
        }
        return -1;
    }

    public Cell [][] occupyCell(Board.Turn player , int row , int col ,  Cell [][] cells) {

        cells[row][col].setPlayer(player);
        return  cells;
    }

    public Board.Turn toggleTurn(Board.Turn player) {
        if (player == Board.Turn.PLAYER_1) {
            return Board.Turn.PLAYER_2;
        } else {
            return  Board.Turn.PLAYER_1;
        }

    }

    private int maxValue(int bestValue, int value) {
        if(bestValue >= value){
            return  bestValue;
        }
        else return value;
    }

    private int minValue(int bestValue, int value) {
        if(bestValue <= value){
            return  bestValue;
        }
        else return value;
    }

    public int miniMax(Node current ,int depth , boolean maximizingPlayer){

        boardLogic = new BoardLogic(current.player , currentNode.cells , 6 , 7 );
        if(depth ==0|| boardLogic.checkForWin()){
            return boardLogic.evalulationFunction();
        }

        if(maximizingPlayer){
            int bestValue = -999999;
            for (int col =0 ; col < numberOfColumns ; col++){
                tempCell = currentNode.cells;
                int row = lastAvailableRow(col,tempCell);
                if(row!=-1){
                    Cell [][] newCells  = occupyCell(current.player  , row , col , tempCell);
                    Node newNode = insertNewNode(col , current , newCells );
                    int value = miniMax(newNode , depth-1 , false);
                    bestValue = maxValue(bestValue , value);
                }

            }
            return bestValue;
        }

        else{
            int bestValue = 999999;
            for (int col =0 ; col < numberOfColumns ; col++){
                tempCell = currentNode.cells;
                int row = lastAvailableRow(col,tempCell);
                if(row!=-1){
                    Cell [][] newCells  = occupyCell(current.player  , row , col , tempCell);
                    Node newNode = insertNewNode(col , current , newCells );
                    int value = miniMax(newNode , depth-1 , true);
                    bestValue = minValue(bestValue , value);
                }

            }
            return bestValue;
        }

    }



    public Node insertNewNode(int col, Node current , Cell [][] newCells) {


        newplayer = toggleTurn(current.player);
        Node newNode = new Node(newCells , newplayer );

        current.column[col] = newNode;
        newNode.parent = current;

        return  newNode;
    }
}
