public class GroundPokemon extends Pokemon{
    public GroundPokemon(String name, Size size) {
        super(name, Type.GROUND, size);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void initializeAttacks() {
        attackMenu.addAttack(new GroundAttack("Earthquake", 20));
        attackMenu.addAttack(new GroundAttack("Rock Slide", 25));
        attackMenu.addAttack(new GroundAttack("Sand Storm", 30));
    }
}
