package sk.stuba.fei.uim.oop.controls;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel {

    private Pipe[][] board;

    public Board(int dimension) {

        this.initializeBoard(dimension);
        this.setTypesInfo(dimension);
        this.setStartAndEnd(dimension);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(51,153,255));
        //this.checkPlayable();


    }
    public Pipe getOnePipe(int x,int y) {
        return board[x][y];
    }

    private void setTypesInfo(int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(this.board[i][j].getState().equals(State.STRAIGHT)){
                    this.board[i][j].setStraightPositionInfo();
                }
                if(this.board[i][j].getState().equals(State.LTYPE)){
                    this.board[i][j].setLTypePositionInfo();
                }
                if(this.board[i][j].getState().equals(State.START)){
                    this.board[i][j].setStartPositionInfo();
                }
                if(this.board[i][j].getState().equals(State.END)){
                    this.board[i][j].setEndPositionInfo();
                }
            }
        }
    }
    private void initializeBoard(int dimension) {

        this.board = new Pipe[dimension][dimension];
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[i][j] = new Pipe(i,j);
                this.add(this.board[i][j]);
            }
        }


/*
        this.board[dimension-2][dimension-1].setState(State.END);
        this.board[1][1].setState(State.LTYPE);
        this.board[2][1].setState(State.LTYPE);
        this.board[2][2].setState(State.STRAIGHT);*/

    }

    public void setStartAndEnd(int dimension) {
        boolean randomBoolean = getRandom().nextBoolean();
        int coOrdinate = getRandom().nextInt(dimension);


        if(randomBoolean){

            randomBoolean = getRandom().nextBoolean();
            if(randomBoolean) {

                this.board[coOrdinate][0].setPozition(Pozition.RIGHT,1);
                this.board[coOrdinate][0].setState(State.START);
                coOrdinate = getRandom().nextInt(dimension);
                this.board[coOrdinate][dimension-1].setPozition(Pozition.LEFT,0);
                this.board[coOrdinate][dimension-1].setState(State.END);

            }else {

                this.board[coOrdinate][dimension-1].setPozition(Pozition.LEFT,1);
                this.board[coOrdinate][dimension-1].setState(State.START);
                coOrdinate = getRandom().nextInt(dimension);
                this.board[coOrdinate][0].setPozition(Pozition.RIGHT,0);
                this.board[coOrdinate][0].setState(State.END);
            }
        }else {


            coOrdinate = getRandom().nextInt(dimension);
            randomBoolean = getRandom().nextBoolean();
            if(randomBoolean) {
                this.board[0][coOrdinate].setPozition(Pozition.DOWN,1);
                this.board[0][coOrdinate].setState(State.START);
                coOrdinate = getRandom().nextInt(dimension);
                this.board[dimension-1][coOrdinate].setPozition(Pozition.UP,0);
                this.board[dimension-1][coOrdinate].setState(State.END);


            }else {


                this.board[dimension-1][coOrdinate].setPozition(Pozition.UP,1);
                this.board[dimension-1][coOrdinate].setState(State.START);
                coOrdinate = getRandom().nextInt(dimension);
                this.board[0][coOrdinate].setPozition(Pozition.DOWN,0);
                this.board[0][coOrdinate].setState(State.END);

            }

        }

    }

public Random getRandom(){
    return new Random();

}



}
