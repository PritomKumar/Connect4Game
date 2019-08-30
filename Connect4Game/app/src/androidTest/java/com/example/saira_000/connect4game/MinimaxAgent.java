package com.example.saira_000.connect4game;

import android.util.Log;

import java.util.ArrayList;

class MinimaxAgent {
	
	int depth;
	int x=0;
	
	public MinimaxAgent(int depth)
	{
		this.depth = depth;
	}	
	
	
	public int getAction(State st) throws CloneNotSupportedException
	{
		double val = max_value(st, depth);
		//return max_value(st, depth);
		return x;
		
	}
	
	
	public double max_value(State st, int d) throws CloneNotSupportedException
	{
		ArrayList<Integer> children = new ArrayList<Integer>();
		if(d == 0) {
			return st.evaluationFunction(st);
		}
		else
		{
			children = st.getLegalActions();
			double bestValue = -10000000;

			double value;
			//double value;
			for(int i = 0; i<children.size();i++){

				String temp = children.get(i) + "";
				Log.d("chil", temp );
				value = min_value(st.generateSuccessor(Board.Turn.PLAYER_2,children.get(i)),d-1);
				String t = value+ "";
				Log.d("maxvalue" , t);
				if(value >= bestValue)
				{
					bestValue =value;
					this.x = i;
				}
			}
			//System.out.println("x: "+this.x);
			return bestValue;
		}
	}
	
	
	public double min_value(State st, int d) throws CloneNotSupportedException
	{
		
		ArrayList<Integer> children = new ArrayList<Integer>();
		if(d == 0) {
			return st.evaluationFunction(st);
		}
		else
		{
			children = st.getLegalActions();

			double bestValue = 10000000;
			int x=0;
			double value;
			for(int i =0; i<children.size();i++)
			{

				String temp = children.get(i) + "";
				Log.d("chip", temp );
				value= max_value(st.generateSuccessor(Board.Turn.PLAYER_1,children.get(i)),d-1);
				String t = value+ "";
				Log.d("minvalue" , t);
				if(value <= bestValue) {
					bestValue = value;
				}

			}
			return bestValue;
		}
	}
	
	
	
}
