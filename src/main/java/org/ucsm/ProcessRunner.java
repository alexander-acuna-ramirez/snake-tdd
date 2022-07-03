package org.ucsm;

import org.ucsm.snake.Snake;

public class ProcessRunner implements Runnable{
    boolean state = true;
    Snake snake;
    public ProcessRunner(Snake _snake){
        this.snake = _snake;
    }
    @Override
    public void run() {
        while (state){
            try {
                this.snake.move();
                this.snake.repaint();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void stopGame() {
        this.state = false;
    }
}
