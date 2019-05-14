package sample;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;


public class Principle extends Task{
    public Canvas canvas;
    GraphicsContext gc ;

    int width,height;
    int[][] board,next_step;
    private int[] neighberi={-1,0,1,-1,1,-1,0,1};
    private int[] neighberj={-1,-1,-1,0,0,1,1,1};
    private int counter,new_i,new_j,size=10;
    public boolean stop=false;

    Principle(Canvas a){
        canvas=a;
    }
    public Object call() throws InterruptedException {
            while (!stop) {
                gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                gc.fillRect(0, 0, width*size, height*size);
                gc.setFill((Color.BLACK));
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        counter = 0;
                        for (int k = 0; k < 8; k++) {
                            new_i = i + neighberi[k];
                            new_j = j + neighberj[k];
                            if (new_i < 0) {
                                new_i = width - 1;
                            }
                            if (new_i >= width) {
                                new_i = 0;
                            }
                            if (new_j < 0) {
                                new_j = height - 1;
                            }
                            if (new_j >= height) {
                                new_j = 0;
                            }

                            if (board[new_i][new_j] == 1) {
                                counter++;
                            }

                        }

                        if (counter == 3 && board[i][j] == 0) {
                            next_step[i][j] = 1;
                        }

                        if ((counter == 2 || counter == 3) && board[i][j] == 1) {
                            next_step[i][j] = 1;
                        }
                        if (!(counter == 2 || counter == 3) && board[i][j] == 1) {
                            next_step[i][j] = 0;
                        }
                    }

                }
                for (int i = 0; i < width; i++)
                    for (int j = 0; j < height; j++) {
                        board[i][j] = next_step[i][j];
                    }

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        if (board[i][j] == 1) {
                            gc.fillRect(i * size, j * size, size, size);
                        }
                    }

                }
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                    while(stop)
                    {
                        Thread.sleep(1000);

                    }

                canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>(){

                            @Override
                            public void handle(MouseEvent event) {
                                board[(int)(event.getX()/size)][(int)(event.getY()/size)]=1;
                                gc.fillRect((int)(event.getX()/size) * size, (int)(event.getY()/size) * size, size, size);
                            }
                        });

            }


                return null;
    }
    void fill_oscillator()
    {
           board[5][4]=1;
           board[5][5]=1;
           board[5][6]=1;
           board[9][4]=1;
           board[9][5]=1;
           board[9][6]=1;
           board[6][3]=1;
           board[7][3]=1;
           board[8][3]=1;
           board[6][7]=1;
           board[7][7]=1;
           board[8][7]=1;

    }
    void fill_glider()
    {
        board[5][4]=1;
        board[5][5]=1;
        board[5][6]=1;
        board[4][6]=1;
        board[3][5]=1;
    }
    void still_life()
    {
        board[10][4]=1;
        board[10][5]=1;
        board[10][6]=1;

        board[1][6]=1;
        board[2][5]=1;
        board[2][7]=1;
        board[3][5]=1;
        board[3][7]=1;
        board[4][6]=1;


    }
    void random()
    {
        Random rand = new Random();
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
                board[i][j] = rand.nextInt(2);
    }


}
