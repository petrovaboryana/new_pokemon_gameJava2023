import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonTest {
    private Pokemon pokemon;
    private Pokemon attacker;
    private Pokemon opponent;
    private AttackMenu attackMenu;

    @BeforeEach
    public void setUp() {
        pokemon = new Pokemon("Test pokemon", Size.NORMAL) {
            @Override
            public void initializeAttacks() {
            }
        };
        attackMenu = new AttackMenu();
        attacker = new Pokemon("Attacker", Size.NORMAL) {
            @Override
            public void initializeAttacks() {

            }
        };
        opponent = new Pokemon("Opponent", Size.NORMAL) {
            @Override
            public void initializeAttacks() {

            }
        };
    }

    @Test
    public void testAdjustStatsBasedOnSizeSmall() {
        pokemon.setSize(Size.SMALL);
        int initialHealthPoints = pokemon.getHealthPoints();

        pokemon.adjustStatsBasedOnSize();

        int expectedHealthPoints = initialHealthPoints - 20;
        Assertions.assertEquals(expectedHealthPoints, pokemon.getHealthPoints());
    }

    @Test
    public void testAdjustStatsBasedOnSizeLarge() {
        pokemon.setSize(Size.LARGE);
        int initialHealthPoints = pokemon.getHealthPoints();

        pokemon.adjustStatsBasedOnSize();

        int expectedHealthPoints = initialHealthPoints + 20;
        Assertions.assertEquals(expectedHealthPoints, pokemon.getHealthPoints());
    }
//    @Test
//    public void testPerformAttack() {
//        Attackable testAttack = new DefaultAttack("Test Attack", 10);
//        attackMenu.addAttack(testAttack);
//
//        attacker.performAttack(opponent);
//
//        Assertions.assertEquals(90, opponent.getHealthPoints());
//    }
//
//    @Test
//    public void testPerformRandomAttack() {
//        Attackable testRandomAttack = new DefaultAttack("Random Test Attack", 15);
//        attackMenu.addAttack(testRandomAttack);
//
//        attacker.performRandomAttack(opponent);
//
//        Assertions.assertEquals(85, opponent.getHealthPoints());
//    }
}
