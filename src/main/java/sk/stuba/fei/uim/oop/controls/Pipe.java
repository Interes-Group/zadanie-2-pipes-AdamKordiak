package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {

    @Getter @Setter
    private State state;
    @Setter
    private boolean highlight;
    @Getter @Setter
    private boolean checkIsFree;
    @Setter
    private int[] pozitionInfo;

    private final int x;
    private final int y;

    public int getPozitionInfo(int i) {
        return pozitionInfo[i];
    }





    public Pipe(int x,int y) {
        this.x = x;
        this.y = y;
        this.state = State.FREE;
        this.setBorder(BorderFactory.createLineBorder(new Color(51,153,255)));
        this.setBackground(new Color(51,204,255));
        this.pozitionInfo = new int[]{1, 2, 0, 0}; // left, right, up , down

    }
   public void setLTypePositionInfo(){
            this.pozitionInfo = new int[]{1, 0, 0 , 2};
   }
    public void setStartPositionInfo(){
        this.pozitionInfo = new int[]{0, 1, 0 , 0};
    }
    public void setEndPositionInfo(){
        this.pozitionInfo = new int[]{2, 0, 0 , 0};
    }
    public void swapTypePositionInfo(){
        int[] actualPositions = new int[pozitionInfo.length];
        System.arraycopy(pozitionInfo, 0, actualPositions, 0, pozitionInfo.length);

        this.pozitionInfo[0] = actualPositions[3];
        this.pozitionInfo[1] = actualPositions[2];
        this.pozitionInfo[2] = actualPositions[0];
        this.pozitionInfo[3] = actualPositions[1];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        double[]mainTube = new double[]{0,0,0,0} ;
        double[]firstHoop = new double[]{0,0,0,0} ;
        double[]secondHoop = new double[]{0,0,0,0};


                if (this.highlight) {
                    g.setColor(new Color(51,184,255));
                    g.fillRect(0,0,10000,100000);
                    this.highlight = false;

                }

        if (this.state.equals(State.STRAIGHT)) {
            if(this.getPozitionInfo(0) == 1 || this.getPozitionInfo(0) == 2){
                mainTube[0] =  0.001; mainTube[1] =  0.38; mainTube[2] =  0.9; mainTube[3] =  0.25;
                firstHoop[0] = 0.001; firstHoop[1] = 0.26; firstHoop[2] = 0.2; firstHoop[3] =0.5 ;
                secondHoop[0] = 0.79; secondHoop[1] = 0.26; secondHoop[2] = 0.5; secondHoop[3] = 0.5;
            }
            if(this.getPozitionInfo(2) == 1 || this.getPozitionInfo(2) == 2){
                mainTube[0] =  0.39;mainTube[1] =  0.001;mainTube[2] =  0.23;mainTube[3] =  0.9;
                firstHoop[0] = 0.28; firstHoop[1] = 0.001; firstHoop[2] = 0.45; firstHoop[3] =0.23 ;
                secondHoop[0] = 0.28; secondHoop[1] = 0.77; secondHoop[2] = 0.45; secondHoop[3] = 1.0;
            }


            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * mainTube[0]), (int) (0 + this.getHeight() * mainTube[1]),
                    (int) (this.getWidth() * mainTube[2]), (int) (this.getHeight() * mainTube[3]));

            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * firstHoop[0]), (int) (0 + this.getHeight() * firstHoop[1]),
                    (int) (this.getWidth() * firstHoop[2]), (int) (this.getHeight() * firstHoop[3]));

            g.fillRect((int) (0 + this.getWidth() * secondHoop[0]), (int) (0 + this.getHeight() * secondHoop[1]),
                    (int) (this.getWidth() * secondHoop[2]), (int) (this.getHeight() * secondHoop[3]));


        } if (this.state.equals(State.LTYPE)) {
            double[]mainTube2 = new double[]{0,0,0,0} ;
            if(this.getPozitionInfo(0) == 1 && this.getPozitionInfo(3) == 2){
                mainTube[0] =  0.001; mainTube[1] =  0.38; mainTube[2] =  0.62; mainTube[3] =  0.25;
                mainTube2[0] =  0.393;mainTube2[1] =  0.41;mainTube2[2] =  0.23;mainTube2[3] =  0.9;
                firstHoop[0] = 0.001; firstHoop[1] = 0.26; firstHoop[2] = 0.2; firstHoop[3] =0.5 ;
                secondHoop[0] = 0.28; secondHoop[1] = 0.77; secondHoop[2] = 0.45; secondHoop[3] = 1.0;
            }
            if(this.getPozitionInfo(2) == 1 && this.getPozitionInfo(0) == 2){
                mainTube[0] =  0.001; mainTube[1] =  0.38; mainTube[2] =  0.62; mainTube[3] =  0.25;
                mainTube2[0] =  0.393;mainTube2[1] =  0.001;mainTube2[2] =  0.23;mainTube2[3] =  0.42;
                firstHoop[0] = 0.28; firstHoop[1] = 0.001; firstHoop[2] = 0.45; firstHoop[3] =0.23 ;
                secondHoop[0] = 0.001; secondHoop[1] = 0.26; secondHoop[2] = 0.2; secondHoop[3] =0.5 ;

            }
            if(this.getPozitionInfo(1) == 1 && this.getPozitionInfo(2) == 2){
                mainTube[0] =  0.393; mainTube[1] =  0.38; mainTube[2] =  0.9; mainTube[3] =  0.25;
                mainTube2[0] =  0.393;mainTube2[1] =  0.001;mainTube2[2] =  0.23;mainTube2[3] =  0.42;
                firstHoop[0] = 0.79; firstHoop[1] = 0.26; firstHoop[2] = 0.5; firstHoop[3] = 0.5;
                secondHoop[0] = 0.28; secondHoop[1] = 0.001; secondHoop[2] = 0.45; secondHoop[3] =0.23 ;

            }
            if(this.getPozitionInfo(1) == 2 && this.getPozitionInfo(3) == 1){
                mainTube[0] =  0.393; mainTube[1] =  0.38; mainTube[2] =  0.9; mainTube[3] =  0.25;
                mainTube2[0] =  0.393;mainTube2[1] =  0.41;mainTube2[2] =  0.23;mainTube2[3] =  0.9;
                secondHoop[0] = 0.79; secondHoop[1] = 0.26; secondHoop[2] = 0.5; secondHoop[3] = 0.5;
                firstHoop[0] = 0.28; firstHoop[1] = 0.77; firstHoop[2] = 0.45; firstHoop[3] = 1.0;

            }

            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * mainTube[0]), (int) (0 + this.getHeight() * mainTube[1]),
                    (int) (this.getWidth() * mainTube[2]), (int) (this.getHeight() * mainTube[3]));

            g.fillRect((int) (0 + this.getWidth() * mainTube2[0]), (int) (0 + this.getHeight() * mainTube2[1]),
                    (int) (this.getWidth() * mainTube2[2]), (int) (this.getHeight() * mainTube2[3]));


            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * firstHoop[0]), (int) (0 + this.getHeight() * firstHoop[1]),
                    (int) (this.getWidth() * firstHoop[2]), (int) (this.getHeight() * firstHoop[3]));


            g.fillRect((int) (0 + this.getWidth() * secondHoop[0]), (int) (0 + this.getHeight() * secondHoop[1]),
                    (int) (this.getWidth() * secondHoop[2]), (int) (this.getHeight() * secondHoop[3]));
        }

        if (this.state.equals(State.START)) {

            mainTube[0] =  0.001; mainTube[1] =  0.38; mainTube[2] =  0.9; mainTube[3] =  0.25;
            secondHoop[0] = 0.79; secondHoop[1] = 0.26; secondHoop[2] = 0.5; secondHoop[3] = 0.5;

            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * mainTube[0]), (int) (0 + this.getHeight() * mainTube[1]),
                    (int) (this.getWidth() * mainTube[2]), (int) (this.getHeight() * mainTube[3]));

            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * secondHoop[0]), (int) (0 + this.getHeight() * secondHoop[1]),
                    (int) (this.getWidth() * secondHoop[2]), (int) (this.getHeight() * secondHoop[3]));


            g.setColor(new Color(102,255,102));
            g.fillRect((int) (0 + this.getWidth() * 0.2), (int) (0 + this.getHeight() * 0.45),
                    (int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.1));
        }
        if (this.state.equals(State.END)) {

            mainTube[0] =  0.001; mainTube[1] =  0.38; mainTube[2] =  0.999; mainTube[3] =  0.25;
            firstHoop[0] = 0.001; firstHoop[1] = 0.26; firstHoop[2] = 0.2; firstHoop[3] =0.5 ;

            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * mainTube[0]), (int) (0 + this.getHeight() * mainTube[1]),
                    (int) (this.getWidth() * mainTube[2]), (int) (this.getHeight() * mainTube[3]));

            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * firstHoop[0]), (int) (0 + this.getHeight() * firstHoop[1]),
                    (int) (this.getWidth() * firstHoop[2]), (int) (this.getHeight() * firstHoop[3]));


            g.setColor(new Color(255,102,102));
            g.fillRect((int) (0 + this.getWidth() * 0.59), (int) (0 + this.getHeight() * 0.45),
                    (int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.1));
        }

    }

}
