package com.example.saira_000.connect4game;

public class AIPlayer {

    private BoardLogic boardLogic;
    private Node currentNode;

    public int babyLevel(){
        int randomInt = (int)(6.0 * Math.random());
        return  randomInt;
    }

    public int easyLevel(){



        return 0 ;
    }

    public int mediumLevel(Cell [][] currentCell){

        currentNode = new Node(currentCell);

        return 0 ;
    }

    public int hardLevel(){

        return 0 ;
    }

    public int veryHardLevel(){

        return 0 ;
    }


    public int miniMax(Node current ,int depth , boolean maximisingPlayer){

        boardLogic = new BoardLogic(Board.Turn.PLAYER_2 , currentNode.cells , 6 , 7 );
        if(depth ==0 || boardLogic.checkForWin()){

        }
        return 0;
    }
}
