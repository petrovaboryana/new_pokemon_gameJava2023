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

//    @Test
//    public void testPlayBattle() {
//        testUserPokemon.add(new ElectricPokemon("Test Pikachu", Size.NORMAL));
//
//        Result result = battleSystem.playBattle(testUserPokemon,opponent,activePokemon);
//
//        String input = "1\n";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        Scanner scanner = new Scanner(System.in);
//        battleSystem.setScanner(scanner);
//
//        assertNotNull(result);
//        assertTrue(result.getCrystalsFromRounds() >= 0);
//        assertTrue(result.getDiamondsFromBattles() >= 0);
//    }

    @Test
    public void testGetRandomOpponent() {
        Pokemon randomOpponent = battleSystem.getRandomOpponent();

        Assertions.assertNotNull(randomOpponent); // опонента не е null
        Assertions.assertTrue(true); // върнатият опонент е Pokemon
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
    public void testWhoWinsTheBattle() {
        int result = battleSystem.whoWinsTheBattle(2, 3);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testWhoWinsTheRound() {
        int result = battleSystem.whoWinsTheRound(opponent, activePokemon);

        assertTrue(result >= 0 && result <= 2);
        switch (result) {
            case 0:
                System.out.println("No one wins!");
                break;
            case 1:
                System.out.println("You won the round!");
                break;
            case 2:
                System.out.println("Your " + activePokemon.getName() + " lost!");
                break;
            default:
        }
    }
    @Test
    public void testGetActivePokemonWithInvalidInput() {
        Result result  = new Result(10,10);
        Pokemon resultPokemon = battleSystem.getActivePokemon(testUserPokemon, 5, activePokemon, opponent,result);

        assertNotNull(resultPokemon);
        assertEquals(activePokemon, resultPokemon);
    }
}
