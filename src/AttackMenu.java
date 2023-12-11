import java.util.*;

public class AttackMenu {
    private List<Attack> attacks;

    public AttackMenu() {
        this.attacks = new ArrayList<>();
    }

    public void addAttack(Attack attack) {
        attacks.add(attack);
    }

    public Attack chooseAttack() { //тук имам проблем трябва изключение
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an attack:");
        for (int i = 0; i < attacks.size(); i++) {
            Attack attack = attacks.get(i);
            System.out.println((i + 1) + ". " + attack.getName());
        }

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
        return attacks.get(choice - 1);
    }

    public Attack getRandomAttack() {
        Random random = new Random();
        int randomIndex = random.nextInt(attacks.size());
        return attacks.get(randomIndex);
    }
}
