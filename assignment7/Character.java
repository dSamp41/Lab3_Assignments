import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {
    int hp;

    private final int MAX_HP;

    public Character(int CHAR_MAX_HP){
        this.MAX_HP = CHAR_MAX_HP;
        this.hp = ThreadLocalRandom.current().nextInt(5, MAX_HP);
    }

    public void getDamage(){
        int n = ThreadLocalRandom.current().nextInt(1, MAX_HP/3);
        this.hp -= n;
    }

    public int getHp(){
        return this.hp;
    }
}
