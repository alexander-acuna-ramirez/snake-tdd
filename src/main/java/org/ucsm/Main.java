package org.ucsm;
import org.ucsm.snake.Snake;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main{

    public static void main( String args[] )
    {
        Snake snake = new Snake(800,30);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,900);
        frame.setBounds(10,10,810,850);
        frame.add(snake);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { return; }
            @Override
            public void keyPressed(KeyEvent e) {
                snake.detectKeyboard(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) { return; }
        });
    }
}