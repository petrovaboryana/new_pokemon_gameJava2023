import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class PokemonGame {
    public static void main(String[] args) {
        System.out.println("------WELCOME TO POKEMON GAME BATTLE. ARE YOU READY FOR THIS?------ \n");
        System.out.println("Please choose 3 pokemons to play with (select by number): ");
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }
}
