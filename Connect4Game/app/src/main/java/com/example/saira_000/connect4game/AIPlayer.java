package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.Stack;

import static android.bluetooth.BluetoothHidDeviceAppQosSettings.MAX;

public class AIPlayer {
    private Board.Turn newplayer;
    private Node currentNode;
    Node head , tail;
    private int numberOfColumns=7;
    private int numberOfRows=6;

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

        createAllChild(currentNode);
        //int result = miniMax(currentNode,3,true) ;

        //String t = result + "";
        //Log.d("result", t);
        return 0;
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

    public Cell[][] occupyCell(Board.Turn player , int row , int col ,  Cell [][] cells) {

        String t = "col =  " + col  +" row " + row;
        //Log.d("occupy", t);

        cells[row][col].setPlayer(player);
        return  cells;
    }
    public Cell[][] unOccupyCell(Board.Turn player , int row , int col ,  Cell [][] cells) {

        String t = "col =  " + col  +" row " + row;
        //Log.d("occupy", t);

        cells[row][col].empty = true ;
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

    public  void printNode(Cell[][] cells){

        String test = "" ;
        for (int row = 0; row < numberOfRows; row++) {
            test = test + "\n";
            for (int col = 0; col < numberOfColumns; col++) {
                if(cells[row][col].empty){
                    test = test + " 0 " ;
                }
                else if(cells[row][col].player == Board.Turn.PLAYER_1){
                    test = test + " p1 " ;
                }
                else if(cells[row][col].player == Board.Turn.PLAYER_2){
                    test = test + " p2 " ;
                }
                else{
                    test = test + " 1 ";
                }
            }

        }

        Log.d("node", test);
    }

    private Stack< Node > dfs = new Stack<Node> ();

    int iteration = 0;

    /*
    public int miniMax(Node current ,int depth , boolean maximizingPlayer){

        iteration++;
        BoardLogic boardLogic = new BoardLogic(current.player , current.cells , 6 , 7 );
        if(depth ==0){
            return boardLogic.evalulationFunction();
        }

        //printNode(current.cells);

        if(maximizingPlayer){
            int bestValue = -999999;
            for (int col =0 ; col < numberOfColumns ; col++){
                Cell [][] tempCell = current.cells;
                int row = lastAvailableRow(col,tempCell);
                printNode(current.cells);

                String t = "Depth "+ depth +"  Iteration " + iteration + " col =  " + col  +" row " + row;
                Log.d("maxmin", t);
                if(row!=-1){
                    Cell [][] newCells  = occupyCell(current.player  , row , col , tempCell);
                    newplayer = Board.Turn.PLAYER_1;
                    Node newNode = insertNewNode(col , current , newCells , newplayer );


                    String t2 = "Depth "+ depth +"  Iteration " + iteration + " col =  " + col  +" row " + row;
                    Log.d("occupy", t2);
                    int value = miniMax(newNode , depth-1 , false);

                    String t3 = value+"";
                    Log.d("value", t3);
                    bestValue = maxValue(bestValue , value);
                }

            }
            return bestValue;
        }

        else{
            int bestValue = 999999;
            for (int col =0 ; col < numberOfColumns ; col++){
                Cell [][] tempCell = current.cells;

                int row = lastAvailableRow(col,tempCell);
                String t = "col =  " + col  +" row " + row;
                Log.d("min", t);
                if(row!=-1){
                    Cell [][] newCells  = occupyCell(current.player  , row , col , tempCell);
                    newplayer = Board.Turn.PLAYER_2;
                    Node newNode = insertNewNode(col , current , newCells , newplayer);


                    int value = miniMax(newNode , depth-1 , true);
                    String t2 = value+"";
                    Log.d("value", t2);
                    bestValue = minValue(bestValue , value);
                }

            }
            return bestValue;
        }

    }
*/

    public void createAllChild(Node current){

        Cell[][] tempNodeCell = current.cells;
        for(int col =0 ; col< numberOfColumns ; col++){

            int row = lastAvailableRow(col , tempNodeCell);
            if(row !=-1) {
                Cell[][] tempCell = occupyCell(current.player , row , col , tempNodeCell );

                Board.Turn nPlayer = toggleTurn(current.player);
                Node newNode = new Node(tempCell , nPlayer);
                current.column[col] = newNode;
                //tempCell = unOccupyCell(current.player , row , col , tempNodeCell );

            }
        }

        for(int col =0 ; col< numberOfColumns ; col++){
            printNode(current.column[col].cells);
        }

    }

    public int miniMax(Node current ,int depth , boolean maximizingPlayer){

        iteration++;
        BoardLogic boardLogic = new BoardLogic(current.player , current.cells , 6 , 7 );
        if(depth ==0){
            return boardLogic.evalulationFunction();
        }

        //printNode(current.cells);

        if(maximizingPlayer){
            int bestValue = -999999;
            for (int col =0 ; col < numberOfColumns ; col++){
                Cell [][] tempCell = current.cells;
                int row = lastAvailableRow(col,tempCell);
                printNode(current.cells);

                String t = "Depth "+ depth +"  Iteration " + iteration + " col =  " + col  +" row " + row;
                Log.d("maxmin", t);
                if(row!=-1){
                    Cell [][] newCells  = occupyCell(current.player  , row , col , tempCell);
                    newplayer = Board.Turn.PLAYER_1;
                    Node newNode = insertNewNode(col , current , newCells , newplayer );


                    String t2 = "Depth "+ depth +"  Iteration " + iteration + " col =  " + col  +" row " + row;
                    Log.d("occupy", t2);
                    int value = miniMax(newNode , depth-1 , false);

                    String t3 = value+"";
                    Log.d("value", t3);
                    bestValue = maxValue(bestValue , value);
                }

            }
            return bestValue;
        }

        else{
            int bestValue = 999999;
            for (int col =0 ; col < numberOfColumns ; col++){
                Cell [][] tempCell = current.cells;

                int row = lastAvailableRow(col,tempCell);
                String t = "col =  " + col  +" row " + row;
                Log.d("min", t);
                if(row!=-1){
                    Cell [][] newCells  = occupyCell(current.player  , row , col , tempCell);
                    newplayer = Board.Turn.PLAYER_2;
                    Node newNode = insertNewNode(col , current , newCells , newplayer);


                    int value = miniMax(newNode , depth-1 , true);
                    String t2 = value+"";
                    Log.d("value", t2);
                    bestValue = minValue(bestValue , value);
                }

            }
            return bestValue;
        }

    }

    public Node insertNewNode(int col, Node current , Cell [][] newCells , Board.Turn player5) {

        Node newNode = new Node(newCells , player5 );

        current.column[col] = newNode;
        newNode.parent = current;

        return  newNode;
    }
}
