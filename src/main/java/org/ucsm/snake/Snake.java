package org.ucsm.snake;

import org.ucsm.ProcessRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class Snake extends JPanel {
    private List<int []> body = new ArrayList<>();

    public int[] getFood() {
        return food;
    }

    int [] food = new int[2];
    Thread thread;
    ProcessRunner pr;
    Color snakeColor = Color.black;
    Color foodColor = Color.RED;
    int tammax,can,tam,res;
    String dir = "right";
    String nextDir = "right";
    public Snake(int tammax, int can){
        this.tammax = tammax;
        this.can = can;
        this.tam = tammax / can;
        this.res = tammax % can;
        int [] a = {can/2 - 1, can/2-1};
        int [] b = {can/2, can/2-1};
        System.out.println(a[0] + " - " + a[1]);
        body.add(a);
        body.add(b);
        setOpaque(true);
        this.createFood();

        pr = new ProcessRunner(this);
        thread = new Thread(pr);
        thread.start();
    }

    public void setFood(int[] food) {
        this.food = food;
    }

    public void paint(Graphics g){
        super.paint(g);

        g.setColor(this.snakeColor);
        for (int i = 0; i < this.body.size(); i++){
            g.fillRect(res/2 + this.body.get(i)[0] * tam, res/2 + this.body.get(i)[1] * tam,tam - 1, tam -1);
        }
        g.setColor(this.foodColor);
        g.fillRect(res/2 + this.food[0] * tam, res/2 + this.food[1] * tam,tam - 1, tam -1);

    }
    public List<int[]> getBody() {
        return body;
    }

    public int getSizeSnake(){
        return this.body.size();
    }
    public boolean move(){
        this.syncDirection();
        int [] last = this.body.get(this.body.size() - 1);
        int addX = 0;
        int addY = 0;
        switch (dir){
            case "right":
                addX = 1;
                break;
            case "left":
                addX = -1;
                break;
            case "up":
                addY = -1;
                break;
            case "down":
                addY = 1;
                break;
        }
        int[] toAdd = {Math.floorMod(last[0] + addX,can), Math.floorMod(last[1] + addY,can)};
        boolean exits = false;
        for (int i = 0; i<this.body.size(); i++){
            if(toAdd[0] == this.body.get(i)[0] && toAdd[1] == this.body.get(i)[1]){
                exits = true;
                break;
            }
        }
        if(exits){
            JOptionPane.showMessageDialog(this, "You have lost");
            return false;
        }else{
            if(toAdd[0] == this.food[0] && toAdd[1] == this.food[1]){
                this.body.add(toAdd);
                this.createFood();
            }else{
                this.body.add(toAdd);
                this.body.remove(0);
            }
            return true;
        }
    }
    public void createFood(){
            boolean exits = false;
            int a = (int) (Math.random() * can);
            int b = (int) (Math.random() * can);
            for(int[] part:this.body){
                if(part[0] == a && part[1] == b){
                    exits = true;
                    this.createFood();
                    break;
                }
            }
            if(!exits){
                this.food[0] = a;
                this.food[1] = b;
            }

    }

    public String getDir() {
        return dir;
    }

    public String getNextDir() {
        return nextDir;
    }

    public void changeDirection(String _dir){

        if((this.dir.equals("right") || this.dir.equals("left")) && (_dir.equals("up") || _dir.equals("down"))){
            this.nextDir = _dir;
        }
        if((this.dir.equals("up") || this.dir.equals("down")) && (_dir.equals("right") || _dir.equals("left"))){
            this.nextDir = _dir;
        }
    }
    public void syncDirection(){
        this.dir = this.nextDir;
    }


    public void detectKeyboard(int keycode){
        if(keycode == KeyEvent.VK_LEFT){
            this.changeDirection("left");
        }
        if(keycode == KeyEvent.VK_RIGHT){
            this.changeDirection("right");
        }
        if(keycode == KeyEvent.VK_UP){
            this.changeDirection("up");
        }
        if(keycode == KeyEvent.VK_DOWN){
            this.changeDirection("down");
        }
    }

}
