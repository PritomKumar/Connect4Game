package com.example.saira_000.connect4game;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable {

    private int utility;
    private Cell[][] state;
    private Expand expand = new Expand();
    private int score;

    public Node(Cell[][] state){
        this.state= state;
    }

    public Cell[][] Getstate(){
        return state;
    }

    public double Getutility(){
        return utility;
    }

    public void Setutility(int utility){
        this.utility = utility;
    }

    public int Getscore(){
        return score;
    }

    public void Setscore(int move){
        this.score = move;
    }

    public List<Node> Expand(boolean Player)
    {
        return expand.MakeChildren(state, Player);
    }

}
