package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")

class State  implements Cloneable, Serializable
{

	int numberOfRows, numberOfColumns;
	Cell[][] board;
	public boolean winCondition;

	Board.Turn turn;

	/* basic methods for constructing and proper hashing of State objects */
	public State(int n_rows, int n_cols ){
		this.numberOfRows = n_rows;
		this.numberOfColumns = n_cols;
		this.board=new Cell[n_rows][n_cols];
		
		//fill the board up with blanks
		for(int i=0; i < n_rows; i++) {
			for (int j = 0; j < n_cols; j++) {
				this.board[i][j] = new Cell();
			}
		}
		reset();


	}
	
	public boolean equals(Object obj){
		//have faith and cast
		State other=(State)obj;
		return Arrays.deepEquals(this.board, other.board);
		/* Arrays.deepEquals() is used to check whether two arrays of single dimensional or
		 *multi-dimensional arrays are equal or not. 
		 *It can compare two nested arrays (i.e. multidimensional array),.*/
	}
	
	public int hashCode(){
		String b = "";
		for(int i=0; i< board.length; i++)
			b += String.valueOf(board[0]);
		return b.hashCode();
	}

	
	// Prototype Pattern Here.
	public Object clone() throws CloneNotSupportedException {
        State new_state = new State(this.numberOfRows, this.numberOfColumns );
		for (int i = 0; i< this.numberOfRows; i++) {
			new_state.board[i] =  this.board[i].clone();
		}
		return new_state;
	}

	
	/* returns a list of actions that can be taken from the current state
	actions are integers representing the column where a coin can be dropped */
	public ArrayList<Integer> getLegalActions(){
		ArrayList<Integer> actions = new ArrayList<Integer>();
		for(int j=0; j < this.numberOfColumns; j++) {

			if(lastAvailableRow(j)!=-1) {
				actions.add(j);
			}

		}
		return actions;
	}

	public int drop(int col)
	{
		//Check if game has ended.
		if (checkForWin())
		{
			return -1;
		}
		//Check col
		int row = 5;
		for ( ; row>=0 && board[row][col].empty; row--)
		{}
		// If the row is -1, then the col is already full.
		if (row==-1)
		{
			return -1;
		}
		// Fill the row of the given col with player's checker.
		else
		{
			board[row][col].setPlayer(turn);
			return row;
		}

	}
	/* returns a State object that is obtained by the agent (parameter)
	performing an action (parameter) on the current state */
	public State generateSuccessor(Board.Turn agent, int action) throws CloneNotSupportedException{
		
		int row= lastAvailableRow(action);
		State new_state = (State) this.clone();
		if(row!=-1) {
			new_state.turn = agent;
			new_state.board[row][action].setPlayer(agent);
		}
		return new_state;
	}



	/* returns True/False if the agent(parameter) has won the game
	by checking all numberOfRows/columns/diagonals for a sequence of >=4 */
	public boolean isGoal(Board.Turn player){

		checkForWin(player);
		return false;
	}

	public boolean checkForWin(Board.Turn player){
		if(horizontalCheck(player) || verticalCheck(player)
				|| ascendingDiagonalCheck(player) || descendingDiagonalCheck(player)){
			return  true;
		}
		else {
			return  false;
		}
	}

	public State deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (State) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException el) {
			el.printStackTrace();
			return null;
		}
	}

	public boolean horizontalCheck(Board.Turn player) {
		for(int i=0 ; i<this.numberOfRows ; i++){
			for (int j=0 ; j<this.numberOfColumns-3 ; j++){
				Cell cell = board[i][j];
				if(cell.player == player){
					if(board[i][j].player== player && board[i][j+1].player== player
							&& board[i][j+2].player== player && board[i][j+3].player== player){
						return  true;
					}
				}
			}
		}
		return  false;
	}

	public boolean verticalCheck(Board.Turn player) {
		for(int i=0 ; i<this.numberOfRows-3 ; i++){
			for (int j=0 ; j<this.numberOfColumns ; j++){
				Cell cell = board[i][j];
				if(cell.player == player){
					String t = i+3 +"";
					Log.d("verti", t);
					if(board[i][j].player== player && board[i+1][j].player== player
							&& board[i+2][j].player== player && board[i+3][j].player== player){
						return  true;
					}
				}
			}
		}
		return  false;
	}

	public boolean ascendingDiagonalCheck(Board.Turn player) {
		for(int i=3 ; i<this.numberOfRows ; i++){
			for (int j=0 ; j<this.numberOfColumns-3 ; j++){
				Cell cell = board[i][j];
				if(cell.player == player){
					String t = i+3 +"";
					Log.d("asi", t);
					if(board[i][j].player== player && board[i-1][j+1].player== player
							&& board[i-2][j+2].player== player && board[i-3][j+3].player== player){
						return  true;
					}
				}
			}
		}
		return  false;
	}

	public boolean descendingDiagonalCheck(Board.Turn player) {

		for(int i=3 ; i<this.numberOfRows ; i++){
			for (int j=3 ; j<this.numberOfColumns ; j++){
				Cell cell = board[i][j];
				if(cell.player == player){
					String t = i+3 +"";
					Log.d("dsi", t);
					if(board[i][j].player== player && board[i-1][j-1].player== player
							&& board[i-2][j-2].player== player && board[i-3][j-3].player== player){
						return  true;
					}
				}
			}
		}
		return  false;
	}


	/* returns the value of each state for minimax to min/max over at
	zero depth. Right now it's pretty trivial, looking for only goal states.
	(This would be perfect for infinite depth minimax. Not so great for d=2) */
	public int evaluationFunction(State st) {

		BoardLogic logic = new BoardLogic(turn , st.board , numberOfRows , numberOfColumns);
		int value = (int)logic.evalulationFunction();

		if(st.turn == Board.Turn.PLAYER_2) {
			if (this.isGoal(st.turn )) {
				return 1000000;

			}
			else{
				String t = value+ "";
				Log.d("evalup" , t);
				return value;
			}
		}

		else {
			if (this.isGoal( Board.Turn.PLAYER_1 )) {
				return -1000000;

			}
			else{
				value = -value;
				String t = value+ "";
				Log.d("evalue" , t);
				return value;
			}
		}
	}

	public void reset() {
		winCondition = false;
		turn = Board.Turn.PLAYER_1;
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfColumns; col++) {
				board [row][col] = new Cell();
			}
		}
	}



	public int lastAvailableRow(int col) {
		for (int row = numberOfRows - 1; row >= 0; row--) {
			if (board[row][col].empty) {
				return row;
			}
		}
		return -1;
	}

	public void occupyCell( int row , int col) {

		board[row][col].setPlayer(turn);
	}

	public void toggleTurn() {
		if (turn == Board.Turn.PLAYER_1) {
			turn = Board.Turn.PLAYER_2;
		} else {
			turn = Board.Turn.PLAYER_1;
		}
	}

	public boolean checkForWin() {
		BoardLogic boardLogic = new BoardLogic(turn , board , numberOfRows , numberOfColumns);

		if(boardLogic.checkForWin()){
			winCondition = true;
		}
		return  boardLogic.checkForWin();
	}
}
	
	
	
	
