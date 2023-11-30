public class WaterPokemon extends Pokemon{
    public WaterPokemon(String name, Size size) {
        super(name, "Water", size);
    }

    @Override
    public void initializeAttacks() {
        attackMenu.addAttack(new WaterAttack("Water Gun", 15));
        attackMenu.addAttack(new WaterAttack("Hydro Pump", 18));
        //да добавя още атаки
    }
}
