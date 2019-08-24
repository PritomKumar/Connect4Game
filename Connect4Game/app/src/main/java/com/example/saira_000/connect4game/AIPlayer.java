package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.ArrayList;

public class AIPlayer {
    private Board.Turn newplayer;
    private Node currentNode;
    Node head , tail;
    private int numberOfColumns=7;
    private int numberOfRows=6;

    ArrayList <Node> possibleValues = new ArrayList<Node>();

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

    public int mediumLevel(Cell [][] currentCell) throws CloneNotSupportedException {

        currentNode = new Node(currentCell,Board.Turn.PLAYER_2);
        MinimaxAgent minimaxAgent = new MinimaxAgent(2);
        State s=new State(numberOfRows,numberOfColumns , currentCell);

        State newState = (State) s.clone();

        int action = minimaxAgent.getAction(newState);
        String t = action+ "";
        Log.d("action" , t);

        printNode(currentCell);
        return action;
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

    public ArrayList<Integer> getLegalActions(Cell [][] tempCell){
        ArrayList<Integer> temp = new ArrayList<Integer>();

        for(int col =0 ; col <numberOfColumns ; col++ ){
            int t = lastAvailableRow(col , tempCell);
            if(t!=-1){
                temp.add(t);
            }
        }
        return temp;
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


    /*

    public int Minimax(Node node, int depth, Board.Turn player) {

        BoardLogic logic = new BoardLogic(node.getPlayer(), node.getstate() , numberOfRows , numberOfColumns);
        if(depth == 0 || logic.checkForWin()){
            return logic.evalulationFunction();
        }

        if (player == Board.Turn.PLAYER_1){
            node.setUtility(Integer.MIN_VALUE);
            for(Node n : node.expandNode(Board.Turn.PLAYER_1)){
                //printNode(n.getstate());

                n.setUtility(Minimax(n, depth -1 ,Board.Turn.PLAYER_2));
                node.setUtility(Math.max(n.getUtility(), node.getUtility()));
                String t = n.getUtility() + "";
                Log.d("uti" ,t);
            }
            return node.getUtility();
        }
        else{
            node.setUtility(Integer.MAX_VALUE);
            for(Node n : node.expandNode(Board.Turn.PLAYER_2)){
                //printNode(n.getstate());
                n.setUtility(Minimax(n, depth -1 ,Board.Turn.PLAYER_1));
                node.setUtility(Math.min(n.getUtility(), node.getUtility()));
                if (depth == 1)
                    possibleValues.add(n);
            }
            return node.getUtility();
        }
    }
*/
}
