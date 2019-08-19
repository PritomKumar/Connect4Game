package com.example.saira_000.connect4game;

public class Cell {
  public boolean empty = true;
  public Board.Turn player;

  public Cell() {

    empty = true;
  }

  public void setPlayer(Board.Turn player) {
    this.player = player;
    empty = false;
  }
}
