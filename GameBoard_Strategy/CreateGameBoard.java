package GameBoard_Strategy;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class CreateGameBoard {

    private GameBoard board;
    private Graphics g;
    private BufferStrategy bs;
    private int width, height;

    public CreateGameBoard(GameModes mode, Graphics g, BufferStrategy bs, int width, int height){
        this.g = g;
        this.bs = bs;
        this.width = width;
        this.height = height;
        this.board = new Congfiguratiuon(mode).setPlayersNumber();
    }

    public void render(){
        board.render(g, bs, width, height);
    }
}

