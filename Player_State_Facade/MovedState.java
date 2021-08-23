package Player_State_Facade;

public class MovedState implements PlayerState {

    private static MovedState instance = null;
    Player player = null;

    public static PlayerState getInstance(){
        if(instance == null) instance = new MovedState();
        return instance;
    }

    @Override
    public void moved() {

    }

    @Override
    public void Moving() {
        System.out.println("Znowu mozesz sie ruszac");
        player.playerTurn = true;
        player.changeState(MovingState.getInstance());
    }

    @Override
    public void setState(Player p) {
        player = p;
    }
}
