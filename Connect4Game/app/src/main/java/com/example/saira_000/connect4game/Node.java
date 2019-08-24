package com.example.saira_000.connect4game;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable {

    private int utility;
    private Cell[][] state;
    private  Board.Turn player2;
    private Expand expand = new Expand();
    private int score;

    public Node(Cell[][] state , Board.Turn player){
        this.state= state;
        this.player2 = player;
    }

    public Cell[][] getstate(){
        return state;
    }

    public int getUtility(){
        return utility;
    }

    public void setUtility(int utility){
        this.utility = utility;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int move){
        this.score = move;
    }

    public Board.Turn getPlayer() {
        return player2;
    }

    public void setPlayer(Board.Turn player) {
        this.player2 = player;
    }

    public List<Node> expandNode(Board.Turn player)
    {
        return expand.MakeChildren(state, player);
    }

}
