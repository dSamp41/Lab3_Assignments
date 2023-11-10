public class GameInstance {
    private Player p;
    private Monster m;
    

    public GameInstance(){
        this.p = new Player();
        this.m = new Monster();
    }

    public void newGame(){
        this.m = new Monster();
        this.p.setPotions(this.p.getPotions() + 5);
    }

    public void fight(){    //FIGHT = player attacks, then monster attacks
        m.getDamage();
        if(m.getHp() < 1){
            return;
        }

        //if monster is still alive => player can receive damage
        p.getDamage();
        if(p.getHp() < 1){
            return;
        }
    }
    
    public void usePotion(){
        p.drink();
    }


    public String toString(){
        String pStatus = "Player: hp=" + p.getHp() + " potions=" + p.getPotions() + "\t\t";
        String mStatus = "Monster: hp=" + m.getHp();

        String recap = pStatus + mStatus;
        return recap;
    }
    
}
