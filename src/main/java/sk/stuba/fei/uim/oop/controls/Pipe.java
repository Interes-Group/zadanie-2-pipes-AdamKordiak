package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {

    @Getter @Setter
    private State state;

    private final Pozition[] pozition;
    @Setter
    private boolean highlight;
    @Getter @Setter
    private boolean checkIsFree;
    private final int x;

    private final int y;

    public Pipe(int x,int y) {
        this.x = x;
        this.y = y;
        this.state = State.FREE;
        this.setBorder(BorderFactory.createLineBorder(new Color(51,153,255)));
        this.setBackground(new Color(51,204,255));
        this.pozition = new Pozition[]{null,null};

    }
    public int getsX() {return x;}
    public int getsY() {return y;}
    public Pozition getPozition(int x) {return pozition[x];}
    public void setPozition(Pozition pozition,int x) {this.pozition[x] = pozition;}

    public void setStraightPositionInfo(){
        this.setPozition(Pozition.LEFT,0);
        this.setPozition(Pozition.RIGHT,1);
    }
   public void setLTypePositionInfo(){
       this.setPozition(Pozition.LEFT,0);
       this.setPozition(Pozition.DOWN,1);
   }
    public void setStartPositionInfo(){
        this.setPozition(Pozition.RIGHT,1);
    }
    public void setEndPositionInfo(){
        this.setPozition(Pozition.LEFT,0);
    }

    public void swapTypePositionInfo() {
        if (this.state.equals(State.STRAIGHT)) {
            if (this.getPozition(0).equals(Pozition.LEFT)) {this.setPozition(Pozition.UP, 0);this.setPozition(Pozition.DOWN, 1);return;}
            if (this.getPozition(0).equals(Pozition.UP)) {this.setPozition(Pozition.RIGHT, 0);this.setPozition(Pozition.LEFT, 1);return;}
            if (this.getPozition(0).equals(Pozition.RIGHT)) {this.setPozition(Pozition.DOWN, 0);this.setPozition(Pozition.UP, 1);return;}
            if (this.getPozition(0).equals(Pozition.DOWN)) {this.setPozition(Pozition.LEFT, 0);this.setPozition(Pozition.RIGHT, 1);return;}
        }
        if (this.state.equals(State.LTYPE)) {
            if (this.getPozition(0).equals(Pozition.LEFT) && this.getPozition(1).equals(Pozition.DOWN)) {this.setPozition(Pozition.UP, 0);this.setPozition(Pozition.LEFT, 1);return;}
            if (this.getPozition(0).equals(Pozition.UP) && this.getPozition(1).equals(Pozition.LEFT)) {this.setPozition(Pozition.RIGHT, 0);this.setPozition(Pozition.UP, 1);return;}
            if (this.getPozition(0).equals(Pozition.RIGHT) && this.getPozition(1).equals(Pozition.UP)) {this.setPozition(Pozition.DOWN, 0);this.setPozition(Pozition.RIGHT, 1);return;}
            if (this.getPozition(0).equals(Pozition.DOWN) && this.getPozition(1).equals(Pozition.RIGHT)) {this.setPozition(Pozition.LEFT, 0);this.setPozition(Pozition.DOWN, 1);}
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        double angle = Math.toRadians(0);


                if (this.highlight) {
                    g.setColor(new Color(51,184,255));
                    g.fillRect(0,0,10000,100000);
                    this.highlight = false;

                }
        if (this.state.equals(State.START)) {

            if(this.getPozition(1).equals(Pozition.DOWN)){angle = Math.toRadians(90);}
            if(this.getPozition(1).equals(Pozition.LEFT)){angle = Math.toRadians(180);}
            if(this.getPozition(1).equals(Pozition.UP)){angle = Math.toRadians(270);}


            g2d.rotate(angle,this.getWidth()/2.0,this.getHeight()/2.0);

            g2d.setColor(new Color(153, 153, 153));
            g2d.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() * 0.38),
                    (int) (this.getWidth() * 0.9), (int) (this.getHeight() * 0.25));

            g2d.setColor(new Color(102, 102, 102));
            g2d.fillRect((int) (0 + this.getWidth() * 0.79), (int) (0 + this.getHeight() * 0.261),
                    (int) (this.getWidth() * 0.5), (int) (this.getHeight() * 0.5));


            g2d.setColor(new Color(102,255,102));
            g2d.fillRect((int) (0 + this.getWidth() * 0.2), (int) (0 + this.getHeight() * 0.46),
                    (int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.1));
        }
        if (this.state.equals(State.STRAIGHT)) {

            if(this.getPozition(0).equals(Pozition.UP )){angle = Math.toRadians(90);}
            if(this.getPozition(0).equals(Pozition.RIGHT )){angle = Math.toRadians(180);}
            if( this.getPozition(0).equals(Pozition.DOWN)){angle = Math.toRadians(270);}

            g2d.rotate(angle,this.getWidth()/2.0,this.getHeight()/2.0);

            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() * 0.39),
                    (int) (this.getWidth() * 0.9), (int) (this.getHeight() * 0.25));

            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() * 0.261),
                    (int) (this.getWidth() * 0.20), (int) (this.getHeight() * 0.5));

            g.fillRect((int) (0 + this.getWidth() * 0.80), (int) (0 + this.getHeight() * 0.261),
                    (int) (this.getWidth() * 0.5), (int) (this.getHeight() * 0.5));


        }
        if (this.state.equals(State.LTYPE)) {

            if(this.getPozition(0).equals(Pozition.UP)){angle = Math.toRadians(90);}
            if(this.getPozition(0).equals(Pozition.RIGHT)){angle =Math.toRadians(180);}
            if(this.getPozition(0).equals(Pozition.DOWN)){angle = Math.toRadians(270);}


            g2d.rotate(angle,this.getWidth()/2.0,this.getHeight()/2.0);

            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() *  0.381),
                    (int) (this.getWidth() * 0.51), (int) (this.getHeight() * 0.25));

            g.fillRect((int) (0 + this.getWidth() * 0.3911), (int) (0 + this.getHeight() * 0.38),
                    (int) (this.getWidth() * 0.23), (int) (this.getHeight() * 0.9));

            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() * 0.261),
                    (int) (this.getWidth() * 0.21), (int) (this.getHeight() * 0.5));


            g.fillRect((int) (0 + this.getWidth() * 0.2608), (int) (0 + this.getHeight() * 0.8),
                    (int) (this.getWidth() * 0.5), (int) (this.getHeight() * 1.0));

        }
        if (this.state.equals(State.END)) {

            if(this.getPozition(0).equals(Pozition.UP)){angle = Math.toRadians(90);}
            if(this.getPozition(0).equals(Pozition.RIGHT)){angle = Math.toRadians(180);}
            if(this.getPozition(0).equals(Pozition.DOWN)){angle = Math.toRadians(270);}

            g2d.rotate(angle,this.getWidth()/2.0,this.getHeight()/2.0);

            g.setColor(new Color(153, 153, 153));
            g.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() * 0.38),
                    (int) (this.getWidth() * 0.999), (int) (this.getHeight() * 0.25));

            g.setColor(new Color(102, 102, 102));
            g.fillRect((int) (0 + this.getWidth() * 0.001), (int) (0 + this.getHeight() * 0.26),
                    (int) (this.getWidth() * 0.21), (int) (this.getHeight() * 0.5));


            g.setColor(new Color(255,102,102));
            g.fillRect((int) (0 + this.getWidth() * 0.59), (int) (0 + this.getHeight() * 0.46),
                    (int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.1));
        }

    }

}
