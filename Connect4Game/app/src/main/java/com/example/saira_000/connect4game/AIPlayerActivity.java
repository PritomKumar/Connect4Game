package com.example.saira_000.connect4game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AIPlayerActivity  extends AppCompatActivity implements View.OnClickListener {

    private int player1Score = 0;
    private int player2Score = 0;
    private ImageView[][] cells;
    private View boardView;
    private Board board;
    private  State state;
    private BoardLogic boardLogic;
    private AIPlayer3 aiPlayer;
    private AIPlayerActivity.ViewHolder viewHolder;
    private static int numberOfRows = 6;
    private static int numberOfColumns = 7;
    private int level ;

    private class ViewHolder {
        public TextView player1Win;
        public TextView player2Win;
        public TextView winnerText;
        public ImageView turnIndicatorImageView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiplayer);
        board = new Board(numberOfRows,numberOfColumns);
        state = new State(numberOfRows,numberOfColumns);
        boardView = findViewById(R.id.game_board);
        buildCells();
        playerTouch();

        Intent intent = getIntent();
        this.level = intent.getIntExtra("level" , 0 );

        aiPlayer = new AIPlayer3();
        viewHolder = new AIPlayerActivity.ViewHolder();
        viewHolder.player1Win = (TextView) findViewById(R.id.player1Win);
        viewHolder.player2Win = (TextView) findViewById(R.id.player2Win);
        viewHolder.player1Win.setText("0");
        viewHolder.player2Win.setText("0");
        viewHolder.turnIndicatorImageView = (ImageView) findViewById(R.id.turn_image);
        viewHolder.turnIndicatorImageView.setImageResource(resourceForTurn());
        viewHolder.winnerText = (TextView) findViewById(R.id.winner_text);
        viewHolder.winnerText.setVisibility(View.GONE);

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    @Override
    public void onClick(@NonNull View view) {
        //if (board.turn==Board.Turn.PLAYER_2) return;
        int col = colAtX(view.getX());
        String temp = view.getX() + "";
       // Toast.makeText(AIPlayerActivity.this , temp , Toast.LENGTH_SHORT).show();
        if (col != -1) {
            try {
                dropDisc(col);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    private void playerTouch() {
        
        boardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                   
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_POINTER_UP: 
                        case MotionEvent.ACTION_UP: {
                            int col = colAtX(motionEvent.getX());
                            String temp = motionEvent.getX() + "";
                            //Toast.makeText(AIPlayerActivity.this, temp, Toast.LENGTH_SHORT).show();
                            if (col != -1) {
                                try {
                                    dropDisc(col);
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            } 
                        }
                    }
                    return true;
            }
        });


    }


    private void buildCells() {
        cells = new ImageView[numberOfRows][numberOfColumns];
        for (int i=0; i<numberOfRows; i++) {
            ViewGroup row = (ViewGroup) ((ViewGroup) boardView).getChildAt(i);
            row.setClipChildren(false);
            for (int j=0; j<numberOfColumns; j++) {
                ImageView imageView = (ImageView) row.getChildAt(j);
                imageView.setImageResource(android.R.color.transparent);
                cells[i][j] = imageView;
            }
        }
    }

    private void dropDisc(int col) throws CloneNotSupportedException {
        if (state.winCondition) {
            return;
        }

        if(checkDraw()){
            return;
        }

        int row = state.lastAvailableRow(col);
        if (row == -1) {
            return;
        }


        final ImageView cell = cells[row][col];
        //float move = -(cell.getHeight() * row + cell.getHeight() + 15);
        float move = -(cell.getHeight() * row + cell.getHeight());
        cell.setY(move);
        cell.setImageResource(resourceForTurn());

        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, Math.abs(move));
        animation.setDuration(200);
        animation.setFillAfter(true);
        cell.startAnimation(animation);

        onEnterAnimationComplete();
        state.generateSuccessor(state.turn , col);
        //aiPlayer.printNode(state.board);

        if (state.checkForWin()) {
            win();
        }
        else if (checkDraw()){
            viewHolder.winnerText.setText("DRAW !!!");
            viewHolder.winnerText.setTextColor(Color.parseColor("#d500f9"));
            viewHolder.winnerText.setVisibility(View.VISIBLE);
        }
        else {

            toggleTurn();
        }
    }

    private void win() {
        int color;
        if( state.turn == Board.Turn.PLAYER_1 ) {
            color = Color.RED;
            player1Score ++;
        }
        else {
            color = Color.parseColor("#fdd835");
            player2Score++ ;
        }
        String temp = player1Score + "";
        viewHolder.player1Win.setText(temp);
        temp = player2Score + "";
        viewHolder.player2Win.setText(temp);
        if(state.turn == Board.Turn.PLAYER_1){
            viewHolder.winnerText.setText("YOU WIN!!!");
        }
        else{
            viewHolder.winnerText.setText("AI WINS!!!");
        }
        viewHolder.winnerText.setTextColor(color);
        viewHolder.winnerText.setVisibility(View.VISIBLE);
    }

    private boolean checkDraw(){
        for (int i=0 ; i<numberOfRows ; i++){
            for(int j=0 ; j < numberOfColumns ; j++){
                if(state.board[i][j].empty){
                    return false;
                }
            }
        }
        return  true;
    }

    private void aiTurn() throws CloneNotSupportedException {

        if(state.turn==Board.Turn.PLAYER_2) {

            Cell [][] newCells = new Cell[numberOfRows][numberOfColumns];
            //aiPlayer.printNode(state.board);

            State newState =  (State) state.deepClone();

           // copyBoard(newState.board , state.board);

            int playerTurn = 0;

            if(level == 0){
                playerTurn = aiPlayer.babyLevel();
            }

            else if(level == 2 ) {
                playerTurn = aiPlayer.easyLevel(state, level);
            }

            else if(level == 3 ) {
                playerTurn = aiPlayer.mediumLevel(state, level);
            }

            else if(level == 4 ) {
                playerTurn = aiPlayer.hardLevel(state, level);
            }

            else if(level == 5 ) {
                playerTurn = aiPlayer.veryHardLevel(state, level);
            }

            else {
                playerTurn = -1 ;
            }
            int col = aiColTest(playerTurn);
            //dropDisc(col);
            String temp = col + "";
            //Toast.makeText(AIPlayerActivity.this , temp , Toast.LENGTH_SHORT).show();
            if (col != -1){
                dropDisc(col);

            }
        }
    }

    private void copyBoard(Cell[][] newCells, Cell[][] cells) {

        for(int i= 0 ; i< numberOfRows ; i++){
            for (int j=0 ; j< numberOfColumns ; j++){
                newCells[i][j] = new Cell();
            }
        }

        for(int i= 0 ; i< numberOfRows ; i++){
            for (int j=0 ; j< numberOfColumns ; j++){
                newCells[i][j] = cells[i][j];
            }
        }
    }

    private void toggleTurn() throws CloneNotSupportedException {
        state.toggleTurn();
        viewHolder.turnIndicatorImageView.setImageResource(resourceForTurn());
        if(state.turn==Board.Turn.PLAYER_2) {
            aiTurn();
        }
    }

    private int colAtX(float x) {
        float colWidth = cells[0][0].getWidth();
        int col = (int) x / (int) colWidth;
        if (col < 0 || col > 6)
            return -1;
        return col;
    }

    private int aiColTest(int x) {
        int col =  x ;
        if (col < 0 || col > 6)
            return -1;
        return col;
    }


    private int resourceForTurn() {
        switch (state.turn) {
            case PLAYER_1:
                return R.drawable.red;
            case PLAYER_2:
                return R.drawable.yellow;
        }
        return R.drawable.yellow;
    }

    private void reset() {
        state.reset();
        viewHolder.winnerText.setVisibility(View.GONE);
        viewHolder.turnIndicatorImageView.setImageResource(resourceForTurn());
        for (int i=0; i<numberOfRows; i++) {
            for (int j=0; j<numberOfColumns; j++) {
                cells[i][j].setImageResource(android.R.color.transparent);
            }
        }
    }
}

