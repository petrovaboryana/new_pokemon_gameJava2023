public class WaterPokemon extends Pokemon{
    public WaterPokemon(String name, Size size) {
        super(name, Type.WATER, size, "\u001B[34m");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void initializeAttacks() {
        attackMenu.addAttack(new WaterAttack("Water Gun", 15));
        attackMenu.addAttack(new WaterAttack("Hydro Pump", 18));
        attackMenu.addAttack(new WaterAttack("Water Blaster",20));
    }
}
