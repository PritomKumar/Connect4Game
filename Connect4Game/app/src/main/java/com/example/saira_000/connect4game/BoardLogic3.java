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

    private  int twoConnectHorizontal(int player){

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

    private  int twoConnectVertical(int player){

        int twoConnectVertical=0;

        for(int i=0 ; i<=numberOfRows-2 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){


                if(cells[i][j] == player && cells[i+1][j] == player){
                    twoConnectVertical++;
                }
            }
        }
        String t = twoConnectVertical +"";
        Log.d("twoverti", t);
        return  twoConnectVertical;

    }

    private  int twoConnectAsending(int player){

        int twoConnectAsending=0;

        for(int i=0 ; i<=numberOfRows - 2 ; i++){
            for (int j=0 ; j<=numberOfColumns-2 ; j++){

                if(cells[i][j] == player && cells[i+1][j+1] == player){
                    twoConnectAsending++;
                }
            }
        }
        String t = twoConnectAsending +"";
        Log.d("twoase", t);
        return  twoConnectAsending;

    }

    private  int twoConnectDesending(int player){

        int twoConnectDesending=0;

        for(int i=numberOfRows-1 ; i>=1 ; i--){
            for (int j=0 ; j<=numberOfColumns-2   ; j++){

                if(cells[i][j] == player && cells[i-1][j+1] == player){
                    twoConnectDesending++;
                }
            }
        }
        String t = twoConnectDesending +"";
        Log.d("twodes", t);
        return  twoConnectDesending;

    }
    
    private  int twoConnectCount(int player){
        int count = twoConnectVertical( player)*10 + twoConnectHorizontal(player)*10
                + twoConnectAsending(player)*15 + twoConnectDesending(player)*15;
        return count;
    }


    private  int threeConnectHorizontal(int player){

        int threeConnectHorizontal=0;

        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<=numberOfColumns-3 ; j++){

                if(cells[i][j] == player && cells[i][j+1] == player && cells[i][j+2] == player){
                    threeConnectHorizontal++;
                }
            }
        }
        String t = threeConnectHorizontal +"";
        Log.d("threehori", t);
        return  threeConnectHorizontal;

    }

    private  int threeConnectVertical(int player){

        int threeConnectVertical=0;

        for(int i=0 ; i<=numberOfRows-3 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){


                if(cells[i][j] == player && cells[i+1][j] == player && cells[i+2][j] == player){
                    threeConnectVertical++;
                }
            }
        }
        String t = threeConnectVertical +"";
        Log.d("threeverti", t);
        return  threeConnectVertical;

    }

    private  int threeConnectAsending(int player){

        int threeConnectAsending=0;

        for(int i=0 ; i<=numberOfRows - 3 ; i++){
            for (int j=0 ; j<=numberOfColumns-3 ; j++){

                if(cells[i][j] == player && cells[i+1][j+1] == player && cells[i+2][j+2] == player){
                    threeConnectAsending++;
                }
            }
        }
        String t = threeConnectAsending +"";
        Log.d("threease", t);
        return  threeConnectAsending;

    }

    private  int threeConnectDesending(int player){

        int threeConnectDesending=0;

        for(int i=numberOfRows-1 ; i>=2 ; i--){
            for (int j=0 ; j<=numberOfColumns-3   ; j++){

                if(cells[i][j] == player && cells[i-1][j+1] == player  && cells[i-2][j+2] == player){
                    threeConnectDesending++;
                }
            }
        }
        String t = threeConnectDesending +"";
        Log.d("threedes", t);
        return  threeConnectDesending;

    }

    private  int threeConnectCount(int player){
        int count = threeConnectVertical(player)*100 + threeConnectHorizontal(player)*100
                + threeConnectAsending(player)*150 + threeConnectDesending(player)*150;
        return count;
    }


    private  int fourConnectHorizontal(int player){

        int fourConnectHorizontal=0;

        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<=numberOfColumns-4 ; j++){

                if(cells[i][j] == player && cells[i][j+1] == player
                        && cells[i][j+2] == player && cells[i][j+3] == player){
                    fourConnectHorizontal++;
                }
            }
        }
        String t = fourConnectHorizontal +"";
        Log.d("fourhori", t);
        return  fourConnectHorizontal;

    }

    private  int fourConnectVertical(int player){

        int fourConnectVertical=0;

        for(int i=0 ; i<=numberOfRows-4 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){


                if(cells[i][j] == player && cells[i+1][j] == player
                        && cells[i+2][j] == player && cells[i+3][j] == player){
                    fourConnectVertical++;
                }
            }
        }
        String t = fourConnectVertical +"";
        Log.d("fourverti", t);
        return  fourConnectVertical;

    }

    private  int fourConnectAsending(int player){

        int fourConnectAsending=0;

        for(int i=0 ; i<=numberOfRows - 4 ; i++){
            for (int j=0 ; j<=numberOfColumns-4 ; j++){

                if(cells[i][j] == player && cells[i+1][j+1] == player
                        && cells[i+2][j+2] == player && cells[i+3][j+3] == player){
                    fourConnectAsending++;
                }
            }
        }
        String t = fourConnectAsending +"";
        Log.d("fourase", t);
        return  fourConnectAsending;

    }

    private  int fourConnectDesending(int player){

        int fourConnectDesending=0;

        for(int i=numberOfRows-1 ; i>=3 ; i--){
            for (int j=0 ; j<=numberOfColumns-4   ; j++){

                if(cells[i][j] == player && cells[i-1][j+1] == player
                        && cells[i-2][j+2] == player && cells[i-3][j+3] == player){

                    fourConnectDesending++;
                }
            }
        }
        String t = fourConnectDesending +"";
        Log.d("fourdes", t);
        return  fourConnectDesending;

    }

    private  int fourConnectCount(int player){
        int count = fourConnectVertical(player)*1000 + fourConnectHorizontal(player)*1000
                + fourConnectAsending(player)*1500 + fourConnectDesending(player)*1500;
        return count;
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
                winCount = -1000000;
                return winCount;
            } else {
                winCount = 10*(twoConnectCount(2) - twoConnectCount(1))
                        + 50* (threeConnectCount(2) - threeConnectCount(1))
                        + 100* (fourConnectCount(2)) - fourConnectCount(1)    ;

                winCount = -winCount;
                String t = winCount + "";
                Log.d("winCount", t);
                return winCount;
            }
        }
        else if(node.player == 1) {
            if (checkForWin()) {
                winCount = 1000000;
                return winCount;
            } else {
                winCount = 10*(twoConnectCount(1) - twoConnectCount(2))
                        + 50* (threeConnectCount(1) - threeConnectCount(2))
                        + 100* (fourConnectCount(1)) - fourConnectCount(2) ;

                winCount = winCount;

                String t = winCount + "";
                Log.d("winCount", t);
                return winCount;
            }
        }
        return  winCount;
    }


}
