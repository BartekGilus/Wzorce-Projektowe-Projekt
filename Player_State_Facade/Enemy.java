package Player_State_Facade;

import Dices_FactoryMethod.DiceFactory;
import Dices_FactoryMethod.DiceType;
import Dices_FactoryMethod.Dices;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Enemy extends AbstractPlayer {

    private Dices dice1;
    private Dices dice2;

    public Enemy(String name){
        this.name = name;
    }

    @Override
    public void throwDices(DiceType dice) {
        Dices dice1 = new DiceFactory().getDice(dice);
        this.dice1 = dice1;
        Dices dice2 = new DiceFactory().getDice(dice);
        this.dice2 = dice2;
        score += dice1.getValue();
        score += dice2.getValue();
    }

    @Override
    public int getScore() {
        return score;
    }

    public String ToString(){
        return Integer.toString(score);
    }

    public void drawDices(Graphics g, BufferStrategy bs, int x, int y){
        if (dice1 != null && dice2 != null) {
            dice1.render(g, bs, x - 150, y);
            dice2.render(g, bs, x, y);
        }
    }

    public void drawScore(Graphics g, BufferStrategy bs, int x, int y){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Wynik: "+ ToString(), x, y);
    }
}
