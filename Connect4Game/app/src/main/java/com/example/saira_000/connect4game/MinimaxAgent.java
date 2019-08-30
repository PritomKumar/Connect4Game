package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.ArrayList;

class MinimaxAgent {
	
	int depth , maxDepth;
	int [][] newGrid;
	int x=0;
	
	public MinimaxAgent(int depth)
	{
		this.depth = depth;
		this.maxDepth = depth;
	}	

	public int[][] translateCellToInt(Cell[][] copy ){
		int [][] newGrid = new int[6][7];

		for (int row = 0 ; row<6 ; row++){
			for (int col = 0 ; col < 7 ; col++){
				if(copy[row][col].player == Board.Turn.PLAYER_1){
					newGrid[row][col] = 1;
				}
				else if(copy[row][col].player == Board.Turn.PLAYER_2){
					newGrid[row][col] = 2;
				}
				else{
					newGrid[row][col] = 0;
				}
			}
		}


		return  newGrid;
	}

	private int[][] copyGrid(int[][]copy)
	{
		int[][] newgrid = new int [7][6];
		for (int row =0; row<6; row++)
		{
			for (int col=0; col<7; col++)
			{
				newgrid[col][row]=copy[col][row];
			}
		}
		return newgrid;
	}


	public int getAction(State st) throws CloneNotSupportedException
	{
		State newState = st.deepClone();

		int val = max_value(newState, 0);
		//return max_value(st, depth);
		return x;
		
	}
	
	
	public int max_value(State st, int d) throws CloneNotSupportedException
	{

		int bestValue = -10000000;
		newGrid = translateCellToInt(st.board);
		int bestPath =0;

		if(d == maxDepth) {
			return st.evaluationFunction(st);
		}
		else
		{
			for(int i = 0; i<7;i++){

				//children = st.getLegalActions();

				State maxState = st.deepClone();
				maxState.turn = Board.Turn.PLAYER_2;

				int r= maxState.drop(i);

				if(r!=-1){


					if (d < maxDepth)
					{

						int v = min_value(maxState,+1);
						if (v >=bestValue)
						{
							bestPath = i;
							bestValue = v;
						}
					}
				}

			}
		}

		if (d==0)
		{
			//System.out.println("Turn end");
			return bestPath;
		}
		else
		{
			//System.out.println(bestValue);
			return bestValue;
		}
	}
	
	
	public int min_value(State st, int d) throws CloneNotSupportedException
	{

		int bestValue = 10000000;

		int bestPath =0;

		if(d == maxDepth) {
			return st.evaluationFunction(st);
		}
		else
		{
			for(int i = 0; i<7;i++){

				//children = st.getLegalActions();

				State minState = st.deepClone();
				minState.turn = Board.Turn.PLAYER_1;

				int r= minState.drop(i);

				if(r!=-1){


					if (d < maxDepth)
					{

						int v = max_value(minState,+1);
						if (v <=bestValue)
						{
							bestPath = i;
							bestValue = v;
						}
					}
				}

			}
		}

		if (d==0)
		{
			//System.out.println("Turn end");
			return bestPath;
		}
		else
		{
			//System.out.println(bestValue);
			return bestValue;
		}
	}
	
	
	
}
