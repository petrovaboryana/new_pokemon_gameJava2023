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
    @Test
    public void testHealIncreasesHealth() {
        int initialHealth = pokemon.getHealthPoints();
        int healAmount = 10;

        pokemon.heal(healAmount);

        Assertions.assertEquals(initialHealth + healAmount, pokemon.getHealthPoints());
    }

    @Test
    public void testHealAtFullHealth() {
        int maxHealth = 100;
        pokemon.setHealthPoints(maxHealth);

        pokemon.heal(10);

        Assertions.assertEquals(maxHealth, pokemon.getHealthPoints());
    }
    @Test
    public void testPerformAttackReducesOpponentHealthToZero() {
        Attackable chosenAttack = new DefaultAttack("test attack",20);
        opponent.setHealthPoints(5);
        chosenAttack.performAttack(attacker, opponent);
        Assertions.assertTrue(opponent.getHealthPoints() >= 0);
        Assertions.assertEquals(0,opponent.getHealthPoints());
    }
    @Test
    void testAttackDoesNotReduceOpponentHealthBelowZero() {
        Attackable chosenAttack = new DefaultAttack("test attack",20);
        opponent.setHealthPoints(2);
        int initialHealth = opponent.getHealthPoints();

        chosenAttack.performAttack(attacker, opponent);

        Assertions.assertTrue(opponent.getHealthPoints() >= 0);
        Assertions.assertTrue(opponent.getHealthPoints() <= initialHealth);
    }

}
