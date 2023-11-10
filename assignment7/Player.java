import java.util.concurrent.ThreadLocalRandom;

public class Player extends Character{
    private static final int MIN_HP = 7;
    private static final int MAX_HP = 10;

    private static final int MIN_POTIONS = 5;
    private static final int MAX_POTIONS = 20;
    
    private int potions;

    public Player(){
        super(MIN_HP, MAX_HP);
        this.potions = ThreadLocalRandom.current().nextInt(MIN_POTIONS, MAX_POTIONS);
    }

    public void drink(){
        if(this.potions >= 2){
            int num = ThreadLocalRandom.current().nextInt(1, this.potions);
            this.hp += num;
            this.potions -= num;
        }
        else if(this.potions == 1){
            this.hp += 1;
            this.potions -= 1;
        }
        else{
            System.out.println("No more potions");
        }
    }

    public int getPotions(){
        return this.potions;
    }

    public void setPotions(int n){
        this.potions = n;
    }    
}