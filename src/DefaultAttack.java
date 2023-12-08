import java.util.Random;

public class DefaultAttack implements Attack{
    protected String name;
    protected int intensity;

    public String getName() {
        return name;
    }

    public DefaultAttack(String name, int intensity) {
        this.name = name;
        this.intensity = intensity;
    }

    @Override
    public void performAttack(Pokemon attacker, Pokemon opponent) {
        int damage = new Random().nextInt(intensity) + 1;
        opponent.healthPoints -= damage;
        System.out.println(attacker.getColor() + attacker.name + "\u001B[0m" + " used " + this.name + " and dealt " + damage + " damage to " + opponent.name);
    }
}
