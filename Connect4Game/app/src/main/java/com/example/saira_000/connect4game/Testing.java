package com.example.saira_000.connect4game;

import android.util.Log;

public class Testing {

    private Board.Turn player;
    private Cell[][] cells;
    private Cell[][] backUpCells;
    private int numberOfColumns = 7 ;
    private int numberOfRows = 6;
    private int winCount=0;

    public Testing(Board.Turn player, Cell[][] cells) {
        this.player = player;
        this.cells = cells;
        this.backUpCells = cells;
    }


    public int horizontalCheckCount() {

        int horizontalCount = 0;
        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                Cell cell = cells[i][j];
                if(cell.player == player){
                    if(cells[i][j].player== player && cells[i][j+1].player== player
                            && cells[i][j+2].player== player && cells[i][j+3].player== player){
                        horizontalCount++;
                    }
                }
            }
        }
        String t = horizontalCount +"";
        Log.d("horizontalCount", t);
        return  horizontalCount;
    }

    public int verticalCheckCount() {
        int verticalCount =0 ;
        for(int i=0 ; i<numberOfRows-3 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){
                Cell cell = cells[i][j];
                if(cell.player == player){
                    if(cells[i][j].player== player && cells[i+1][j].player== player
                            && cells[i+2][j].player== player && cells[i+3][j].player== player){
                        verticalCount++;
                    }
                }
            }
        }
        String t = verticalCount +"";
        Log.d("vertiCount", t);
        return  verticalCount;
    }

    public int ascendingDiagonalCheckCount() {
        int ascendingDiagonalCount=0;
        for(int i=3 ; i<numberOfRows ; i++){
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                Cell cell = cells[i][j];
                if(cell.player == player){
                    if(cells[i][j].player== player && cells[i-1][j+1].player== player
                            && cells[i-2][j+2].player== player && cells[i-3][j+3].player== player){
                        ascendingDiagonalCount++;
                    }
                }
            }
        }

        String t = ascendingDiagonalCount +"";
        Log.d("ascendingDiagonalCount", t);
        return  ascendingDiagonalCount;
    }

    public int descendingDiagonalCheckCount() {
        int descendingDiagonalCount = 0;
        for(int i=3 ; i<numberOfRows ; i++){
            for (int j=3 ; j<numberOfColumns ; j++){
                Cell cell = cells[i][j];
                if(cell.player == player){
                    String t = i+3 +"";
                    Log.d("dsi", t);
                    if(cells[i][j].player== player && cells[i-1][j-1].player== player
                            && cells[i-2][j-2].player== player && cells[i-3][j-3].player== player){
                        descendingDiagonalCount++;
                    }
                }
            }
        }

        String t = descendingDiagonalCount +"";
        Log.d("descendingDiagonalCount", t);
        return  descendingDiagonalCount;
    }


    public void fillCellsWithCurrentPlayer(){

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                if(cells [row][col].empty){
                    cells [row][col].setPlayer(player);
                }
            }
        }

    }
    public int evalulationFunction(){

        winCount = 0;

        fillCellsWithCurrentPlayer();
        winCount = horizontalCheckCount() + verticalCheckCount()
                    + ascendingDiagonalCheckCount() + descendingDiagonalCheckCount();

        String t = winCount +"";
        Log.d("winCount", t);
        return  winCount;


    }


/*
    public int horizontalCheckCount(Cell [][] lalCell) {

        int horizontalCount = 0;
        for(int i=0 ; i<numberOfRows ; i++){
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                Cell cell = lalCell[i][j];
                if(cell.player == player){
                    if(lalCell[i][j].player== player && lalCell[i][j+1].player== player
                            && lalCell[i][j+2].player== player && lalCell[i][j+3].player== player){
                        horizontalCount++;
                    }
                }
            }
        }
        String t = horizontalCount +"";
        Log.d("horizontalCount", t);
        return  horizontalCount;
    }

    public int verticalCheckCount(Cell [][] lalCell) {
        int verticalCount =0 ;
        for(int i=0 ; i<numberOfRows-3 ; i++){
            for (int j=0 ; j<numberOfColumns ; j++){
                Cell cell = lalCell[i][j];
                if(cell.player == player){
                    if(lalCell[i][j].player== player && lalCell[i+1][j].player== player
                            && lalCell[i+2][j].player== player && lalCell[i+3][j].player== player){
                        verticalCount++;
                    }
                }
            }
        }
        String t = verticalCount +"";
        Log.d("vertiCount", t);
        return  verticalCount;
    }

    public int ascendingDiagonalCheckCount(Cell [][] lalCell) {
        int ascendingDiagonalCount=0;
        for(int i=3 ; i<numberOfRows ; i++){
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                Cell cell = lalCell[i][j];
                if(cell.player == player){
                    if(lalCell[i][j].player== player && lalCell[i-1][j+1].player== player
                            && lalCell[i-2][j+2].player== player && lalCell[i-3][j+3].player== player){
                        ascendingDiagonalCount++;
                    }
                }
            }
        }

        String t = ascendingDiagonalCount +"";
        Log.d("ascendingDiagonalCount", t);
        return  ascendingDiagonalCount;
    }

    public int descendingDiagonalCheckCount(Cell [][] lalCell) {
        int descendingDiagonalCount = 0;
        for(int i=3 ; i<numberOfRows ; i++){
            for (int j=3 ; j<numberOfColumns ; j++){
                Cell cell = lalCell[i][j];
                if(cell.player == player){
                    String t = i+3 +"";
                    Log.d("dsi", t);
                    if(lalCell[i][j].player== player && lalCell[i-1][j-1].player== player
                            && lalCell[i-2][j-2].player== player && lalCell[i-3][j-3].player== player){
                        descendingDiagonalCount++;
                    }
                }
            }
        }

        String t = descendingDiagonalCount +"";
        Log.d("descendingDiagonalCount", t);
        return  descendingDiagonalCount;
    }




    public Cell [][] fillCellsWithCurrentPlayer(Cell [][] newCells){

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                if(newCells [row][col].empty){
                    newCells [row][col].setPlayer(player);
                }
            }
        }
        return newCells;

    }
    public int evalulationFunction(Cell [][] newCells){

        winCount = 0;
        if(checkForWin()){
            winCount = 10000;
            return  winCount;
        }
        else {
            //Cell [][] lalCell = newCells;
            Cell [][] lalCell = fillCellsWithCurrentPlayer(newCells);
            winCount = horizontalCheckCount(lalCell) + verticalCheckCount(lalCell)
                    + ascendingDiagonalCheckCount(lalCell) + descendingDiagonalCheckCount(lalCell);

            String t = winCount +"";
            Log.d("winCount", t);
            return  winCount;
        }

    }

 */
}
