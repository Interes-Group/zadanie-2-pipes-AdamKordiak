package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class GameLogic extends UniversalAdapter {

    public static final int INITIAL_BOARD_SIZE = 4;
    private JFrame mainGame;
    private Board currentBoard;
    @Getter
    private JLabel label;
    @Getter
    private JLabel boardSizeLabel;
    @Getter
    private int counter;
    private int currentBoardSize;



    public GameLogic(JFrame mainGame) {
        this.mainGame = mainGame;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.label = new JLabel();
        this.counter = 0;
        this.boardSizeLabel = new JLabel();
        this.updateNameLabel();
        this.updateBoardSizeLabel();


    }

    private void updateNameLabel() {
        this.label.setText("WINS: " + this.counter);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }


    private void updateBoardSizeLabel() {
        this.boardSizeLabel.setText("CURRENT BOARD SIZE: " + this.currentBoardSize);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateNameLabel();
    }

    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameRestart();
        this.mainGame.revalidate();
        this.mainGame.repaint();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Pipe)) {
            return;
        }
        ((Pipe) current).setHighlight(true);

        this.currentBoard.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Pipe)) {
            return;
        }
            if(!((Pipe) current).getState().equals(State.FREE) && !((Pipe) current).getState().equals(State.START) && !((Pipe) current).getState().equals(State.END) ) {

                this.counter++;
                this.currentBoard.findPipe((Pipe) current).swapTypePositionInfo();
                System.out.println("  "+this.currentBoard.findPipe((Pipe) current).getPozitionInfo(2));
                System.out.print(""+this.currentBoard.findPipe((Pipe) current).getPozitionInfo(0));
                System.out.println("   "+this.currentBoard.findPipe((Pipe) current).getPozitionInfo(1));
                System.out.println("  "+this.currentBoard.findPipe((Pipe) current).getPozitionInfo(3));
                this.currentBoard.repaint();

        }

    }


    private boolean checkWin() {
        ArrayList<Pipe> playableBlack = this.currentBoard.checkPlayable();
        ArrayList<Pipe> playableWhite = this.currentBoard.checkPlayable();
        if (playableBlack.size() == 0 && playableWhite.size() == 0) {
            int black = this.currentBoard.count(State.STRAIGHT);
            int white = this.currentBoard.count(State.LTYPE);
            int counter = this.counter++;
            if (black > white) {
                this.label.setText(" WINS " + counter);
                this.gameRestart();
            } else if (black < white) {
                this.label.setText(" WINS " + counter);
                this.gameRestart();
            }  else  {
                this.label.setText("Its TIE!");
                this.gameRestart();
            }
        }

        return playableBlack.size() == 0 && playableWhite.size() == 0;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.updateBoardSizeLabel();
        this.gameRestart();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();
        }
    }

}
