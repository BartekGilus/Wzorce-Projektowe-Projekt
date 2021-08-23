package GameBoard_Strategy;

import java.awt.*;
import java.awt.image.BufferStrategy;

public interface GameBoard {
    void render(Graphics g, BufferStrategy bs, int width, int height);
}
