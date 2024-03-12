package sample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 550;
    static final int UNIT_SIZE = 25;
    static final int DELAY = 175;
    final int x[] = new int[SCREEN_WIDTH/UNIT_SIZE];
    final int y[] = new int[SCREEN_HEIGHT/UNIT_SIZE];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if(running) {

            g.setColor(Color.red);
            g.setColor(Color.red);
            g.fillArc(appleX, appleY + 10, UNIT_SIZE, UNIT_SIZE - 5, 0, 180);
            
            g.setColor(new Color(249, 219, 195));
            g.fillArc(appleX + 1, appleY + 11, UNIT_SIZE - 2, UNIT_SIZE - 7, 0, 180);
             
            g.setColor(Color.red);
            g.fillOval(appleX + 10, appleY + 9, 8, 8);
            
            g.setColor(new Color(249, 219, 195));
            g.fillOval(appleX + 12, appleY + 8, 7, 7);
            
            g.setColor(new Color(242, 181, 92));
            g.drawArc(appleX + 5, appleY + 17, 10, 4, 0, 180);
            
            g.setColor(Color.black);
            g.fillOval(appleX + 18, appleY + 14, 3, 3);
            
            g.setColor(Color.red);
            g.fillArc(appleX, appleY + 18, 9, 5, 0, 180);
            g.fillArc(appleX + 12, appleY + 18, 7, 5, 0, 180);
            
            g.setColor(new Color(194, 134, 84));
            g.fillRect(appleX - 9, appleY + 19, 10, 1);
            g.fillRect(appleX + 23, appleY + 17, 1, 1);
            
            for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }

    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions() {
       
        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                running = false;
            }
        }
      
        if(x[0] < 0) {
            running = false;
        }
        
        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }
       
        if(y[0] < 0) {
            running = false;
        }

        if(y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {
    
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
     
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
    
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}