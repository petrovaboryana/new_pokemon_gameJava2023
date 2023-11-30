import java.util.List;

public class GameEngine {
    private PokemonMenu pokemonMenu;
    private BattleSystem battleSystem;

    public GameEngine() {
        this.pokemonMenu = new PokemonMenu();
        this.battleSystem = new BattleSystem();
    }

    public void startGame() {
        pokemonMenu.choosePokemon();
        List<Pokemon> userPokemon = pokemonMenu.getUserPokemon();

        int crystals = battleSystem.startTournament(userPokemon);

        System.out.println("\nTournament ended!");
        System.out.println("Your result: " + crystals + " crystals");
        if (crystals >= 40) {
            System.out.println("Congratulations! You completed the tournament successfully.");
        } else {
            System.out.println("You need at least 40 crystals to win the tournament. Better luck next time!");
        }
    }
}
