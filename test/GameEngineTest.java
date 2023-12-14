import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class GameEngineTest {
    private GameEngine gameEngine;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setup(){
        gameEngine = new GameEngine();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
    }
    @Test
    public void testStartGameSuccessfulTournament() {
        // Assume that your real PokemonMenu and BattleSystem implementations are available
        PokemonMenu pokemonMenu = new PokemonMenu();
        BattleSystem battleSystem = new BattleSystem();

        gameEngine.setPokemonMenu(pokemonMenu);
        gameEngine.setBattleSystem(battleSystem);

        // Mock user input to choose Pokemon
        InputStream mockInputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(mockInputStream);

        // Act
        Result result = gameEngine.startGame();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getDiamondsFromBattles() >= 15);
        Assertions.assertTrue(outContent.toString().contains("Congratulations! You completed the tournament successfully."));
        // Add more assertions based on your actual implementation
    }

    @Test
    public void testStartGameUnsuccessfulTournament() {
        // Assume that your real PokemonMenu and BattleSystem implementations are available
        PokemonMenu pokemonMenu = new PokemonMenu();
        BattleSystem battleSystem = new BattleSystem();

        gameEngine.setPokemonMenu(pokemonMenu);
        gameEngine.setBattleSystem(battleSystem);

        // Mock user input to choose Pokemon
        InputStream mockInputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(mockInputStream);

        // Act
        Result result = gameEngine.startGame();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getDiamondsFromBattles() < 15);
        Assertions.assertTrue(outContent.toString().contains("You need at least 15 diamonds to win the tournament. Better luck next time!"));
        // Add more assertions based on your actual implementation
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

}
