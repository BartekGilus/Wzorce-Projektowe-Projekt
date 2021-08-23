import Dices_FactoryMethod.DiceType;
import GameBoard_Strategy.CreateGameBoard;
import GameBoard_Strategy.GameModes;
import Player_State_Facade.AbstractPlayer;
import Player_State_Facade.Enemy;
import Player_State_Facade.Player;


import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private WindowFrame window;
    private boolean isRunning = false;
    private Thread thread;
    private MouseManager mouseManager;

    private GameModes mode = GameModes.PLAYERS_4;
    private DiceType dicetype = DiceType.DICE_6;

    private Player player;
    private AbstractPlayer enemy;
    private AbstractPlayer enemy2;
    private AbstractPlayer enemy3;

    private int[] mousePos = new int[4];
    private int rounds;

    private BufferStrategy bs;
    private Graphics g;

    public void setDicetype(DiceType dice){
        this.dicetype = dice;
    }
    public void setMode(GameModes mode){
        this.mode = mode;
    }
    public MouseManager getMouseManager(){
        return mouseManager;
    }

    private void init(){
        window = WindowFrame.getInstance();
        window.setGame(this);
        initPlayers();
        mouseManager = new MouseManager();
        window.getFrame().addMouseListener(mouseManager);
        window.getFrame().addMouseMotionListener(mouseManager);
        window.getCanvas().addMouseListener(mouseManager);
        window.getCanvas().addMouseMotionListener(mouseManager);

        window.setGame(this);
    }

    public void initPlayers(){
        rounds = 0;
        if(mode == GameModes.PLAYERS_2){
            player = new Player("Gracz");
            enemy = new Enemy("Przeciwnik 1");
            mousePos[0] = 540; mousePos[1] = 740; mousePos[2] = 620; mousePos[3] = 670;
        }
        else if(mode == GameModes.PLAYERS_4){
            player = new Player("Gracz");
            enemy = new Enemy("Przeciwnik 1");
            enemy2 = new Enemy("Przeciwnik 2");
            enemy3 = new Enemy("Przeciwnik 3");
            mousePos[0] = 415; mousePos[1] = 615; mousePos[2] = 620; mousePos[3] = 670;
        }
    }

    private void update(){
        if(mode == GameModes.PLAYERS_2) {
            if (mouseManager.getMouseX() >= mousePos[0] && mouseManager.getMouseX() <= mousePos[1] && mouseManager.getMouseY() >= mousePos[2] && mouseManager.getMouseY() <= mousePos[3] && player.getTurn()) {
                if (mouseManager.isLeftPressed() && !mouseManager.isLeftHold()) {
                    player.throwDices(dicetype);
                    player.MovedState();
                    mouseManager.setLeftHold(true);
                }
            }
            if (!player.getTurn()) {
                enemy.throwDices(dicetype);
                player.Moving();
                rounds++;
            }
        }
        else if(mode == GameModes.PLAYERS_4){
            if (mouseManager.getMouseX() >= mousePos[0] && mouseManager.getMouseX() <= mousePos[1] && mouseManager.getMouseY() >= mousePos[2] && mouseManager.getMouseY() <= mousePos[3] && player.getTurn()) {
                if (mouseManager.isLeftPressed() && !mouseManager.isLeftHold()) {
                    player.throwDices(dicetype);
                    player.MovedState();
                    mouseManager.setLeftHold(true);
                }
            }
            if (!player.getTurn()) {
                enemy.throwDices(dicetype);
                enemy2.throwDices(dicetype);
                enemy3.throwDices(dicetype);
                player.Moving();
                rounds ++;
            }
        }
    }

    public void render(){
        bs = window.getCanvas().getBufferStrategy();
        if(bs == null) {
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0, window.getWidth(), window.getHeight());
        if(rounds < 10) {
            new CreateGameBoard(mode, g, bs, window.getWidth(), window.getHeight()).render();
            renderPlayer(g, bs);
            renderEnemys(g, bs);
        }
        else
            renderEnding(g, bs);
        bs.show();
        g.dispose();
    }

    public void renderPlayer(Graphics g, BufferStrategy bs){
        if(mode == GameModes.PLAYERS_2){
            player.drawButton(g, bs, 1280, 720);
            player.drawDices(g, bs, window.getWidth() / 2, window.getHeight() - window.getHeight() / 3);
            player.drawScore(g, bs, 20, window.getHeight() - 100);
        }
        else if(mode == GameModes.PLAYERS_4){
            player.drawButton(g, bs, window.getWidth() - 250, window.getHeight());
            player.drawDices(g, bs, window.getWidth() / 4, window.getHeight() - window.getHeight() / 3);
            player.drawScore(g, bs, 20, window.getHeight()- 40);
        }
    }

    public void renderEnemys(Graphics g, BufferStrategy bs){
        if(mode == GameModes.PLAYERS_2){
            enemy.drawDices(g, bs, window.getWidth() / 2, 150);
            enemy.drawScore(g, bs, 20, 100);
        }
        else if(mode == GameModes.PLAYERS_4){
            enemy.drawDices(g, bs, window.getWidth() / 4, 150);
            enemy.drawScore(g, bs, 20, 40);
            enemy2.drawDices(g, bs, window.getWidth() - window.getWidth() / 4, 150);
            enemy2.drawScore(g, bs, window.getWidth() - 150, 40);
            enemy3.drawDices(g, bs, window.getWidth() - window.getWidth() / 4, window.getHeight() - window.getHeight() / 3);
            enemy3.drawScore(g, bs, window.getWidth() - 150, window.getHeight() - 40);
        }

    }

    private void renderEnding(Graphics g, BufferStrategy bs){
        String name = calculateScore();
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, window.getWidth(), window.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arail", Font.BOLD, 70));
        g.drawString("Wygrywa: "+ name, 20, window.getHeight() / 2 - 50);
    }

    private String calculateScore(){
        if(mode == GameModes.PLAYERS_2){
            if(player.getScore() > enemy.getScore())
                return player.name;
            else if(player.getScore() < enemy.getScore())
                return enemy.name;
            return "Remis";
        }
        else if(mode == GameModes.PLAYERS_4){
            if(player.getScore() >= enemy.getScore() && player.getScore() >= enemy2.getScore() && player.getScore() >= enemy3.getScore())
                return player.name;
            else if(enemy.getScore() >= player.getScore() && enemy.getScore() >= enemy2.getScore() && enemy.getScore() >= enemy3.getScore())
                return enemy.name;
            else if(enemy2.getScore() >= player.getScore() && enemy2.getScore() >= enemy.getScore() && enemy2.getScore() >= enemy3.getScore())
                return enemy2.name;
            return enemy3.name;
        }
        return "Brak Wygranego";
    }

    public void run(){
         init();

         while(isRunning){
            update();
            render();
         }
         stop();
    }

    public synchronized void start(){
        if(isRunning)
            return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!isRunning)
            return;
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
