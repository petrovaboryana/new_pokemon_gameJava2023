import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {//да извадя сканера
    private static final int NUM_BATTLES = 5;
    private static final int NUM_ROUNDS_PER_BATTLE = 3;
    private static final int CRYSTALS_PER_VICTORY = 10;
    String redColor = "\u001B[31m";
    String resetColor = "\u001B[0m";

    public int startTournament(List<Pokemon> userPokemon) {
        int crystals = 0;

        for (int battleNum = 1; battleNum <= NUM_BATTLES; battleNum++) {
            System.out.println("\n----- Battle " + battleNum + " -----");

            for (int round = 1; round <= NUM_ROUNDS_PER_BATTLE; round++) {
                System.out.println(redColor + "\n--- Round " + round + " ---" + resetColor);
                Pokemon opponent = getRandomOpponent();

                System.out.println("You are facing a " + opponent.getColor() + opponent.name + resetColor + "!");

                boolean allUserPokemonDead = false;
                boolean opponentDead = false;
                Pokemon activePokemon = PokemonMenu.chooseActivePokemon(userPokemon);
                while (!allUserPokemonDead && !opponentDead) {


                    System.out.println("\nYour " + activePokemon.getColor() + activePokemon.getName() + "\u001B[0m" + "'s turn:");
                    displayBattleOptions();
                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        activePokemon.performAttack(opponent);
                    } else if (choice == 2) {
                        activePokemon = changePokemon(userPokemon, activePokemon);
                    }

                    if (opponent.healthPoints <= 0) {
                        opponentDead = true;
                        break;
                    }
                    System.out.println(redColor + "Opponent's turn:" + resetColor);
                    opponent.performRandomAttack(activePokemon);

                    if (activePokemon.healthPoints <= 0) {
                        System.out.println(activePokemon.name + " fainted!");
                        boolean allUserPokemonFainted = true;
                        for (Pokemon userPokeCheck : userPokemon) {
                            if (userPokeCheck.healthPoints > 0) {
                                allUserPokemonFainted = false;
                                break;
                            }
                        }
                        allUserPokemonDead = allUserPokemonFainted;
                    }
                }
                if (opponentDead) {
                    System.out.println("You defeated the wild " + opponent.name + " in Round " + round + "!");
                    crystals += CRYSTALS_PER_VICTORY;
                } else {
                    System.out.println("All your Pokemon fainted in Round " + round + ". Battle over!");
                    break;
                }
            }
        }
        return crystals;
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
