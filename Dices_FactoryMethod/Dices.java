package Dices_FactoryMethod;

import java.awt.*;
import java.awt.image.BufferStrategy;

public interface Dices {
   int size = 100;

    void setValue();
    int getValue();
    void render(Graphics g, BufferStrategy bs, int x, int y);
}
