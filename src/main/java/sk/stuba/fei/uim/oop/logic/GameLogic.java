package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.bord.Board;
import sk.stuba.fei.uim.oop.bord.Pipe;
import sk.stuba.fei.uim.oop.bord.State;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class GameLogic extends UniversalAdapter {

    public static final int INITIAL_BOARD_SIZE = 8;
    private final JFrame mainGame;
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

    private void restart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateNameLabel();
        this.currentBoard.setPaint(true);

    }


    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RESTART")) {

            this.restart();


        } else if (e.getActionCommand().equals("CHECK")) {
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Pipe)) {
            return;
        }
        ((Pipe) current).setHighlight(true);
            this.mainGame.repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component current = this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Pipe)) {
            return;
        }
            if(!((Pipe) current).getState().equals(State.FREE) && !((Pipe) current).getState().equals(State.START) && !((Pipe) current).getState().equals(State.END) ) {
                ((Pipe) current).swapTypePositionInfo();
                this.currentBoard.repaint();

        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {

        this.currentBoardSize = ((JSlider) e.getSource()).getValue();
        this.updateBoardSizeLabel();
        this.restart();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println(e);
        switch (e.getKeyCode()) {

            case KeyEvent.VK_R:
                this.restart();
                break;

            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();
        }
    }

}
