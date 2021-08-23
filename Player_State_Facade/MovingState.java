package Player_State_Facade;

public class MovingState implements PlayerState {

    private static MovingState instance = null;
    Player player = null;

    public static PlayerState getInstance(){
        if(instance == null) instance = new MovingState();
        return instance;
    }

    @Override
    public void moved() {
        player.playerTurn = false;
        System.out.println("Gracze sie ruszyli");
        player.changeState(MovedState.getInstance());
    }

    @Override
    public void Moving() {

    }

    @Override
    public void setState(Player p) {
        player = p;
    }
}
