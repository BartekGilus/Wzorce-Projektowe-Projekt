package GameBoard_Strategy;

public class Congfiguratiuon {

    public GameModes gameMode;

    public Congfiguratiuon(GameModes mode){
        this.gameMode = mode;
    }

    public GameBoard setPlayersNumber(){
        if(gameMode == GameModes.PLAYERS_2)
            return new GameBoard_PLAYERS_2();
        else if(gameMode == GameModes.PLAYERS_4)
            return new GameBoard_PLAYERS_4();

        return new GameBoard_PLAYERS_2();
    }
}
