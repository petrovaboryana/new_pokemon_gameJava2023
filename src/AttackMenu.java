import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AttackMenu {
    private List<Attack> attacks;

    public AttackMenu() {
        this.attacks = new ArrayList<>();
    }

    public void addAttack(Attack attack) {
        attacks.add(attack);
    }

    public Attack chooseAttack() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an attack:");
        for (int i = 0; i < attacks.size(); i++) {
            Attack attack = attacks.get(i);
            System.out.println((i + 1) + ". " + attack.getName());
        }

        int choice;
        do {
            choice = scanner.nextInt();
        } while (choice < 1 || choice > attacks.size());

        return attacks.get(choice - 1);
    }
    public Attack getRandomAttack() {
        Random random = new Random();
        int randomIndex = random.nextInt(attacks.size());
        return attacks.get(randomIndex);
    }
}
