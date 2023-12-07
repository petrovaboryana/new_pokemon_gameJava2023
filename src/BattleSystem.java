import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {//да извадя сканера
    private static final int NUM_BATTLES = 5;
    private static final int NUM_ROUNDS_PER_BATTLE = 3;
    private static final int CRYSTALS_PER_VICTORY = 10;

    public int startTournament(List<Pokemon> userPokemon) {//да коригирам битките, така не е добре
        int crystals = 0;

        for (int battleNum = 1; battleNum <= NUM_BATTLES; battleNum++) {
            System.out.println("\n----- Battle " + battleNum + " -----");

            for (int round = 1; round <= NUM_ROUNDS_PER_BATTLE; round++) {
                System.out.println("\n--- Round " + round + " ---");
                Pokemon opponent = getRandomOpponent();

                System.out.println("You are facing a " + opponent.name + "!");

                boolean allUserPokemonDead = false;
                boolean opponentDead = false;

                while (!allUserPokemonDead && !opponentDead) {
                    Pokemon activePokemon = PokemonMenu.chooseActivePokemon(userPokemon);

                    System.out.println("\nYour " + activePokemon.name + "'s turn:");
                    displayBattleOptions();
                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        activePokemon.performAttack(opponent);
                    } else if (choice == 2) {
                        changePokemon(userPokemon, activePokemon);
                    }

                    if (opponent.healthPoints <= 0) {
                        opponentDead = true;
                        break;
                    }
                    System.out.println("Opponent's turn:");
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
        System.out.println("1. Attack");
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
    private void changePokemon(List<Pokemon> userPokemon, Pokemon currentPokemon) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a Pokemon to switch to:");
        PokemonMenu.displayUserPokemon(userPokemon);
        int switchIndex = scanner.nextInt();

        if (switchIndex >= 1 && switchIndex <= userPokemon.size()) {
            Pokemon newPokemon = userPokemon.get(switchIndex - 1);

            if (newPokemon.healthPoints > 0) {
                System.out.println("Switching to " + newPokemon.name + "!");
                currentPokemon = newPokemon;
            } else {
                System.out.println(newPokemon.name + " has fainted and cannot be switched to!");
            }
        } else {
            System.out.println("Invalid choice. No switch was made.");
        }
    }
//    private void displayUserPokemon(List<Pokemon> userPokemon) {
//        System.out.println("Your Pokemon:");
//        for (int i = 0; i < userPokemon.size(); i++) {
//            Pokemon pokemon = userPokemon.get(i);
//            System.out.println((i + 1) + ". " + pokemon.name + " (HP: " + pokemon.healthPoints + ")");
//        }
//    }
//    private Pokemon chooseActivePokemon(List<Pokemon> userPokemon) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Choose the active Pokemon for this round:");
//        displayUserPokemon(userPokemon);
//        int selectedIndex;
//
//        do {
//            System.out.println("Select by number:");
//            selectedIndex = scanner.nextInt();
//            if (selectedIndex < 1 || selectedIndex > userPokemon.size()) {
//                System.out.println("Invalid choice. Please select a valid Pokemon.");
//            }
//        } while (selectedIndex < 1 || selectedIndex > userPokemon.size());
//
//        return userPokemon.get(selectedIndex - 1);
//    }

}
