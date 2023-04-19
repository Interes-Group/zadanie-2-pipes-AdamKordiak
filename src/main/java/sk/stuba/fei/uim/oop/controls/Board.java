package sk.stuba.fei.uim.oop.controls;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Board extends JPanel {

    private Pipe[][] board;

    public Board(int dimension) {
        this.initializeBoard(dimension);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(51,153,255));
        //this.checkPlayable();
        this.setTypesInfo(dimension);

    }
    private void setTypesInfo(int dimension){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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
        this.board[1][0].setState(State.START);
        this.board[dimension-2][dimension-1].setState(State.END);
        this.board[1][1].setState(State.LTYPE);
        this.board[2][1].setState(State.LTYPE);
        this.board[2][2].setState(State.STRAIGHT);
    }

    public ArrayList<Pipe> checkPlayable() {
        ArrayList<Pipe> playable = new ArrayList<>();
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board.length; y++) {
                this.board[x][y].setCheckIsFree(true);

                    playable.add(this.board[x][y]);

            }
        }

        this.repaint();
        return playable;
    }

    public Pipe findPipe(Pipe pipe) {
        for (int x = 0; x < this.board.length; x++) {

            for (int y = 0; y < this.board.length; y++) {

                if (Objects.equals(this.board[x][y], pipe)) {
                     return pipe;
                }

            }
        }

        return null; //null = error
    }

    public int count(State state) {
        int count = 0;
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board.length; y++) {
                if (this.board[x][y].getState().equals(state)) {
                    count++;
                }
            }
        }
        return count;
    }



}
