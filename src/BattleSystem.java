import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {//да извадя сканера
    private static final int NUM_BATTLES = 3;
    private static final int NUM_ROUNDS_PER_BATTLE = 5;
    private static final int CRYSTALS_PER_ROUND = 10;
    private static final int DIAMONDS_PER_BATTLE = 5;
    private static final int HEAL_COST = 10;
    String redColor = "\u001B[31m";
    String resetColor = "\u001B[0m";
    String greenColor = "\u001B[32m";
    public int startTournament(List<Pokemon> userPokemon) {
        int totalDiamonds = 0;

        for (int battleNum = 1; battleNum <= NUM_BATTLES; battleNum++) {
            System.out.println(redColor + "\n----- Battle " + battleNum + " -----" + resetColor);
            Pokemon opponent = getRandomOpponent();
            Pokemon activePokemon = PokemonMenu.chooseActivePokemon(userPokemon);
            int diamondsEarned = playBattle(userPokemon,opponent, activePokemon);
            totalDiamonds += diamondsEarned;

            if (diamondsEarned == 0) {
                System.out.println("You lost the Battle " + battleNum + ". Better luck next time!");
            }
        }
        return totalDiamonds;
    }
    private int playBattle(List<Pokemon> userPokemon,Pokemon opponent, Pokemon activePokemon) {
        int userWins = 0;
        int opponentWins =0;
        int winnercristals = 0;

        for (int round = 1; round <= NUM_ROUNDS_PER_BATTLE; round++) {
            System.out.println("\n--- Round " + round + " ---");
            int result = playRound(activePokemon,opponent,userPokemon);
            if (result == 1) {
                userWins++;
                winnercristals += CRYSTALS_PER_ROUND;
            } else if (result == 2) {
                opponentWins++;
                System.out.println("Better luck next round!");
            }
        }
        return whoWinsTheBattle(userWins,opponentWins);
    }
    public int whoWinsTheBattle(int userWins, int opponentWins) {
        int diamonds = 0;
        if (userWins < opponentWins) {
            System.out.println("You lost the battle.");
            return 0;
        } else if (userWins == opponentWins){
            System.out.println("No winner in this Battle!");
            return 0;
        }
        System.out.println("You won the battle!" );
        return diamonds += DIAMONDS_PER_BATTLE;
    }

    private int playRound(Pokemon activePokemon,Pokemon opponent,List<Pokemon> userPokemon) {
        System.out.println("You are facing a " + opponent.getColor() + opponent.name + " HP: " + opponent.healthPoints + resetColor + "!");
        System.out.println("Your " + activePokemon.getColor() + activePokemon.name + resetColor + "'s turn:");
        displayBattleOptions();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        activePokemon = getActivePokemon(userPokemon, choice, activePokemon, opponent);

        System.out.println(redColor + "Opponent's turn:" + redColor);
        opponent.performRandomAttack(activePokemon);
        System.out.println("Your " + activePokemon.name + "'s HP: " + activePokemon.getHealthPoints());
        System.out.println("Opponent's " + opponent.name + "'s HP: " + opponent.getHealthPoints());

        return whoWinsTheRound(opponent, activePokemon);
    }
    private Pokemon getActivePokemon(List<Pokemon> userPokemon, int choice, Pokemon activePokemon, Pokemon opponent) {
        switch (choice) {
            case 1:
                activePokemon.performAttack(opponent);
                System.out.println("Your " + activePokemon.name + "'s HP: " + activePokemon.getHealthPoints());
                System.out.println("Opponent's " + opponent.name + "'s HP: " + opponent.getHealthPoints());
                break;
            case 2:
                activePokemon = changePokemon(userPokemon, activePokemon);
                activePokemon.performAttack(opponent);
                break;
            case 3:
                healPokemonAction(activePokemon);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return activePokemon;
    }

    private static int whoWinsTheRound(Pokemon opponent, Pokemon activePokemon) {
        if (opponent.healthPoints < activePokemon.healthPoints) {
            System.out.println("You won the round!");
            return 1;
        } else if (opponent.healthPoints == activePokemon.healthPoints ) {
            System.out.println("No one wins!");
            return 0;
        }
        System.out.println("Your " + activePokemon.name + " lost!");
        return 2;
    }

    private void displayBattleOptions() {
        System.out.println("Choose your action:");
        System.out.println(redColor + "1. Attack" + resetColor);
        System.out.println("2. Change Pokemon");
        System.out.println(greenColor + "3. Heal Pokemon" + resetColor);
    }
    private Pokemon getRandomOpponent() {
        List<Pokemon> availableOpponents = new ArrayList<>();
        availableOpponents.add(new ElectricPokemon("Wild Pikachu", Size.NORMAL));
        availableOpponents.add(new WaterPokemon("Wild Squirtle", Size.NORMAL));
        availableOpponents.add(new GroundPokemon("Wild Geodude", Size.NORMAL));
        availableOpponents.add(new ElectricPokemon("Wild Shockle",Size.LARGE));
        availableOpponents.add(new WaterPokemon("Wild Shark",Size.LARGE));
        availableOpponents.add(new GroundPokemon("Wild Tree",Size.SMALL));

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
    private void healPokemonAction(Pokemon selectedPokemon) {
        System.out.println("Do you want to heal " + selectedPokemon.getName() + "? (Cost: " + HEAL_COST + " crystals)");
        System.out.println("1. Yes");
        System.out.println("2. No");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            selectedPokemon.heal(HEAL_COST);
            System.out.println(selectedPokemon.getName() + " has been healed. New HP is: " + selectedPokemon.getHealthPoints());
        } else {
            System.out.println("Healing canceled.");
        }
    }

}
