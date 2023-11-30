import java.util.ArrayList;
import java.util.List;
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
            System.out.println((i + 1) + ". " + attacks.get(i).getClass().getSimpleName());
        }

        int choice;
        do {
            choice = scanner.nextInt();
        } while (choice < 1 || choice > attacks.size());

        return attacks.get(choice - 1);
    }
}
