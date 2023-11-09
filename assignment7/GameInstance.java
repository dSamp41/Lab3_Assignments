public class GameInstance {
    enum GameState {
        PLAYING,
        WIN,
        LOST
    }
    
    Player p;
    Monster m;
    GameState state;
    

    public GameInstance(){
        this.p = new Player();
        this.m = new Monster();
        this.state = GameState.PLAYING;
    }

    public void newGame(){
        /*if(m.getHp() <= 0){
            this.m = new Monster();
            this.state = GameState.PLAYING;
        }*/
        this.m = new Monster();
        this.state = GameState.PLAYING;
    }

    public void fight(){
        m.fight();
        if(m.getHp() <= 0){
            this.state = GameState.WIN;
            return;
        }

        //if monster is still alive => player can receive damage
        p.fight();
        if(p.getHp() <= 0){
            this.state = GameState.LOST;
        }
    }
    
    public void usePotion(){
        p.drink();
    }

    public String getState(){
        return this.state.toString();
    }

    public String toString(){
        String pStatus = "Player: hp=" + p.getHp() + " potions=" + p.getPotions() + "\t\t";
        String mStatus = "Monster: hp=" + m.getHp();

        String recap = pStatus + mStatus;
        return recap;
    }
    
}
