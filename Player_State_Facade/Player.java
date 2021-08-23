package Player_State_Facade;

import Dices_FactoryMethod.DiceFactory;
import Dices_FactoryMethod.DiceType;
import Dices_FactoryMethod.Dices;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Player extends AbstractPlayer {

    private PlayerState state;
    public String name;
    private Dices dice1;
    private Dices dice2;

    public Player(String name){
        this.name = name;
        state = MovingState.getInstance();
        state.setState(this);
    }

    public void MovedState(){
        state.moved();
    }

    public void Moving(){
        state.Moving();
    }

    public void changeState(PlayerState p){
        state = p;
        state.setState(this);
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

    public boolean getTurn(){
        return playerTurn;
    }

    public void drawDices(Graphics g, BufferStrategy bs, int x, int y) {
        if (dice1 != null && dice2 != null) {
            dice1.render(g, bs, x - 150, y );
            dice2.render(g, bs, x, y);
        }
    }

    public void drawButton(Graphics g, BufferStrategy bs, int width, int height) {
        g.setColor(Color.GRAY);
        g.fillRect(width / 2 - 100, height - 100, 200, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Rzuć", width / 2 - 60, height - 60);
    }

    public void drawScore(Graphics g, BufferStrategy bs, int x, int y){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Wynik: "+ ToString(), x, y);
    }

}
