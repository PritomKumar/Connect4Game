package com.example.saira_000.connect4game;

import android.util.Log;

public class BoardLogic {

    private Board.Turn player;
    private Cell[][] cells;
    private int numberOfColumns;
    private int numberOfRows;

    public BoardLogic(Board.Turn player, Cell[][] cells, int numberOfRows ,int numberOfColumns) {
        this.player = player;
        this.cells = cells;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
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
            for (int j=0 ; j<numberOfColumns-3 ; j++){
                Cell cell = cells[i][j];
                if(cell.player == player){
                    if(cells[i][j].player== player && cells[i][j+1].player== player
                            && cells[i][j+2].player== player && cells[i][j+3].player== player){
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
                Cell cell = cells[i][j];
                if(cell.player == player){
                    String t = i+3 +"";
                    Log.d("verti", t);
                    if(cells[i][j].player== player && cells[i+1][j].player== player
                            && cells[i+2][j].player== player && cells[i+3][j].player== player){
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
                Cell cell = cells[i][j];
                if(cell.player == player){
                    String t = i+3 +"";
                    Log.d("asi", t);
                    if(cells[i][j].player== player && cells[i-1][j+1].player== player
                            && cells[i-2][j+2].player== player && cells[i-3][j+3].player== player){
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
                Cell cell = cells[i][j];
                if(cell.player == player){
                    String t = i+3 +"";
                    Log.d("dsi", t);
                    if(cells[i][j].player== player && cells[i-1][j-1].player== player
                            && cells[i-2][j-2].player== player && cells[i-3][j-3].player== player){
                        return  true;
                    }
                }
            }
        }
        return  false;
    }


    public int evalulationFunction(){

    }
}
