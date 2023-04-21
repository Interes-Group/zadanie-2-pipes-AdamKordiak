package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.bord.Board;
import sk.stuba.fei.uim.oop.bord.Pipe;
import sk.stuba.fei.uim.oop.bord.Pozition;
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

    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.updateNameLabel();
        this.currentBoard.setPaint(true);

    }
    private void checkPathCorrection() {
        int actualX ;
        int actualY ;
        int befourX = 0;
        int befourY = 0;

        for (int x = 0; x < this.currentBoardSize; x++) {
            for (int y = 0; y < this.currentBoardSize; y++) {

                if (this.currentBoard.getOnePipe(x, y).getState().equals((State.START))) {
                    befourX = this.currentBoard.getOnePipe(x, y).getsX();
                    befourY = this.currentBoard.getOnePipe(x, y).getsY();
                    break;
                }
            }
        }
        while(true) {
            while(true) {
                if (this.currentBoard.getOnePipe(befourX, befourY).getPozition(1).equals(Pozition.RIGHT)) {
                    actualX = befourX;
                    actualY = befourY + 1;
                    System.out.println(actualX + ", " + actualY);
                    if (this.currentBoard.getOnePipe(actualX, actualY).getPozition(0).equals(Pozition.LEFT)) {
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.STRAIGHT)) {

                            befourY = actualY;
                            actualY++;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.LTYPE)) {

                            befourY = actualY;
                            actualX++;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }

                    }
                    System.out.println(actualX + ", " + actualY);

                }
                if (this.currentBoard.getOnePipe(befourX, befourY).getPozition(1).equals(Pozition.LEFT)) {
                    actualX = befourX;
                    actualY = befourY - 1;
                    System.out.println(actualX + ", " + actualY);
                    if (this.currentBoard.getOnePipe(actualX, actualY).getPozition(0).equals(Pozition.RIGHT)) {
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.STRAIGHT)) {

                            befourY = actualY;
                            actualY--;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.LTYPE)) {

                            befourY = actualY;
                            actualX++;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }


                    }
                }
                if (this.currentBoard.getOnePipe(befourX, befourY).getPozition(1).equals(Pozition.UP)) {
                    actualX = befourX + 1;
                    actualY = befourY;
                    System.out.println(actualX + ", " + actualY);
                    if (this.currentBoard.getOnePipe(actualX, actualY).getPozition(0).equals(Pozition.DOWN)) {
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.STRAIGHT)) {
                            befourX = actualX;

                            actualY++;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.LTYPE)) {
                            befourX = actualX;

                            actualX--;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }

                    }
                }
                if (this.currentBoard.getOnePipe(befourX, befourY).getPozition(1).equals(Pozition.DOWN)) {
                    actualX = befourX ;
                    actualY = befourY-1;
                    System.out.println(actualX + ", " + actualY);
                    if (this.currentBoard.getOnePipe(actualX, actualY).getPozition(0).equals(Pozition.UP)) {
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.STRAIGHT)) {

                            befourY = actualY;
                            actualY--;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }
                        if (this.currentBoard.getOnePipe(actualX, actualY).getState().equals(State.LTYPE)) {

                            befourY = actualY;
                            actualX++;
                            System.out.println(actualX + ", " + actualY);
                            break;
                        }

                    }
                    System.out.println(actualX + ", " + actualY);

                }
            }
        }

    }


    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RESTART")) {

            this.gameRestart();


        } else if (e.getActionCommand().equals("CHECK")) {
            System.out.println("CHECK");
            //this.checkPathCorrection();
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

                this.counter++;
                ((Pipe) current).swapTypePositionInfo();
                System.out.println("  "+((Pipe) current).getPozition(0)+", "+ ((Pipe) current).getPozition(1));
                System.out.println(((Pipe) current).getsX()+", "+((Pipe) current).getsY());

                this.currentBoard.repaint();

        }else{
                System.out.println(((Pipe) current).getsX()+", "+((Pipe) current).getsY());
            }
        this.mainGame.repaint();

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
