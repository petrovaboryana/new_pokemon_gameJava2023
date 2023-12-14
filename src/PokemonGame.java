import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PokemonGame {
    public static void main(String[] args) {
        String redColor = "\u001B[31m";
        String reset = "\u001B[0m";
        try {
            MusicPlayer.playMusic("Pokémon Theme Song (Music Video).wav");
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

        System.out.println(redColor + "------WELCOME TO POKEMON GAME BATTLE. ARE YOU READY FOR THIS?------" + reset);
        System.out.println("Please choose 3 pokemons to play with (select by number): ");
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }
}
