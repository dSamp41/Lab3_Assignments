import java.util.concurrent.ThreadLocalRandom;

public class Monster {
    
    private int hp;
    private final int MAX_HP = 15;

    public Monster(){
        this.hp = ThreadLocalRandom.current().nextInt(5, MAX_HP);
    }

    public void fight(){
        int n = ThreadLocalRandom.current().nextInt(1, MAX_HP/3);
        this.hp -= n;
    }

    public int getHp(){
        return this.hp;
    }
}
