package GameBoard_Strategy;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameBoard_PLAYERS_2 implements GameBoard {

    @Override
    public void render(Graphics g, BufferStrategy bs, int width, int height) {
        g.setColor(new Color(0,100,0));
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(0, height / 2 - 3, width, 6);
    }

}
