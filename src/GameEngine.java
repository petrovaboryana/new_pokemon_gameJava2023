import java.util.List;

public class GameEngine {
    protected PokemonMenu pokemonMenu;
    protected BattleSystem battleSystem;

    public GameEngine() {
        this.pokemonMenu = new PokemonMenu();
        this.battleSystem = new BattleSystem();
    }

    public void startGame() {
        pokemonMenu.choosePokemon();
        List<Pokemon> userPokemon = pokemonMenu.getUserPokemon();

        int diamonds = battleSystem.startTournament(userPokemon);

        System.out.println("\nTournament ended!");
        System.out.println("Your result: " + diamonds + " crystals");
        if (diamonds >= 15) {
            System.out.println("Congratulations! You completed the tournament successfully.");
        } else {
            System.out.println("You need at least 15 diamonds to win the tournament. Better luck next time!");
        }
    }
}
