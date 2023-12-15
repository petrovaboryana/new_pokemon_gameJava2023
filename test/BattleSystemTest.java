import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BattleSystemTest {
    private BattleSystem battleSystem;
    private InputStream originalSystemIn;
    private List<Pokemon> testUserPokemon;
    private Pokemon opponent;
    private Pokemon activePokemon;
    private static Scanner scanner = new Scanner(System.in);

    @BeforeEach
    public void setUp() {
        battleSystem = new BattleSystem();
        originalSystemIn = System.in;
        testUserPokemon = new ArrayList<>();
        opponent = new ElectricPokemon("Test Opponent", Size.NORMAL);
        activePokemon = new ElectricPokemon("Test Active Pokemon", Size.NORMAL);
    }
    @Test
    public void testGetRandomOpponent() {
        Pokemon randomOpponent = battleSystem.getRandomOpponent();

        Assertions.assertNotNull(randomOpponent);
        Assertions.assertTrue(true);
    }

    @Test
    public void testChangePokemon() {
        testUserPokemon.add(new ElectricPokemon("Test Pikachu", Size.NORMAL));
        testUserPokemon.add(new WaterPokemon("Test Squirtle", Size.NORMAL));

        Pokemon testCurrentPokemon = new ElectricPokemon("Test Current Pokemon", Size.NORMAL);

        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Pokemon newPokemon = battleSystem.changePokemon(testUserPokemon, testCurrentPokemon);

        System.setIn(originalSystemIn);

        Assertions.assertEquals(testUserPokemon.get(1), newPokemon);
        Assertions.assertTrue(newPokemon.getHealthPoints() > 0);
    }

    @Test
    public void testWhoWinsTheBattle_Lose() {
        int result = battleSystem.whoWinsTheBattle(2, 3);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void testWhoWinsTheBattle_Win() {
        int beforeDiamonds = 0;
        int resultDiamonds = battleSystem.whoWinsTheBattle(3, 2);

        Assertions.assertEquals(beforeDiamonds + 5,resultDiamonds);
    }
    @Test
    void testWhoWinsTheRound_Win() {
        opponent.setHealthPoints(50);
        activePokemon.setHealthPoints(70);
        int result = battleSystem.whoWinsTheRound(opponent, activePokemon);

        Assertions.assertEquals(1, result);
    }
    @Test
    void testWhoWinsTheRound_Equal(){
        opponent.setHealthPoints(50);
        activePokemon.setHealthPoints(50);
        int result = battleSystem.whoWinsTheRound(opponent, activePokemon);

        Assertions.assertEquals(0, result);
    }
    @Test
    void testWhoWinsTheRound_Loss(){
        opponent.setHealthPoints(50);
        activePokemon.setHealthPoints(20);
        int result = battleSystem.whoWinsTheRound(opponent, activePokemon);

        Assertions.assertEquals(2, result);
    }

    @Test
    public void testGetActivePokemonWithInvalidInput() {
        Result result  = new Result(10,10);
        Pokemon resultPokemon = battleSystem.getActivePokemon(testUserPokemon, 5, activePokemon, opponent,result);

        assertNotNull(resultPokemon);
        assertEquals(activePokemon, resultPokemon);
    }
}
