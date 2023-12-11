import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {//да извадя сканера
    private static final int NUM_BATTLES = 5;
    private static final int NUM_ROUNDS_PER_BATTLE = 5;
    private static final int CRYSTALS_PER_BATTLE = 10;
    private static final int DIAMONDS_PER_BATTLE = 50;
    String redColor = "\u001B[31m";
    String resetColor = "\u001B[0m";
    public int startTournament(List<Pokemon> userPokemon) {
        int totalCrystals = 0;

        for (int battleNum = 1; battleNum <= NUM_BATTLES; battleNum++) {
            System.out.println("\n----- Battle " + battleNum + " -----");
            int crystalsEarned = playBattle(userPokemon);
            totalCrystals += crystalsEarned;

            if (crystalsEarned == 0) {
                System.out.println("You lost the tournament. Better luck next time!");
                break;
            }
        }
        return totalCrystals;
    }
    private int playBattle(List<Pokemon> userPokemon) {
        int userWins = 0;

        for (int round = 1; round <= NUM_ROUNDS_PER_BATTLE; round++) {
            System.out.println("\n--- Round " + round + " ---");

            int result = playRound(userPokemon);
            if (result == 1) {
                userWins++;
            } else if (result == 2) {
                System.out.println("Your Pokemon fainted.");
            }
        }
        return whoWinsTheBattle(userWins);
    }
    public int whoWinsTheBattle(int userWins) {
        int crystals = 0;
        if (userWins < NUM_ROUNDS_PER_BATTLE / 2) {
            System.out.println("You lost the battle.");
            return 0;
        } else {
            System.out.println("You won the battle!");
            return crystals += CRYSTALS_PER_BATTLE;
        }
    }

    private int playRound(List<Pokemon> userPokemon) {
        Pokemon opponent = getRandomOpponent();
        System.out.println("You are facing a " + opponent.getColor() + opponent.name + resetColor + "!");
        Pokemon activePokemon = PokemonMenu.chooseActivePokemon(userPokemon);

        System.out.println("Your " + activePokemon.name + "'s turn:");
        displayBattleOptions();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            activePokemon.performAttack(opponent);
            System.out.println("Your " + activePokemon.name + "'s HP: " + activePokemon.getHealthPoints());
            System.out.println("Opponent's " + opponent.name + "'s HP: " + opponent.getHealthPoints());
            } else if (choice == 2) {
                activePokemon = changePokemon(userPokemon, activePokemon);
            }

        System.out.println("Opponent's turn:");
        opponent.performRandomAttack(activePokemon);
        System.out.println("Your " + activePokemon.name + "'s HP: " + activePokemon.getHealthPoints());
        System.out.println("Opponent's " + opponent.name + "'s HP: " + opponent.getHealthPoints());

        if (opponent.healthPoints < activePokemon.healthPoints) {
            System.out.println("Opponent fainted!");
            return 1;
        } else if (opponent.healthPoints == activePokemon.healthPoints ) {
            System.out.println("No one wins!");
            return 0;
        }
        System.out.println("Your " + activePokemon.name + " fainted!");
        return 2;
    }

    private void displayBattleOptions() {
        System.out.println("Choose your action:");
        System.out.println(redColor + "1. Attack" + resetColor);
        System.out.println("2. Change Pokemon");
    }
    private Pokemon getRandomOpponent() {
        List<Pokemon> availableOpponents = new ArrayList<>();
        availableOpponents.add(new ElectricPokemon("Wild Pikachu", Size.NORMAL));
        availableOpponents.add(new WaterPokemon("Wild Squirtle", Size.NORMAL));
        availableOpponents.add(new GroundPokemon("Wild Geodude", Size.NORMAL));
        availableOpponents.add(new ElectricPokemon("Wild Shockle",Size.LARGE));
        availableOpponents.add(new WaterPokemon("Wild Shark",Size.LARGE));

        Random random = new Random();
        int randomIndex = random.nextInt(availableOpponents.size());
        return availableOpponents.get(randomIndex);
    }
    private Pokemon changePokemon(List<Pokemon> userPokemon, Pokemon currentPokemon) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a Pokemon to switch to:");
        PokemonMenu.displayUserPokemon(userPokemon);
        int switchIndex = scanner.nextInt();

        if (switchIndex >= 1 && switchIndex <= userPokemon.size()) {
            Pokemon newPokemon = userPokemon.get(switchIndex - 1);

            if (newPokemon.healthPoints > 0) {
                System.out.println("Switching to " + newPokemon.getColor() + newPokemon.name + "\u001B[0m" + "!");
                return newPokemon;
            } else {
                System.out.println(newPokemon.name + " has fainted and cannot be switched to!");
            }
        } else {
            System.out.println("Invalid choice. No switch was made.");
        }
        return currentPokemon;
    }
}
