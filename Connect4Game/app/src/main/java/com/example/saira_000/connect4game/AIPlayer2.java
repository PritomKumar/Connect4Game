package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.ArrayList;

public class AIPlayer2 {
    private Board.Turn newplayer;
    private Node currentNode;
    Node head , tail;
    private int numberOfColumns=7;
    private int numberOfRows=6;
    private  int path ;

    ArrayList <State> possibleValues ;

    public AIPlayer2() {

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

    public int mediumLevel(State state ) throws CloneNotSupportedException {

        possibleValues = new ArrayList<State>();
        State newState = new State(numberOfRows , numberOfColumns);
        newState = state.deepClone();
        int col = 0;

        printNode(newState.board);

        int [][] aiBoard = convertToMatrix(newState.board );
        MyNode aiNode = new MyNode(aiBoard , 2);

        int bestMove = miniMaxAB(aiNode,2,-1000000,10000000,true);

        String t =bestMove+ "";
        Log.d("bestMove" , t);
        Cell [][] checkMove = performBestMove(newState , bestMove);


        int action = checkMove(newState , checkMove);
        String t2 =action+ "";
        Log.d("action5" , t2);

       // printNode(newState.board);
        return 0;
    }

    private int[][] convertToMatrix(Cell[][] board) {
        int [][] newBoard = new int[numberOfRows][numberOfColumns];

        for(int row = 0 ; row < numberOfRows ; row++){
            for (int col = 0 ; col < numberOfColumns ; col++){
                if(board[row][col].player == Board.Turn.PLAYER_1){
                    newBoard[row][col] =1;
                }
                else if(board[row][col].player == Board.Turn.PLAYER_2){
                    newBoard[row][col] =2;
                }
                else {
                    newBoard[row][col] = 0;
                }
            }
        }
        return  newBoard;
    }

    public int hardLevel(){

        return 0 ;
    }

    public int veryHardLevel(){

        return 0 ;
    }

    public int checkMove(MyNode root , int[][] move){

        MyNode newState = root.deepClone();

        for(int col =0 ; col <numberOfColumns ; col++ ){
            MyNode chcekChild = newState.deepClone();
            int row = lastAvailableRow(col , chcekChild.game);
            if(row!=-1){
                occupyCell(Board.Turn.PLAYER_1 , row , col  , chcekChild.game  );
                if(compareBoard(chcekChild.game , move)){
                    return col;
                }
            }
        }
        return -1;

    }

    private boolean compareBoard(int[][] board, int[][] move) {

        for(int row = 0 ; row < numberOfRows ; row++){
            for (int col = 0 ; col < numberOfColumns ; col++){
                if(board[row][col] != move[row][col]){
                    return  false;
                }
            }
        }
        return true;
    }

    public int lastAvailableRow(int col, int [][] cells) {
        for (int row = numberOfRows - 1; row >= 0; row--) {
            if (cells[row][col] == 0 ) {
                return row;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getLegalActions(int [][] tempCell){
        ArrayList<Integer> temp = new ArrayList<Integer>();

        for(int col =0 ; col <numberOfColumns ; col++ ){
            int t = lastAvailableRow(col , tempCell);
            if(t!=-1){
                temp.add(t);
            }
        }
        return temp;
    }

    public int[][] occupyCell(int player , int row , int col ,  int [][] cells) {

        String t = "col =  " + col  +" row " + row;
        //Log.d("occupy", t);

        cells[row][col] = player;
        return  cells;
    }
    public int[][] unOccupyCell(int player , int row , int col ,  int [][] cells) {

        String t = "col =  " + col  +" row " + row;
        //Log.d("occupy", t);

        cells[row][col] = 0;
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



    private int miniMaxAB(MyNode root, int depth, int alpha, int beta, boolean maximizingPlayer) {

        MyNode newNode = root.deepClone();
        for(int col =0 ; col <numberOfColumns ; col++ ){
            MyNode chcekChild = newNode.deepClone();
            int row = lastAvailableRow(col , chcekChild.game);
            if(row!=-1){
                occupyCell(move , row , col  , chcekChild.game  );
                
            }
        }
        return -1;

        String t2 = node.getScore() + "";
        Log.d("scorenode", t2);
/*
        for(MyNode s: node.getSuccessor()){
            String t3 = s.getParent() + "";
            Log.d("parent", t3);
           // printNode(s.board);
        }
        //return  0;
*/



        if(depth == 0 || node.checkForWin()){
            return node.evaluationFunction();
        }
        if (maximizingPlayer) {
            int v = -1000000000;
            int col = 0;
            for (MyNode child : node.getSuccessor()) {
                col++;
                printNode(child.board);
                v = Math.max(v, miniMaxAB(child, depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, v);
                node.setScore(v);
                if(depth==1){
                    possibleValues.add(child);
                }
                path= v;
                String t = child.getScore() + "";
                Log.d("scoremax", t);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }

        else {
            int v = 1000000000;
            for (MyNode child : node.getSuccessor()) {
                printNode(child.board);

                v = Math.min(v, miniMaxAB(child, depth - 1, alpha, beta, true));
                beta = Math.min(beta, v);
                node.setScore(v);
                if(depth==1){
                    possibleValues.add(child);
                }
                String t = child.getScore() + "";
                Log.d("scoremin", t);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }


    }

    private Cell [][] performBestMove(MyNode root, int bestMove) {
        ArrayList<MyNode> children = root.getSuccessor();

        if(children.size() == 1) {
            return children.get(0).board;
        }
        for (MyNode child : children) {
            if (child.getScore() == bestMove) {
                return child.board;
            }
        }
        return null;
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
