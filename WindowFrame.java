import Dices_FactoryMethod.DiceType;
import GameBoard_Strategy.GameModes;

import javax.swing.*;
import java.awt.*;


public class WindowFrame {

    private JFrame frame;
    private Canvas canvas;
    private static WindowFrame instance = null;

    private String title;
    private int width, height;
    private Game game;

    private WindowFrame(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createWindow();
        createMenu(frame);
    }

    public static WindowFrame getInstance(){
        if(instance == null) instance = new WindowFrame("WPP - Projekt Zaliczeniowy", 1280, 720);
        return instance;
    }

    public void setGame(Game game){
        this.game = game;
    }

    private void createWindow(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
    }

    private void createMenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu dices = new JMenu("Rodzaje kosci");
        JMenu boards = new JMenu("Ilosc graczy");

        JMenuItem newGame = new JMenuItem("Nowa gra");;

        JMenuItem dice1 = new JMenuItem("Kosci cztero scienne");
        JMenuItem dice2 = new JMenuItem("Kosci szescio scienne");

        JMenuItem board1 = new JMenuItem(" Dwoch graczy");
        JMenuItem board2 = new JMenuItem(" Czterech graczy");
        newGame.addActionListener(e -> {
            game.initPlayers();
        });
        dice1.addActionListener(e -> {
            game.setDicetype(DiceType.DICE_4);
            game.initPlayers();
        });
        dice2.addActionListener(e ->{
            game.setDicetype(DiceType.DICE_6);
            game.initPlayers();
        });
        board1.addActionListener(e -> {
            game.setMode(GameModes.PLAYERS_2);
            game.initPlayers();
        });
        board2.addActionListener(e -> {
            game.setMode(GameModes.PLAYERS_4);
            game.initPlayers();
        });

        file.add(newGame);
        dices.add(dice1);
        dices.add(dice2);
        boards.add(board1);
        boards.add(board2);
        menuBar.add(file);
        menuBar.add(dices);
        menuBar.add(boards);

        frame.setJMenuBar(menuBar);
    }

    public Canvas getCanvas(){
        return canvas;
    }
    public JFrame getFrame(){
        return frame;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
