package com.example.saira_000.connect4game;

import android.util.Log;

public class BoardLogic3 {

    private int player;
    private int opponant;
    public int[][] cells;
    private int[][] backUpCells;
    private int numberOfColumns;
    private int numberOfRows;
    private int winCount=0;

    public BoardLogic3(int player, int[][] cells, int numberOfRows , int numberOfColumns) {
        this.player = player;
        this.cells = cells;
        this.backUpCells = cells;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        this.opponant = player;
    }

    public boolean checkForWin(){
        if(horizontalCheck() || verticalCheck() || ascendingDiagonalCheck() || descendingDiagonalCheck()){
            return  true;
        }
        else {
            return  false;
        }
    }


    public boolean horizontalCheck() {
        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<=numberOfColumns-4 ; j++){

                    if(cells[i][j]== player && cells[i][j+1]== player
                            && cells[i][j+2]== player && cells[i][j+3]== player){
                        return  true;
                    }

            }
        }
        return  false;
    }

    public boolean verticalCheck() {
        for(int i=0 ; i<=numberOfRows-4 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){

                    String t = i+3 +"";
                    Log.d("verti", t);
                    if(cells[i][j]== player && cells[i+1][j] == player
                            && cells[i+2][j]== player && cells[i+3][j]== player){
                        return  true;
                    }

            }
        }
        return  false;
    }
    public boolean ascendingDiagonalCheck() {
        for(int i=0 ; i<=numberOfRows - 4 ; i++){
            for (int j=0 ; j<=numberOfColumns-4 ; j++){

                    String t = i+3 +"";
                    Log.d("asi", t);
                    if(cells[i][j]== player && cells[i+1][j+1]== player
                            && cells[i+2][j+2]== player && cells[i+3][j+3]== player){
                        return  true;
                    }
                }

        }
        return  false;
    }

    public boolean descendingDiagonalCheck() {

        for(int i=numberOfRows-1 ; i>=3 ; i--){
            for (int j=0 ; j<=numberOfColumns-4   ; j++){

                    String t = i+3 +"";
                    Log.d("dsi", t);
                    if(cells[i][j]== player && cells[i-1][j+1]== player
                            && cells[i-2][j+2]== player && cells[i-3][j+3]== player){
                        return  true;
                    }

            }
        }
        return  false;
    }
    
/*
    public boolean horizontalCheck() {
        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                int player2 = cells[i][j];
                if(player2 == opponant){
                    if(cells[i][j]== player && cells[i][j+1]== player
                            && cells[i][j+2]== player && cells[i][j+3]== player){
                            return  true;
                    }
                }
            }
        }
        return  false;
    }

    public boolean verticalCheck() {
        for(int i=0 ; i<numberOfRows-3 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){
                int player2 = cells[i][j];
                if(player2 == player){
                    String t = i+3 +"";
                    Log.d("verti", t);
                    if(cells[i][j]== player && cells[i+1][j] == player
                            && cells[i+2][j]== player && cells[i+3][j]== player){
                        return  true;
                    }
                }
            }
        }
        return  false;
    }

    public boolean ascendingDiagonalCheck() {
        for(int i=3 ; i<numberOfRows ; i++){
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                int player2 = cells[i][j];
                if(player2 == player){
                    String t = i+3 +"";
                    Log.d("asi", t);
                    if(cells[i][j]== player && cells[i-1][j+1]== player
                            && cells[i-2][j+2]== player && cells[i-3][j+3]== player){
                        return  true;
                    }
                }
            }
        }
        return  false;
    }

    public boolean descendingDiagonalCheck() {

        for(int i=3 ; i<numberOfRows ; i++){
            for (int j=3 ; j<numberOfColumns ; j++){
                int player2 = cells[i][j];
                if(player2 == player){
                    String t = i+3 +"";
                    Log.d("dsi", t);
                    if(cells[i][j]== player && cells[i-1][j-1]== player
                            && cells[i-2][j-2]== player && cells[i-3][j-3]== player){
                        return  true;
                    }
                }
            }
        }
        return  false;
    }
*/

    private  int twoConnectHorizontal(){

        int twoConnectHorizontal=0;

        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<=numberOfColumns-2 ; j++){

                if(cells[i][j] == player && cells[i][j+1] == player){
                    twoConnectHorizontal++;
                }
            }
        }
        String t = twoConnectHorizontal +"";
        Log.d("twohori", t);
        return  twoConnectHorizontal;
        
    }

    public int horizontalCheckCount() {

        int horizontalCount = 0;
        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<=numberOfColumns-4 ; j++){

                if(cells[i][j] == 0 && cells[i][j+1] == 0
                        && cells[i][j+2] == 0 && cells[i][j+3] == 0){
                    horizontalCount++;
                }

            }
        }
        String t = horizontalCount +"";
        Log.d("horizontalCount", t);
        return  horizontalCount;
    }

    public int verticalCheckCount() {
        int verticalCount =0 ;
        for(int i=0 ; i<=numberOfRows-4 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){

                if(cells[i][j] == 0 && cells[i+1][j] == 0
                        && cells[i+2][j] == 0 && cells[i+3][j] == 0){
                    verticalCount++;
                }

            }
        }
        String t = verticalCount +"";
        Log.d("vertiCount", t);
        return  verticalCount;
    }

    public int ascendingDiagonalCheckCount() {
        int ascendingDiagonalCount=0;
        for(int i=0 ; i<=numberOfRows - 4 ; i++){
            for (int j=0 ; j<=numberOfColumns-4 ; j++){
                String t = i+3 +"";
                Log.d("asi", t);
                if(cells[i][j]== 0 && cells[i+1][j+1]== 0
                        && cells[i+2][j+2]== 0 && cells[i+3][j+3]== 0){
                    ascendingDiagonalCount++;
                }
            }
        }

        String t = ascendingDiagonalCount +"";
        Log.d("ascendingDiagonalCount", t);
        return  ascendingDiagonalCount;
    }

    public int descendingDiagonalCheckCount() {
        int descendingDiagonalCount = 0;
        for(int i=numberOfRows-1 ; i>=3 ; i--){
            for (int j=0 ; j<=numberOfColumns-4   ; j++){
                int player2 = cells[i][j];
                if(player2 ==0){
                    String t = i+3 +"";
                    Log.d("dsi", t);
                    if(cells[i][j]== 0 && cells[i-1][j+1]== 0
                            && cells[i-2][j+2]== 0 && cells[i-3][j+3]== 0){
                        descendingDiagonalCount++;
                    }
                }
            }
        }

        String t = descendingDiagonalCount +"";
        Log.d("descendingDiagonalCount", t);
        return  descendingDiagonalCount;
    }



    public int evalulationFunction(MyNode node){

        winCount = 0;

        if(node.player == 2) {
            if (checkForWin()) {
                winCount = -100000;
                return winCount;
            } else {
                winCount = horizontalCheckCount() + verticalCheckCount()
                        + ascendingDiagonalCheckCount() + descendingDiagonalCheckCount();
                winCount = -winCount;
                String t = winCount + "";
                Log.d("winCount", t);
                return winCount;
            }
        }
        else if(node.player == 1) {
            if (checkForWin()) {
                winCount = 100000;
                return winCount;
            } else {
                winCount = horizontalCheckCount() + verticalCheckCount()
                        + ascendingDiagonalCheckCount() + descendingDiagonalCheckCount();

                winCount = winCount;

                String t = winCount + "";
                Log.d("winCount", t);
                return winCount;
            }
        }
        return  winCount;
    }


}
