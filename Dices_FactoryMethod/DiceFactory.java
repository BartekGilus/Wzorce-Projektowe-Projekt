package Dices_FactoryMethod;

public class DiceFactory {
    public Dices getDice(DiceType type){
        switch(type){
            case DICE_4:
                return new DiceFour();
            case DICE_6:
                return new DiceSix();
            default:
                return null;
        }
    }
}
