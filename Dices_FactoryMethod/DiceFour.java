package Dices_FactoryMethod;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class DiceFour implements Dices {
    private int value;

    public DiceFour(){
        setValue();
    }

    @Override
    public void setValue() {
        Random gen = new Random();
        this.value = gen.nextInt(4) + 1;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void render(Graphics g, BufferStrategy bs, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        switch(value){
            case 1:
                g.fillOval(x + (size / 2 - 8), y + (size / 2 - 8), 16, 16);
                break;
            case 2:
                g.fillOval(x + ((size / 2) / 2 - 8), y + (size / 2 - 8), 16, 16);
                g.fillOval(x + ((size - (size / 2) / 2) - 8), y + (size / 2 - 8), 16, 16);
                break;
            case 3:
                g.fillOval(x + (size / 2 - 8), y + (size / 2 - 8), 16, 16);
                g.fillOval(x + (size / 2 - 8), y + ((size / 2) / 2 - 8), 16, 16);
                g.fillOval(x + (size / 2 - 8), y + ((size - (size / 2) / 2) - 8), 16, 16);
                break;
            case 4:
                g.fillOval(x + ((size / 2) / 2 - 8), y + ((size / 2) / 2 - 8), 16, 16);
                g.fillOval(x + ((size - (size / 2) / 2) - 8), y + ((size / 2) / 2 - 8), 16, 16);
                g.fillOval(x + ((size / 2) / 2 - 8), y + ((size - (size / 2) / 2) - 8), 16, 16);
                g.fillOval(x + ((size - (size / 2) / 2) - 8), y + ((size - (size / 2) / 2) - 8), 16, 16);
                break;
        }
    }
}
