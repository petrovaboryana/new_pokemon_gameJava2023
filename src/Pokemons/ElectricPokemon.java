package Pokemons;

public class ElectricPokemon extends Pokemon {
    public ElectricPokemon(String name, Size size) {
        super(name, "Electric", size);
    }

    @Override
    public void initializeAttacks() {
        attackMenu.addAttack(new ElectricAttack("Thunder Shock", 12));
        attackMenu.addAttack(new ElectricAttack("Spark", 15));
        //да добавя още
    }
}
