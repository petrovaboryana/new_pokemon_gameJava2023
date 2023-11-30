public class GroundPokemon extends Pokemon{
    public GroundPokemon(String name, Size size) {
        super(name, "Ground", size);
    }

    @Override
    public void initializeAttacks() {
        attackMenu.addAttack(new GroundAttack("Earthquake", 10));
        attackMenu.addAttack(new GroundAttack("Rock Slide", 14));
        //да добавя още
    }
}
