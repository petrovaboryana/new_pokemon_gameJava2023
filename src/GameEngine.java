import java.util.List;

public class GameEngine {
    protected PokemonMenu pokemonMenu;
    protected BattleSystem battleSystem;

    public GameEngine() {
        this.pokemonMenu = new PokemonMenu();
        this.battleSystem = new BattleSystem();
    }

    public void setPokemonMenu(PokemonMenu pokemonMenu) {
        this.pokemonMenu = pokemonMenu;
    }

    public void setBattleSystem(BattleSystem battleSystem) {
        this.battleSystem = battleSystem;
    }

    public Result startGame() {
        pokemonMenu.choosePokemon();
        List<Pokemon> userPokemon = pokemonMenu.getUserPokemon();

        Result tournamentResult = battleSystem.startTournament(userPokemon);

        System.out.println("\nTournament ended!");
        System.out.println("Your result: " + tournamentResult.getDiamondsFromBattles() + " diamonds");
        if (tournamentResult.getDiamondsFromBattles() >= 15) {
            System.out.println("Congratulations! You completed the tournament successfully.");
        } else {
            System.out.println("You need at least 15 diamonds to win the tournament. Better luck next time!");
        }
        return tournamentResult;
    }
}
