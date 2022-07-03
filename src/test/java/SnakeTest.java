import org.junit.jupiter.api.Test;
import org.ucsm.snake.Snake;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SnakeTest {

    @Test
    public void an_snake_can_be_created(){
        Snake snake = new Snake(800,30);
        assertEquals(snake.getBody().size(), 2);
    }


    @Test
    public void snake_can_generate_food(){
        Snake snake = new Snake(800,30); //2
        snake.createFood();

        List<int[]> snakeBody = snake.getBody();
        //Getting the initial body of our snake
        int[] initial = new int[2];
        //Our snake starts with a length of 2
        initial[0] = snakeBody.get(0)[0];
        initial[1] = snakeBody.get(0)[1];

        assertNotEquals(initial, snake.getFood());;
    }
    @Test
    public void snake_can_eat_and_grow(){

        Snake snake = new Snake(800,30);//2
        snake.setFood(new int[]{16, 14});
        snake.move();
        /* snake has grown 1 space*/
        assertEquals(snake.getBody().size(), 3);
    }
    @Test
    public void snake_can_dead() {
        Snake snake = new Snake(800, 30);//2
        snake.setFood(new int[]{16, 14});
        snake.move();
        snake.setFood(new int[]{17, 14});
        snake.move();

        //The snake now has the minimum length required to dead.
        assertEquals(snake.getBody().size(), 4);

        snake.changeDirection("up");
        snake.move();
        snake.changeDirection("left");
        snake.move();
        snake.changeDirection("down");
        boolean isStillAlive = snake.move();
        assertEquals(isStillAlive, false);
    }
    @Test
    public void snake_cannot_go_against_its_direction() {

        Snake snake = new Snake(800, 30);//2
        /*
            The default direction of the snake is right
            So, if it is going to the right, this can only go up or down
            The other directions cannot be applied because the snake is already going to
            the right and if we try to go to the left the head of the snake would hit into
            its own body.
         */
        String initialDir = snake.getDir();//it is right
        snake.changeDirection("left");
        snake.move();
        String currentDir = snake.getDir();
        assertEquals(initialDir,currentDir);

    }
    @Test
    public void snake_can_change_its_direction_using_keycodes() {


        Snake snake = new Snake(800, 30);//2
        snake.setFood(new int[]{16, 14});
        snake.move();
        snake.setFood(new int[]{17, 14});
        snake.move();

        //The snake now has the minimum length required to dead.
        assertEquals(snake.getBody().size(), 4);

        snake.detectKeyboard(38);
        snake.move();
        snake.detectKeyboard(37);
        snake.move();
        snake.detectKeyboard(40);
        /*If our snake is dead it means that it has been able to change its direction
        * using the keycodes provided by the keyboard
        * */
        boolean isStillAlive = snake.move();
        assertEquals(isStillAlive, false);

    }

}
