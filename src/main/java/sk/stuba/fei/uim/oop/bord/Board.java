package sk.stuba.fei.uim.oop.bord;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Board extends JPanel {

    private Pipe[][] board;
    @Getter @Setter
    private boolean paint;
    private int[][] dps;
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] path;


    public Board(int dimension) {

        this.initializeBoard(dimension);

        this.setPipes(dimension);

        this.setTypesInfo(dimension);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(51,153,255));
        this.paint = false;

    }

    public void paintComponent(Graphics g) {
        if(this.paint) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int random = getRandom().nextInt(10);
            double angle = Math.toRadians(random * 90);
            g2d.rotate(angle, this.getWidth() / 2.0, this.getHeight() / 2.0);
            this.paint = false;
        }
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
                this.board[i][j] = new Pipe();
                this.add(this.board[i][j]);

            }
        }



    }
    public void setPipes(int dimension) {

        int StartPoz = getRandom().nextInt(dimension);
        int EndPoz = getRandom().nextInt(dimension);

        this.genrateDPS(dimension, StartPoz, EndPoz);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boolean random = getRandom().nextBoolean();

                if(this.path[i][j] == 1){
                    if(random) {
                        this.board[i][j].setState(State.STRAIGHT);
                    }else{
                        this.board[i][j].setState(State.LTYPE);

                    }

                }

            }
        }
            this.board[StartPoz][0].setPozition(Pozition.RIGHT, 1);
            this.board[StartPoz][0].setState(State.START);

            this.board[EndPoz][dimension - 1].setPozition(Pozition.LEFT, 0);
            this.board[EndPoz][dimension - 1].setState(State.END);


    }

public Random getRandom(){
    return new Random();

}

    private void genrateDPS(int dimension, int StartPoz, int EndPoz){
        this.dps = new int[dimension][dimension];
        generateMaze(0, 0,dimension);

         path = findShortestPath(dimension,StartPoz,EndPoz);

        if(path ==null){
            genrateDPS(dimension,StartPoz,EndPoz);
        }

    }
    private void generateMaze(int x, int y,int dimension) {
        dps[x][y] = 1;
        List<int[]> dirs = Arrays.asList(directions);
        Collections.shuffle(dirs);

        for (int[] dir : dirs) {
            int dx = dir[0];
            int dy = dir[1];
            int nx = x + dx;
            int ny = y + dy;

            if (nx >= 0 && nx < dimension && ny >= 0 && ny < dimension && dps[nx][ny] == 0) {
                if (dx == 1) {
                    dps[x][y] |= 2;}
                else if (dx == -1){ dps[nx][ny] |= 2;}
                else if (dy == 1){ dps[x][y] |= 1;}
                else if (dy == -1) {
                    dps[nx][ny] |= 1;}
                generateMaze(nx, ny,dimension);
            }
        }

    }

    private int[][] findShortestPath(int dimension, int StartPoz,int EndPoz) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int[][] path = new int[dimension][dimension];
        for (int[] row : path) Arrays.fill(row, -1);
        path[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            if (x == EndPoz && y == dimension - 1) {
                break;
            }
            for (int[] dir : directions) {
                int dx = dir[0];
                int dy = dir[1];
                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < dimension && ny >= 0 && ny < dimension && dps[nx][ny] == 1 && path[nx][ny] == -1) {
                    queue.offer(new int[]{nx, ny});
                    path[nx][ny] = path[x][y] + 1;
                }
            }
        }
        int[][] shortestPath = new int[dimension][dimension];
        for (int[] row : shortestPath) Arrays.fill(row, 0);
        int x = EndPoz;
        int y = dimension - 1;

        int BreakWhile  = 0;
        while (x != StartPoz || y != 0) {
            BreakWhile++;
            shortestPath[x][y] = 1;
            for (int[] dir : directions) {
                int dx = dir[0];
                int dy = dir[1];

                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < dimension && ny >= 0 && ny < dimension && path[nx][ny] == path[x][y] - 1) {
                    x = nx;
                    y = ny;
                    break;
                }
            }
            if(BreakWhile > 10000 ){
                return null;
            }
        }
        shortestPath[0][0] = 0;
        return shortestPath;
    }





}
