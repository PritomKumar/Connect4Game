package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.ArrayList;

public class AIPlayer3 {
    private Board.Turn newplayer;
    private Node currentNode;
    Node head , tail;
    private int numberOfColumns=7;
    private int numberOfRows=6;
    private  int mainDepth = 1;
    private  int path ;

    ArrayList <MyNode> possibleValues ;

    public AIPlayer3() {

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

    public int easyLevel(State state , int level ) throws CloneNotSupportedException {

        possibleValues = new ArrayList<MyNode>();
        this.mainDepth = level;
        State newState = new State(numberOfRows , numberOfColumns);
        newState = state.deepClone();
        int col = 0;

        printNode2(newState.board);


        int [][] aiBoard = convertToMatrix(newState.board );
        MyNode aiNode = new MyNode(aiBoard , 2);

        aiNode.player=2;

        printNode(aiNode.game);

        //int bestScore = miniMax(aiNode,mainDepth,true);

        long startTime = System.currentTimeMillis();

        int bestScore = miniMaxAB(aiNode,mainDepth,-10000000 , 100000000, true);

        String t =bestScore+ "";
        Log.d("bestScore" , t);

        int action = findMove(bestScore);

        String t2 =action+ "";
        Log.d("action5" , t2);

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        // printNode(newState.board);
        return action;
    }

    public int mediumLevel(State state  , int level ) throws CloneNotSupportedException {

        possibleValues = new ArrayList<MyNode>();
        this.mainDepth = level;
        State newState = new State(numberOfRows , numberOfColumns);
        newState = state.deepClone();
        int col = 0;

        printNode2(newState.board);

        int [][] aiBoard = convertToMatrix(newState.board );
        MyNode aiNode = new MyNode(aiBoard , 2);

        aiNode.player=2;

        printNode(aiNode.game);

        long startTime = System.nanoTime();

        //int bestScore = miniMax(aiNode,mainDepth,true);
        int bestScore = miniMaxAB(aiNode,mainDepth,-10000000 , 100000000, true);

        String t =bestScore+ "";
        Log.d("bestScore" , t);

        int action = findMove(bestScore);

        String t2 =action+ "";
        Log.d("action5" , t2);

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        String s ="Time in Milliseconds : " + (int)timeElapsed + "" ;
        Log.d("Timein" , s);

       // printNode(newState.board);
        return action;
    }


    public int hardLevel(State state, int level ) throws CloneNotSupportedException {

        this.mainDepth = level;
        possibleValues = new ArrayList<MyNode>();
        State newState = new State(numberOfRows , numberOfColumns);
        newState = state.deepClone();
        int col = 0;

        printNode2(newState.board);

        int [][] aiBoard = convertToMatrix(newState.board );
        MyNode aiNode = new MyNode(aiBoard , 2);

        aiNode.player=2;

        printNode(aiNode.game);

        long startTime = System.nanoTime();

        //int bestScore = miniMax(aiNode,mainDepth,true);
        int bestScore = miniMaxAB(aiNode,mainDepth,-10000000 , 100000000, true);

        String t =bestScore+ "";
        Log.d("bestScore" , t);

        int action = findMove(bestScore);

        String t2 =action+ "";
        Log.d("action5" , t2);

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        String s ="Time in Milliseconds : " + (int)timeElapsed + "" ;
        Log.d("Timein" , s);

        // printNode(newState.board);
        return action;
    }

    public int veryHardLevel(State state, int level ) throws CloneNotSupportedException {

        this.mainDepth = level;
        possibleValues = new ArrayList<MyNode>();
        State newState = new State(numberOfRows , numberOfColumns);
        newState = state.deepClone();
        int col = 0;

        printNode2(newState.board);

        int [][] aiBoard = convertToMatrix(newState.board );
        MyNode aiNode = new MyNode(aiBoard , 2);

        aiNode.player=2;

        printNode(aiNode.game);

        //int bestScore = miniMax(aiNode,mainDepth,true);

        long startTime = System.currentTimeMillis();

        int bestScore = miniMaxAB(aiNode,mainDepth,-10000000 , 100000000, true);

        String t =bestScore+ "";
        Log.d("bestScore" , t);

        int action = findMove(bestScore);

        String t2 =action+ "";
        Log.d("action5" , t2);

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        // printNode(newState.board);
        return action;
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


    public int checkMove(MyNode root , int[][] move){

        MyNode newState = root.deepClone();

        for(int col =0 ; col <numberOfColumns ; col++ ){
            MyNode chcekChild = newState.deepClone();
            int row = lastAvailableRow(col , chcekChild.game);
            if(row!=-1){
                occupyCell( root.player , row , col  , chcekChild.game  );
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

    public  void printNode2(Cell [][] cells){

        String test = "" ;
        for (int row = 0; row < numberOfRows; row++) {
            test = test + "\n";
            for (int col = 0; col < numberOfColumns; col++) {
                if(cells[row][col].empty  ){
                    test = test + " 0 " ;
                }
                else if(cells[row][col].player == Board.Turn.PLAYER_1){
                    test = test + " 1 " ;
                }
                else if(cells[row][col].player == Board.Turn.PLAYER_2){
                    test = test + " 2 " ;
                }
                else{
                    test = test + " @ ";
                }
            }

        }

        Log.d("node", test);
    }


    public  void printNode(int [][] cells){

        String test = "" ;
        for (int row = 0; row < numberOfRows; row++) {
            test = test + "\n";
            for (int col = 0; col < numberOfColumns; col++) {
                if(cells[row][col] == 0 ){
                    test = test + " 0 " ;
                }
                else if(cells[row][col] == 1){
                    test = test + " 1 " ;
                }
                else if(cells[row][col] == 2){
                    test = test + " 2 " ;
                }
                else{
                    test = test + " @ ";
                }
            }

        }

        Log.d("node", test);
    }

    private int tooglePlayer(int player){
        if(player == 1){
            return  2;
        }
        else return  1;
    }

    private ArrayList<MyNode> generateChildren(MyNode newNode){
        ArrayList<MyNode> children = new ArrayList<MyNode>();
        for(int col =0 ; col <numberOfColumns ; col++ ){
            MyNode chcekChild = newNode.deepClone();
            int row = lastAvailableRow(col , chcekChild.game);
            if(row!=-1){
                int player = tooglePlayer(newNode.player);
                occupyCell(player , row , col  , chcekChild.game  );
                chcekChild.col = col;
                chcekChild.player = player;
                children.add(chcekChild);
            }
        }
        return  children;
    }

    private int miniMax(MyNode node, int depth, boolean maximizingPlayer) {

        String t2 = node.score + "";
        Log.d("scorenode", t2);
/*
        for(MyNode child : generateChildren(newNode)){
            String t3 = child.col + "";
            Log.d("parent", t3);
            printNode(child.game);
        }
        return  0;

 */

        int player5 = 0;
        if(maximizingPlayer){
            player5 = 2;
        }
        else if(!maximizingPlayer){
            player5 = 1;
        }

        BoardLogic3 boardLogic = new BoardLogic3(node.player , node.game , numberOfRows,numberOfColumns);
        Log.d("node", "BoardLogic");
        //printNode(boardLogic.cells);

        if(boardLogic.checkForWin()){
            Log.d("winners", "Winnner!!!!!!!");
            int result = boardLogic.evalulationFunction(node);
            Log.d("winners", result + "" );
        }

        if(depth == 0 || boardLogic.checkForWin()){
            int result = boardLogic.evalulationFunction(node);
            node.score = result;
            return result;
        }
        if (maximizingPlayer) {
            int v = -1000000000;

            for (MyNode child : generateChildren(node)) {

                String s = "Child = " + child.player;
                Log.d("node" , s);
                //printNode(child.game);
                v = Math.max(v, miniMax(child, depth - 1, false));
                node.score = v ;
                if(depth == mainDepth){
                    possibleValues.add(child);
                }
                String t = child.score + "";
                Log.d("scoremax", t);

            }
            return v;
        }

        else {
            int v = 1000000000;
            for (MyNode child : generateChildren(node)) {
               // String s = "Child = " + child.player;
               // Log.d("node" , s);
               // printNode(child.game);

                v = Math.min(v, miniMax(child, depth - 1, true));
                node.score = v ;
                if(depth == mainDepth){
                    possibleValues.add(child);
                }
               // String t = child.score + "";
               // Log.d("scoremin", t);

            }
            return v;
        }


    }


    private int miniMaxAB(MyNode node, int depth, int alpha, int beta, boolean maximizingPlayer) {

        MyNode newNode = node.deepClone();


        String t2 = node.score + "";
        Log.d("scorenode", t2);
/*
        for(MyNode s: node.getSuccessor()){
            String t3 = s.getParent() + "";
            Log.d("parent", t3);
           // printNode(s.board);
        }
        //return  0;
*/
        int player5 = 0;
        if(maximizingPlayer){
            player5 = 2;
        }
        else if(!maximizingPlayer){
            player5 = 1;
        }
        BoardLogic3 boardLogic = new BoardLogic3(node.player , newNode.game , numberOfRows,numberOfColumns);



        if(depth == 0 || boardLogic.checkForWin()){
            int result = boardLogic.evalulationFunction(node);
            node.score = result;
            return result;
        }

        if (maximizingPlayer) {
            int v = -1000000000;
            int col = 0;

            for (MyNode child : generateChildren(newNode)) {
                col++;
               // printNode(child.game);
                v = Math.max(v, miniMaxAB(child, depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, v);
                node.score = v ;
                if(depth == mainDepth){
                    possibleValues.add(child);
                }
               // String t = child.score + "";
                //Log.d("scoremax", t);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }

        else {
            int v = 1000000000;
            for (MyNode child : generateChildren(newNode)) {
                //printNode(child.game);

                v = Math.min(v, miniMaxAB(child, depth - 1, alpha, beta, true));
                beta = Math.min(beta, v);
                node.score = v ;
                if(depth == mainDepth){
                    possibleValues.add(child);
                }
                //String t = child.score + "";
               //Log.d("scoremin", t);
                if (beta <= alpha) {
                    break;
                }
            }
            return v;
        }


    }

    private  int findMove(int bestScore){

        String s = "Possible value size = " + possibleValues.size() + "" ;
        Log.d("possible" , s);

        for(int i = 0 ; i< possibleValues.size() ; i++){
            if(possibleValues.get(i).score == bestScore){
                //String s2 = "Parent  = " + possibleValues.get(i).col + "" ;
                //Log.d("node" , s2);
                //printNode(possibleValues.get(i).game);

                return possibleValues.get(i).col;
            }
        }
        return -1;
    }

/*
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

*/


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
