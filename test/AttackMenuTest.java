import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackMenuTest {
    private AttackMenu attackMenu;

    @BeforeEach
    public void setUp() {
        attackMenu = new AttackMenu();
    }

    @Test
    public void testAddAttack() {
        Attackable testAttack = new DefaultAttack("Test attack",12);
        attackMenu.addAttack(testAttack);
        assertEquals(1, attackMenu.getAttacks().size());
        assertEquals(testAttack, attackMenu.getAttacks().get(0));
    }

    @Test
    public void testChooseAttackWithValidInput() {
        List<Attackable> attacks = new ArrayList<>();
        Attackable attack1 = new DefaultAttack("Attack 1", 10);
        Attackable attack2 = new DefaultAttack("Attack 2", 20);
        attacks.add(attack1);
        attacks.add(attack2);

        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        attackMenu.setAttacks(attacks);

        Attackable chosenAttack = attackMenu.chooseAttack();

        assertEquals(attack2, chosenAttack);
    }

    @Test
    public void testChooseAttackWithInvalidInputThenValidInput() {
        List<Attackable> attacks = new ArrayList<>();
        Attackable attack1 = new DefaultAttack("Attack 1", 10);
        Attackable attack2 = new DefaultAttack("Attack 2", 20);
        attacks.add(attack1);
        attacks.add(attack2);

        String input = "invalid\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        attackMenu.setAttacks(attacks);

        Attackable chosenAttack = attackMenu.chooseAttack();

        assertEquals(attack2, chosenAttack);
    }

    @Test
    public void testGetRandomAttack() {
        Attackable attack1 = new DefaultAttack("Test attack1",6);
        Attackable attack2 = new ElectricAttack("Electric test attack",10);
        attackMenu.addAttack(attack1);
        attackMenu.addAttack(attack2);

        List<Attackable> randomAttacks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomAttacks.add(attackMenu.getRandomAttack());
        }
        Assertions.assertTrue(randomAttacks.contains(attack1));
        Assertions.assertTrue(randomAttacks.contains(attack2));
    }
    @Test
    public void testGetAttacks() {
        Assertions.assertNotNull(attackMenu.getAttacks());
        Assertions.assertTrue(attackMenu.getAttacks().isEmpty());
    }
    @Test
    public void testSetAttacks() {
        List<Attackable> newAttacks = new ArrayList<>();
        newAttacks.add(new DefaultAttack("TestAttack",10));

        attackMenu.setAttacks(newAttacks);

        Assertions.assertEquals(newAttacks, attackMenu.getAttacks());
    }
}
