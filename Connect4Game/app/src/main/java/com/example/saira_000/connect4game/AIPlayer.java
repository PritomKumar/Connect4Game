package com.example.saira_000.connect4game;

public class AIPlayer {



    public int babyLevel(){
        int randomInt = (int)(6.0 * Math.random());
        return  randomInt;
    }

    public int easyLevel(){



        return 0 ;
    }

    public int mediumLevel(){

        return 0 ;
    }

    public int hardLevel(){

        return 0 ;
    }

    public int veryHardLevel(){

        return 0 ;
    }


    public int miniMax(Node current ,int depth , boolean maximisingPlayer){

        if(depth ==0 ){}
        return 0;
    }
}
