package com.example.saira_000.connect4game;

import android.util.Log;

import java.io.Serializable;

import java.lang.reflect.Array;
import java.util.ArrayList;
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


}
