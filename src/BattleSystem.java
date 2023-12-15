import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    private static final int NUM_BATTLES = 5;
    private static final int NUM_ROUNDS_PER_BATTLE = 5;
    private static final int CRYSTALS_PER_ROUND = 10;
    private static final int DIAMONDS_PER_BATTLE = 5;
    private static final int HEAL_COST = 10;
    String redColor = "\u001B[31m";
    String resetColor = "\u001B[0m";
    String greenColor = "\u001B[32m";
    private static Scanner scanner = new Scanner(System.in);


    public Result startTournament(List<Pokemon> userPokemon) {
        int totalDiamonds = 0;
        int totalCrystals = 0;
        Result firstResult = new Result(0,0);

        for (int battleNum = 1; battleNum <= NUM_BATTLES; battleNum++) {
            System.out.println(redColor + "\n----- Battle " + battleNum + " -----" + resetColor);
            Pokemon opponent = getRandomOpponent();
            Pokemon activePokemon = PokemonMenu.chooseActivePokemon(userPokemon);
            Result battleResult = playBattle(userPokemon, opponent, activePokemon,firstResult);
            totalDiamonds += battleResult.getDiamondsFromBattles();
            totalCrystals += battleResult.getCrystalsFromRounds();

            if (battleResult.getDiamondsFromBattles() == 0) {
                System.out.println("You lost the Battle " + battleNum + ". Better luck next time!");
            }
        }
        return new Result(totalCrystals, totalDiamonds);
    }
    public Result playBattle(List<Pokemon> userPokemon, Pokemon opponent, Pokemon activePokemon,Result result) {
        int userWins = 0;
        int opponentWins =0;
        int crystalsEarned = 0;

        for (int round = 1; round <= NUM_ROUNDS_PER_BATTLE; round++) {
            System.out.println("\n--- Round " + round + " ---");
            int newResult = playRound(activePokemon,opponent,userPokemon,result);

            if (newResult == 1) {
                userWins++;
                crystalsEarned += CRYSTALS_PER_ROUND;
                result.setCrystalsFromRounds(crystalsEarned);
            } else if (newResult == 2) {
                opponentWins++;
                System.out.println("Better luck next round!");
            }
        }
        return new Result(result.getCrystalsFromRounds(), whoWinsTheBattle(userWins, opponentWins));
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
        System.out.println("\u001B[33m" + "You won the battle!" + resetColor );
        return diamonds += DIAMONDS_PER_BATTLE;
    }

    public int playRound(Pokemon activePokemon,Pokemon opponent,List<Pokemon> userPokemon,Result result) {
        System.out.println("You are facing a " + opponent.getColor() + opponent.name + " HP: " + opponent.healthPoints + resetColor + "!");
        System.out.println("Your " + activePokemon.getColor() + activePokemon.name + resetColor + "'s turn:");
        displayBattleOptions();
        int choice = scanner.nextInt();
        Pokemon activePokemonRound = getActivePokemon(userPokemon, choice, activePokemon,opponent,result);

        System.out.println(redColor + "Opponent's turn:" + redColor);
        opponent.performRandomAttack(activePokemon);
        System.out.println("Your " + activePokemon.name + "'s HP: " + activePokemon.getHealthPoints());
        System.out.println("Opponent's " + opponent.name + "'s HP: " + opponent.getHealthPoints());

        return whoWinsTheRound(opponent, activePokemonRound);
    }
    public Pokemon getActivePokemon(List<Pokemon> userPokemon, int choice, Pokemon activePokemon, Pokemon opponent, Result result) {
        Pokemon active1 = null;

        switch (choice) {
            case 1:
                active1 = activePokemon;
                active1.performAttack(opponent);
                System.out.println("Your " + activePokemon.name + "'s HP: " + activePokemon.getHealthPoints());
                System.out.println("Opponent's " + opponent.name + "'s HP: " + opponent.getHealthPoints());
                break;
            case 2:
                active1 = changePokemon(userPokemon, active1);
                active1.performAttack(opponent);
                break;
            case 3:
                healPokemonAction(activePokemon,result);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return active1;
    }

    public int whoWinsTheRound(Pokemon opponent, Pokemon activePokemon) {
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
    public Pokemon getRandomOpponent() {
        List<Pokemon> availableOpponents = new ArrayList<>();
        availableOpponents.add(new ElectricPokemon("Wild Pikachu", Size.NORMAL));
        availableOpponents.add(new WaterPokemon("Wild Squirtle", Size.NORMAL));
        availableOpponents.add(new GroundPokemon("Wild Geodude", Size.NORMAL));
        availableOpponents.add(new ElectricPokemon("Wild Shockle",Size.LARGE));
        availableOpponents.add(new WaterPokemon("Wild Shark",Size.LARGE));
        availableOpponents.add(new GroundPokemon("Wild Tree",Size.SMALL));

        Random random = new Random();
        int randomIndex = random.nextInt(availableOpponents.size());
        if(availableOpponents.get(randomIndex).getHealthPoints() == 0 && randomIndex < availableOpponents.size()){
            randomIndex++;
        }else if (availableOpponents.get(randomIndex).getHealthPoints() == 0 && randomIndex == availableOpponents.size() && availableOpponents.size() >= 1){
            randomIndex--;
        }
        return availableOpponents.get(randomIndex);
    }
    public Pokemon changePokemon(List<Pokemon> userPokemon, Pokemon currentPokemon) {
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
    private void healPokemonAction(Pokemon selectedPokemon,Result result) {
        System.out.println("Do you want to heal " + selectedPokemon.getName() + "? (Cost: " + HEAL_COST + " crystals)");
        System.out.println("Your crystals: " + result.getCrystalsFromRounds());
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = scanner.nextInt();

        if (choice == 1) {
            if (result.getCrystalsFromRounds() >= HEAL_COST){
                selectedPokemon.heal(HEAL_COST);
                int currentCrystals = result.getCrystalsFromRounds() - HEAL_COST;
                result.setCrystalsFromRounds(currentCrystals);
                System.out.println(selectedPokemon.getName() + " has been healed. New HP is: " + selectedPokemon.getHealthPoints());
            }else {
                System.out.println("You don't have enough crystals to heal " + selectedPokemon.getName() + ".");
            }
        } else {
            System.out.println("Healing canceled.");
        }
    }

}
