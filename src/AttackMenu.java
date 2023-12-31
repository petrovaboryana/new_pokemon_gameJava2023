import java.util.*;

public class AttackMenu {
    private List<Attackable> attacks;
    private static Scanner scanner = new Scanner(System.in);

    public AttackMenu() {
        this.attacks = new ArrayList<>();
    }

    public List<Attackable> getAttacks() {
        return attacks;
    }

    public void addAttack(Attackable attack) {
        attacks.add(attack);
    }

    public void displayAttackMenu() {
        System.out.println("Choose an attack:");
        for (int i = 0; i < attacks.size(); i++) {
            Attackable attack = attacks.get(i);
            System.out.println((i + 1) + ". " + attack.getName());
        }
    }

    private int getValidInput() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter the number corresponding to your choice:");
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= attacks.size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + attacks.size() + ".");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input or input not available. Please try again.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public Attackable chooseAttack() {
        displayAttackMenu();
        int choice = getValidInput();
        return attacks.get(choice - 1);
    }

    public Attackable getRandomAttack() {
        Random random = new Random();
        int randomIndex = random.nextInt(attacks.size());
        return attacks.get(randomIndex);
    }
}
