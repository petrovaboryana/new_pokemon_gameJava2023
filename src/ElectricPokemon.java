public class ElectricPokemon extends Pokemon {
    public ElectricPokemon(String name, Size size) {
        super(name,Type.ELECTRIC, size,"\u001B[33m");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void initializeAttacks() {
        attackMenu.addAttack(new ElectricAttack("Thunder Shock", 20));
        attackMenu.addAttack(new ElectricAttack("Spark", 25));
        attackMenu.addAttack(new ElectricAttack("Electric Thunder",30));
    }
}
