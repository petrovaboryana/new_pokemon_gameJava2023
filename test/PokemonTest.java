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
                attackMenu.addAttack(new DefaultAttack("New Test Attack1", 5));
            }
        };
        attackMenu = new AttackMenu();
        attacker = new Pokemon("Attacker", Size.NORMAL) {
            @Override
            public void initializeAttacks() {
                attackMenu.addAttack(new DefaultAttack("New Test Attack", 10));
            }
        };
        opponent = new Pokemon("Opponent", Size.NORMAL) {
            @Override
            public void initializeAttacks() {
                attackMenu.addAttack(new DefaultAttack("New Test Attack2", 10));
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
//
//        attacker.performAttack(opponent);
//
//        Assertions.assertTrue(opponent.getHealthPoints() < 80, "Opponent health should be decreased after the attack.");
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
