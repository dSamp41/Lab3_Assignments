import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private int hp;
    private int potions;

    private final int MAX_HP = 10;
    private final int MAX_POTIONS = 20;

    public Player(){
        this.hp = ThreadLocalRandom.current().nextInt(5, MAX_HP);
        this.potions = ThreadLocalRandom.current().nextInt(7, MAX_POTIONS);
    }

    public void fight(){
        int n = ThreadLocalRandom.current().nextInt(1, MAX_HP/3);
        this.hp -= n;
    }

    public void drink(){
        if(this.potions > 0){
            int num = ThreadLocalRandom.current().nextInt(1, this.potions);
            this.hp += num;
            this.potions -= num;
        }
        else{
            System.out.println("No more potions");
        }
    }

    public int getHp(){
        return this.hp;
    }

    public int getPotions(){
        return this.potions;
    }    
}
