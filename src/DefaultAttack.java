import java.util.Random;

public class DefaultAttack implements Attack{
    String name;
    int intensity;

    DefaultAttack(String name, int intensity) {
        this.name = name;
        this.intensity = intensity;
    }
    @Override
    public void performAttack(Pokemon attacker, Pokemon opponent) {
        int damage = new Random().nextInt(intensity) + 1;
        opponent.healthPoints -= damage;
        System.out.println(attacker.name + " useddd " + name + " and dealt " + damage + " damage to " + opponent.name);
    }
}
