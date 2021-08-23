package Player_State_Facade;

import Dices_FactoryMethod.DiceType;

import java.awt.*;
import java.awt.image.BufferStrategy;

public abstract class AbstractPlayer {

    protected int score = 0;
    protected boolean playerTurn = true;
    public String name;

    public abstract void throwDices(DiceType dice);
    public abstract int getScore();

    public abstract String ToString();
    public abstract void drawDices(Graphics g, BufferStrategy bs, int x, int y);
    public abstract void drawScore(Graphics g, BufferStrategy bs, int x, int y);
}
